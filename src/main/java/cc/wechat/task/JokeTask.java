package cc.wechat.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
		jokeService.init(50);
		logger.debug("==============================TASK JokeTask END==========================");
	}
}
