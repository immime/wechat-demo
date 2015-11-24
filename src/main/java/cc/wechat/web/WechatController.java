package cc.wechat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;

import cc.wechat.service.ISessionService;
import cc.wechat.service.IWechatService;

@RestController
@RequestMapping("/")
public class WechatController extends WeixinControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
	private static final String TOKEN = "myqiqi";

	@Autowired
	private IWechatService service;
	@Autowired
	private ISessionService sessionService;
	

	@Override
	protected String getToken() {
		return TOKEN;
	}

	//重写父类方法，处理对应的微信消息
    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent();
        logger.debug("用户发送到服务器的内容:{}", content);
        return new TextMsg(String.format("来自服务器回复用户消息:%s", content));
    }
	
	/* Session test */

	@RequestMapping(value = "/put/{key}/{value}")
	public String testPut(@PathVariable("key") String key, @PathVariable("value") String value) {
		sessionService.put(key, value);
		return "success";
	}

	@RequestMapping(value = "/get/{key}")
	public Object testGet(@PathVariable("key") String key) {
		return sessionService.get(key);
	}

}
