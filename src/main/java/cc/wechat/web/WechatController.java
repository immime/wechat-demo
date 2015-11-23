package cc.wechat.web;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.wechat.sdk.api.TokenStore;
import cc.wechat.sdk.bean.xml.EventMessage;
import cc.wechat.sdk.bean.xml.XMLTextMessage;
import cc.wechat.service.ISessionService;
import cc.wechat.service.IWechatService;
import cc.wechat.utils.ExpireSet;
import cc.wechat.utils.SignatureUtil;
import cc.wechat.utils.XMLConverUtil;

@RestController
@RequestMapping("/weixin")
public class WechatController {
	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

	@Autowired
	private IWechatService service;
	@Autowired
	private ISessionService sessionService;

	// 重复通知过滤 时效60秒
	private static ExpireSet<String> expireSet = new ExpireSet<String>(60);

	@RequestMapping(produces = "application/xml;charset=UTF-8")
	public String process(HttpServletRequest request) {
		ServletInputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e) {
			logger.error("Read request InputStream error.");
			e.printStackTrace();
		}
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		// 首次请求申请验证,返回echostr
		if (echostr != null) {
			return echostr;
		}

		// 验证请求签名
		if (!signature.equals(SignatureUtil.generateEventMessageSignature(TokenStore.get(), timestamp, nonce))) {
			System.out.println("The request signature is invalid");
			return null;
		}

		if (inputStream != null) {
			// 转换XML
			EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream);
			String expireKey = eventMessage.getFromUserName() + "__" + eventMessage.getToUserName() + "__"
					+ eventMessage.getMsgId() + "__" + eventMessage.getCreateTime();
			if (expireSet.contains(expireKey)) {
				// 重复通知不作处理
				return null;
			} else {
				expireSet.add(expireKey);
			}

			// 创建回复
			XMLTextMessage xmlTextMessage = new XMLTextMessage(eventMessage.getFromUserName(),
					eventMessage.getToUserName(), "你好");
			// 回复
			return xmlTextMessage.toString();
		}
		return echostr;
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
