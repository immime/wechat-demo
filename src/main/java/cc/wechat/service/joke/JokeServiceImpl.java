package cc.wechat.service.joke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.wechat.config.ApiConfigCenter;
import cc.wechat.constant.WechatConsts;
import cc.wechat.data.domain.Joke;
import cc.wechat.data.repository.JokeRepository;
import cc.wechat.openapi.ApiStoreClient;
import cc.wechat.openapi.exception.ApiStoreException;
import cc.wechat.sdk.api.MaterialAPI;
import cc.wechat.sdk.api.config.ApiConfig;
import cc.wechat.sdk.api.entity.Article;
import cc.wechat.sdk.api.enums.MaterialType;
import cc.wechat.sdk.api.response.DownloadMaterialResponse;
import cc.wechat.sdk.api.response.UploadMaterialResponse;
import cc.wechat.sdk.message.ArticleMsg;
import cc.wechat.sdk.message.NewsMsg;
import cc.wechat.sdk.message.req.BaseReq;

@Service("jokeService")
@Transactional
public class JokeServiceImpl implements JokeService {
	
	@Autowired
	private JokeRepository jokeRepository;

	//TODO 请求一次api返回二十条，应该缓存起来，用户重复点击时优先使用缓存。这样少消耗api请求次数
	
	@Override
	public Joke queryOneJoke() {
		List<Joke> jokes = this.queryBatchJokes();
		Joke j = new Joke();
		if(CollectionUtils.isNotEmpty(jokes)) {
			int size = jokes.size();
			Random random = new Random();
			int index = random.nextInt(size);
			j = jokes.get(index);
		}
		return j;
	}

	@Override
	public List<Joke> queryBatchJokes() {
		Random random = new Random();
		int index = random.nextInt(10);
		String uri = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text?page=" + index;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("apikey", WechatConsts.BAIDU_API_KEY);
		HttpEntity<Object> request = new HttpEntity<Object>(headers);
		String rawResult = restTemplate.postForObject(uri, request, String.class);
		JSONObject rawJsonObj = JSON.parseObject(rawResult);  
		JSONObject bodyJsonObj = rawJsonObj.getJSONObject("showapi_res_body");
		
		JSONArray result = bodyJsonObj.getJSONArray("contentlist");  
		List<Joke> jokes= JSON.parseArray(result.toJSONString(),Joke.class);
		
		return jokes;
	}

	@Override
	public NewsMsg queryJokeNewsMsg(BaseReq req) throws ApiStoreException {
		// TODO Auto-generated method stub
		ApiConfig config = ApiConfigCenter.getCongig();
		MaterialAPI materialAPI = new MaterialAPI(config);
		
		List<Joke> jokes = this.queryBatchJokes();
		List<Article> articles = new ArrayList<>();
		
		if(CollectionUtils.isNotEmpty(jokes)) {
			for (int i=0; i<4; i++) {
				Joke joke = jokes.get(i);
				Article article = new Article("twcBNCdySHHdz_3ivG6CR3J5mXKlqxvim4Eu_3rWHYI", "John.Wen", joke.getTitle(), "http://www.baidu.com", joke.getText(), joke.getText().substring(0, 30), Article.ShowConverPic.YES);
				articles.add(article);
			}
		}
		
        UploadMaterialResponse response = materialAPI.uploadMaterialNews(articles);
		String mediaId = response.getMediaId();
		DownloadMaterialResponse download = materialAPI.downloadMaterial(mediaId, MaterialType.NEWS);
		List<ArticleMsg> articleMsgs = download.getNews();
		NewsMsg msg = new NewsMsg();
		msg.setArticles(articleMsgs);
		msg.setCreateTime(System.currentTimeMillis());
		msg.setFromUserName(req.getToUserName());
		msg.setToUserName(req.getFromUserName());
		
		return msg;
	}

	@Override
	public void batchPersist() {
		// TODO Auto-generated method stub
		JSONObject bodyJsonObj =  ApiStoreClient.post("/showapi_joke/joke_text?page=1", null);
		
		JSONArray result = bodyJsonObj.getJSONArray("contentlist"); 
		List<Joke> jokes= JSON.parseArray(result.toJSONString(),Joke.class);
		
		jokeRepository.save(jokes);
	}

	



}
