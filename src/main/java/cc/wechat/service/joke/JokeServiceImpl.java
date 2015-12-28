package cc.wechat.service.joke;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.wechat.config.ApiConfigCenter;
import cc.wechat.data.domain.Joke;
import cc.wechat.data.repository.JokeRepository;
import cc.wechat.openapi.ApiStoreClient;
import cc.wechat.sdk.api.MaterialAPI;
import cc.wechat.sdk.api.config.ApiConfig;
import cc.wechat.sdk.api.entity.Article;
import cc.wechat.sdk.api.enums.MaterialType;
import cc.wechat.sdk.api.response.DownloadMaterialResponse;
import cc.wechat.sdk.api.response.UploadMaterialResponse;
import cc.wechat.sdk.message.ArticleMsg;
import cc.wechat.sdk.message.NewsMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.BaseReq;

@Service("jokeService")
@Transactional
public class JokeServiceImpl implements JokeService {
	private static final Logger logger = LoggerFactory.getLogger(JokeServiceImpl.class);

	
	@Autowired
	private ApiConfigCenter apiConfigCenter;
	@Autowired
	private JokeRepository jokeRepository;

	//TODO 请求一次api返回二十条，应该缓存起来，用户重复点击时优先使用缓存。这样少消耗api请求次数
	
	@SuppressWarnings("unchecked")
	@Override
	public Joke findRandomOne() {
		Iterable<Joke> jokes = this.jokeRepository.findAll();
		Iterator<Joke> i = jokes.iterator();
		List<Joke> list = IteratorUtils.toList(i);
		int size = list.size();
		Random random = new Random();
		int index = random.nextInt(size);
		return list.get(index);
	}

	@SuppressWarnings("unchecked")
	@Override
	public NewsMsg queryJokeNewsMsg(BaseReq req) {
		ApiConfig config = apiConfigCenter.getConfig();
		MaterialAPI materialAPI = new MaterialAPI(config);
		
		Iterable<Joke> results = this.jokeRepository.findAll();
		Iterator<Joke> iterator = results.iterator();
		List<Joke> jokes = IteratorUtils.toList(iterator);
		
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
	public void batchPersist(List<Joke> jokes) {
		jokeRepository.save(jokes);
	}

	@Override
	public TextMsg queryOneJokeTextMsg() {
		Joke j = this.findRandomOne();
		TextMsg msg = new TextMsg();
		msg.add("《").add(j.getTitle()).add("》").add("\n").add(j.getText());
		return msg;
	}

	@Override
	public void init(int size) {
		Random r = new Random();
		int pageNo = r.nextInt(100);
		JSONObject bodyJsonObj =  ApiStoreClient.post("/showapi_open_bus/showapi_joke/joke_text?page=" + pageNo + "&maxResult=" + size , null);
		JSONObject result = bodyJsonObj.getJSONObject("showapi_res_body"); 
		Assert.notNull(result, "Failed to request Token on URL：/showapi_open_bus/showapi_joke/joke_text");
		JSONArray contentlist = result.getJSONArray("contentlist"); 
		List<Joke> jokes= JSON.parseArray(contentlist.toJSONString(),Joke.class);
		this.batchPersist(jokes);
		logger.debug("persist jokes sieze is {}", jokes.size());
	}


}
