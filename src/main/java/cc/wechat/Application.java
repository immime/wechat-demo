package cc.wechat;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import cc.wechat.config.WebappConfig;
import cc.wechat.sdk.mp.api.WxMpInMemoryConfigStorage;
import cc.wechat.sdk.mp.api.WxMpService;
import cc.wechat.sdk.mp.api.WxMpServiceImpl;
import cc.wechat.sdk.mp.rest.WeixinApi;
import cc.wechat.sdk.mp.rest.entity.msg.send.BaseSendMsg;
import cc.wechat.sdk.mp.rest.entity.msg.send.Text;
import cc.wechat.sdk.mp.rest.entity.msg.send.TextSendMsg;


@SpringBootApplication
@EnableScheduling
@Import(WebappConfig.class)
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
		WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAccessToken(TOKEN);
		config.setAppId(APP_ID);
		config.setSecret(APP_SECRET);
		WxMpService wxMpService = new WxMpServiceImpl();
	    wxMpService.setWxMpConfigStorage(config);
	    
	    TextSendMsg sendMsg = new TextSendMsg();
		sendMsg.setTouser("oODT9jpg8GZG90ewWP3QPDjlKhVE");
		sendMsg.setMsgtype("text");
		sendMsg.setText(new Text("aaaaa"));
		BaseSendMsg result = WeixinApi.ForSendMsg.customSend(sendMsg);
	}
	
}
