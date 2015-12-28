package cc.wechat.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.wechat.config.ApiConfigCenter;
import cc.wechat.handle.ContextMessageHandle;
import cc.wechat.handle.DefaultMessageHandle;
import cc.wechat.handle.MenuEventHandle;
import cc.wechat.handle.RobotMessageHandle;
import cc.wechat.sdk.api.JsAPI;
import cc.wechat.sdk.api.config.ApiConfig;
import cc.wechat.sdk.api.response.BaseResponse;
import cc.wechat.sdk.api.response.GetSignatureResponse;
import cc.wechat.sdk.handle.EventHandle;
import cc.wechat.sdk.handle.MessageHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.BaseReqEvent;
import cc.wechat.sdk.servlet.WeixinControllerSupport;
import cc.wechat.service.context.ContextService;
import cc.wechat.service.session.SessionService;

@RestController
@RequestMapping("/wechat")
public class WechatController extends WeixinControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
	private static final String TOKEN = "myqiqi";

	@Autowired
	private ApiConfigCenter apiConfigCenter;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private MenuEventHandle menuEventHandle;
	@Autowired
	private DefaultMessageHandle defaultMessageHandle;
	@Autowired
	private RobotMessageHandle robotMessageHandle;
	@Autowired
	private ContextMessageHandle contextMessageHandle;
	
	/**
	 * js指纹验证
	 * @param url
	 * @return
	 */
	@RequestMapping("/jsSignature")
	public BaseResponse helloWorld(@RequestParam String url) {
		ApiConfig config = apiConfigCenter.getConfig();
		JsAPI api = new JsAPI(config);
		GetSignatureResponse response = api.getSignature(url);
		return response;
	}

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
		handles.add(contextMessageHandle);
		handles.add(robotMessageHandle);
		handles.add(defaultMessageHandle);
		return handles;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List<EventHandle> initEventHandles() {
		// TODO 个人订阅号开发者模式的自定义菜单无效
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
