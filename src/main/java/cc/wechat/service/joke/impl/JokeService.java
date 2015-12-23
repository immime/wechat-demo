package cc.wechat.service.joke.impl;

import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.wechat.constant.WechatConsts;
import cc.wechat.service.joke.IJokeService;
import cc.wechat.service.joke.bean.Joke;

@Service
public class JokeService implements IJokeService {

	//TODO 请求一次api返回二十条，应该缓存起来，用户重复点击时优先使用缓存。这样少消耗api请求次数
	
	@Override
	public Joke getRandomJoke() {
		String uri = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text?page=1";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("apikey", WechatConsts.BAIDU_API_KEY);
		HttpEntity<Object> request = new HttpEntity<Object>(headers);
		String rawResult = restTemplate.postForObject(uri, request, String.class);
		JSONObject rawJsonObj = JSON.parseObject(rawResult);  
		JSONObject bodyJsonObj = rawJsonObj.getJSONObject("showapi_res_body");
		
		JSONArray result = bodyJsonObj.getJSONArray("contentlist");  
		List<Joke> jokes= JSON.parseArray(result.toJSONString(),Joke.class);
		Joke j = new Joke();
		if(CollectionUtils.isNotEmpty(jokes)) {
			int size = jokes.size();
			Random random = new Random();
			int index = random.nextInt(size);
			j = jokes.get(index);
		}
		return j;
	}


}
