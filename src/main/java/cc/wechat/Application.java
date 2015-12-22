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
//@Import(AppConfig.class)
public class Application {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
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
