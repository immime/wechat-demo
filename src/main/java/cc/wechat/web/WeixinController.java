package cc.wechat.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.wechat.config.WeixinManager;
import cc.wechat.sdk.mp.api.WxMpConfigStorage;
import cc.wechat.sdk.mp.api.WxMpService;
import cc.wechat.sdk.mp.bean.WxMpXmlMessage;
import cc.wechat.sdk.mp.bean.WxMpXmlOutMessage;
import cc.wechat.sdk.mp.bean.WxMpXmlOutTextMessage;

@RestController
@RequestMapping("/weixin")
public class WeixinController {
	private static final Logger logger = LoggerFactory.getLogger(WeixinController.class);

	private final static WxMpConfigStorage config = WeixinManager.getConfig();
	private final static WxMpService wxMpService = WeixinManager.getService();

	@RequestMapping(produces = "application/xml;charset=UTF-8")
	public String process(HttpServletRequest request) {
		String replyMsg = "";
		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp");

		if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
			// 消息签名不正确，说明不是公众平台发过来的消息
			logger.debug(String.format("验证signature失败，timestamp：{0}, nonce:{1}, signature{2}", timestamp, nonce,
					signature));
			return "非法请求";
		}

		String echostr = request.getParameter("echostr");
		if (StringUtils.isNotBlank(echostr)) {
			// 说明是一个仅仅用来验证的请求，回显echostr
			return echostr;
		}

		String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw" : request
				.getParameter("encrypt_type");

		if ("raw".equals(encryptType)) {
			// 明文传输的消息
			WxMpXmlMessage inMessage;
			try {
				inMessage = WxMpXmlMessage.fromInputStream(request.getInputStream());
				WxMpXmlOutTextMessage outMsg = WxMpXmlOutMessage.TEXT().content("测试加密消息")
						.fromUser(inMessage.getToUserName()).toUser(inMessage.getFromUserName()).build();
				replyMsg = outMsg.toXml();
			} catch (IOException e) {
				replyMsg = "出错啦！";
				e.printStackTrace();
			}
		} else if ("aes".equals(encryptType)) {
			// 是aes加密的消息
			String msgSignature = request.getParameter("msg_signature");
			WxMpXmlMessage inMessage;
			try {
				inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), config, timestamp, nonce,
						msgSignature);
				WxMpXmlOutTextMessage outMsg = WxMpXmlOutMessage.TEXT().content("测试加密消息")
						.fromUser(inMessage.getToUserName()).toUser(inMessage.getFromUserName()).build();
				replyMsg = outMsg.toXml();
			} catch (IOException e) {
				replyMsg = "出错啦！";
				e.printStackTrace();
			}
		} else {
			replyMsg = "不可识别的加密类型";
		}
		logger.debug("回复的消息：\n{}", replyMsg);
		return replyMsg;
	}

	@RequestMapping(value = "/test")
	public String test() {
		return "success";
	}

}
