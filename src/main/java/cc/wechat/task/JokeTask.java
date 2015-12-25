package cc.wechat.task;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.wechat.data.domain.Joke;
import cc.wechat.openapi.ApiStoreClient;
import cc.wechat.openapi.exception.ApiStoreException;
import cc.wechat.service.joke.JokeService;

@Component
public class JokeTask {
	private static final Logger logger = LoggerFactory.getLogger(JokeTask.class);

	@Autowired
	private JokeService jokeService;
	
	/**
	 * 每隔30分钟刷新一次
	 * @return
	 * @throws FailedToGetTokenException 
	 */
	@Scheduled(fixedRate = 1000 * 60 * 30)
	private void refresh() throws ApiStoreException {
		logger.debug("==============================TASK JokeTask BEGIN==========================");
		Random r = new Random();
		int pageNo = r.nextInt(100);
		JSONObject bodyJsonObj =  ApiStoreClient.post("/showapi_joke/joke_text?page=" + pageNo + "&maxResult=50", null);
		JSONArray result = bodyJsonObj.getJSONArray("contentlist"); 
		List<Joke> jokes= JSON.parseArray(result.toJSONString(),Joke.class);
		this.jokeService.batchPersist(jokes);
		logger.debug("persist jokes sieze is {}", jokes.size());
		logger.debug("==============================TASK JokeTask END==========================");
	}
}
