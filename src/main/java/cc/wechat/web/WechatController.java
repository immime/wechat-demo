package cc.wechat.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.wechat.sdk.handle.EventHandle;
import cc.wechat.sdk.handle.MessageHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.BaseReqEvent;
import cc.wechat.sdk.servlet.WeixinControllerSupport;
import cc.wechat.service.session.SessionService;

@RestController
@RequestMapping("/")
public class WechatController extends WeixinControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
	private static final String TOKEN = "myqiqi";

	@Autowired
	private SessionService sessionService;
	@Autowired
	private EventHandle<BaseReqEvent> menuEventHandle;
	private MessageHandle<BaseReqEvent> defaultMessageHandle;
	private MessageHandle<BaseReqEvent> robotMessageHandle;

	@Override
	protected String getToken() {
		return TOKEN;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected List<MessageHandle> initMessageHandles() {
		// TODO Auto-generated method stub
		System.err.println("autowire SUCCESS:" + (menuEventHandle != null));
		List<MessageHandle> handles = new ArrayList<MessageHandle>();
		handles.add(defaultMessageHandle);
		handles.add(robotMessageHandle);
		return handles;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List<EventHandle> initEventHandles() {
		// TODO Auto-generated method stub
		List<EventHandle> handles = new ArrayList<EventHandle>();
		handles.add(menuEventHandle);
		return handles;
	}

	/**
	 * 关注
	 */
	@Override
	protected BaseMsg handleSubscribe(BaseReqEvent event) {
		// TODO Auto-generated method stub
		logger.debug("用户关注");
		return new TextMsg(String.format("来自服务器回复用户消息:%s", "欢迎光临！"));
	}

	/**
	 * 取消关注
	 */
	@Override
	protected BaseMsg handleUnsubscribe(BaseReqEvent event) {
		// TODO Auto-generated method stub
		logger.debug("用户取消关注");
		return new TextMsg(String.format("来自服务器回复用户消息:%s", "又见面了！"));
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
