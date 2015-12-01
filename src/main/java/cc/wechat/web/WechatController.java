package cc.wechat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.BaseEvent;
import cc.wechat.sdk.message.req.MenuEvent;
import cc.wechat.sdk.message.req.TextReqMsg;
import cc.wechat.sdk.servlet.WeixinControllerSupport;
import cc.wechat.service.IWechatService;
import cc.wechat.service.joke.IJokeService;
import cc.wechat.service.joke.bean.Joke;
import cc.wechat.service.session.ISessionService;

@RestController
@RequestMapping("/")
public class WechatController extends WeixinControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
	private static final String TOKEN = "myqiqi";

	@Autowired
	private IWechatService wechatService;
	@Autowired
	private ISessionService sessionService;
	@Autowired
	private IJokeService jokeService;

	@Override
	protected String getToken() {
		return TOKEN;
	}
	
	// 重写父类方法，处理对应的微信消息
	@Override
	protected BaseMsg handleTextMsg(TextReqMsg msg) {
		String content = msg.getContent();
		logger.debug("用户发送到服务器的内容:{}", content);

		String key = "context".concat(":").concat(msg.getFromUserName());
		sessionService.put(key, content);

		return new TextMsg(
				String.format(
						"欢迎光临！\n%s",
						"<a href='http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzA3NDMyNzc5Mg==#wechat_webview_type=1&wechat_redirect'>查看历史消息</a>"));
	}
	
	@Override
	protected BaseMsg handleMenuClickEvent(MenuEvent event) {
		// TODO Auto-generated method stub
		String clickKey = event.getEventKey();
		switch (clickKey) {
		case "MENU_CLICK_JOKE":
			Joke j = jokeService.getRandomJoke();
			return new TextMsg("《" + j.getTitle() + "》" + "\n" + j.getText());
		default:
			break;
		}
		
		return super.handleMenuClickEvent(event);
	}

	/**
	 * 关注
	 */
	@Override
	protected BaseMsg handleSubscribe(BaseEvent event) {
		// TODO Auto-generated method stub
		logger.debug("用户关注");
		return new TextMsg(String.format("来自服务器回复用户消息:%s", "欢迎光临！"));
	}

	/**
	 * 取消关注
	 */
	@Override
	protected BaseMsg handleUnsubscribe(BaseEvent event) {
		// TODO Auto-generated method stub
		logger.debug("用户取消关注");
		return new TextMsg(String.format("来自服务器回复用户消息:%s", "再会！"));
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
