package cc.wechat.service.joke;

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

import cc.wechat.service.joke.bean.Joke;

@Service
public class JokeService implements IJokeService {

	@Override
	public Joke getRandomJoke() {
		String uri = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text?page=1";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("apikey", "7525894c141b957d9bd0acb8eededfcb");
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
