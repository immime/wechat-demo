package cc.wechat;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import cc.wechat.config.AppConfig;


@SpringBootApplication
@EnableScheduling
@Import(AppConfig.class)
public class Application {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	private static final String TOKEN = "myqiqi";
	private static final String APP_ID = "wxe6626fc25736c77e";
	private static final String APP_SECRET = "c5ea13a94c08a1ed07fc4eaeb6ca913b";
	
	@PostConstruct
	public void logSomething() {
		logger.trace("------------------------------------------Active Log Level [Trace]------------------------------------------");
		logger.info("------------------------------------------Active Log Level [Info]------------------------------------------");
		logger.warn("------------------------------------------Active Log Level [Warn]------------------------------------------");
		logger.error("------------------------------------------Active Log Level [Error]------------------------------------------");
		logger.debug("------------------------------------------Active Log Level [Debug]------------------------------------------");
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
}
