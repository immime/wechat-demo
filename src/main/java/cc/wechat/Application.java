package cc.wechat;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.MapSession;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

import cc.wechat.config.AppConfig;
import cc.wechat.config.SessionManager;
import cc.wechat.sdk.session.IWechatSession;
import cc.wechat.sdk.session.WechatSession;


@SpringBootApplication
@EnableScheduling
@Import(AppConfig.class)
public class Application {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Autowired(required=true)
	private SessionRepository<?> sessionRepo;
	
	@PostConstruct
	public void logSomething() {
		logger.trace("------------------------------------------Active Log Level [Trace]------------------------------------------");
		logger.info("------------------------------------------Active Log Level [Info]------------------------------------------");
		logger.warn("------------------------------------------Active Log Level [Warn]------------------------------------------");
		logger.error("------------------------------------------Active Log Level [Error]------------------------------------------");
		logger.debug("------------------------------------------Active Log Level [Debug]------------------------------------------");
		
		Session session = sessionRepo.createSession();
		session.setAttribute("test", "aaa");
		logger.info("------------------------------------------Session is fired []------------------------------------------");
		
		String sId = session.getId();
		Session sessions = sessionRepo.getSession(sId);
		String val = sessions.getAttribute("test");
		System.out.println("------------value from session is:" + val);
		
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
}
