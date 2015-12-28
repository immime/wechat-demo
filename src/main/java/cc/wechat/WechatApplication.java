package cc.wechat;

import javax.annotation.PostConstruct;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import cc.wechat.config.AppConfig;
import cc.wechat.config.WechatProperties;

@SpringBootApplication
@EnableScheduling
@Import(AppConfig.class)
@EnableConfigurationProperties({ WechatProperties.class })
public class WechatApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(WechatApplication.class);
	
	@PostConstruct
	public void logSomething() {
		logger.trace("------------------------------------------Active Log Level [Trace]------------------------------------------");
		logger.info("------------------------------------------Active Log Level [Info]------------------------------------------");
		logger.warn("------------------------------------------Active Log Level [Warn]------------------------------------------");
		logger.error("------------------------------------------Active Log Level [Error]------------------------------------------");
		logger.debug("------------------------------------------Active Log Level [Debug]------------------------------------------");
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WechatApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
	    registration.addUrlMappings("/console/*");
	    return registration;
	}
	
}
