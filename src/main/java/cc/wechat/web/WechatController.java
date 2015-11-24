package cc.wechat.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/")
public class WechatController {
	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

	@Autowired
	private IWechatService service;
	@Autowired
	private ISessionService sessionService;

	// 重复通知过滤 时效60秒
	private static ExpireSet<String> expireSet = new ExpireSet<String>(60);

	@RequestMapping(produces = "application/xml;charset=UTF-8")
	public String process(HttpServletRequest request, HttpServletResponse response) {
		ServletInputStream inputStream = null;
		ServletOutputStream outputStream = null;
		try {
			inputStream = request.getInputStream();
			outputStream = response.getOutputStream();
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
			outputStreamWrite(outputStream, echostr);
			return null;
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
			xmlTextMessage.outputStreamWrite(outputStream);
			return null;
		}
		outputStreamWrite(outputStream, "");
		return echostr;
	}

	/**
	 * 数据流输出
	 * 
	 * @param outputStream
	 * @param text
	 * @return
	 */
	private boolean outputStreamWrite(OutputStream outputStream, String text) {
		try {
			outputStream.write(text.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[] { TokenStore.get(), timestamp, nonce };
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		content = null;
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	// 将字节转换为十六进制字符串
	private static String byteToHexStr(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];

		String s = new String(ob);
		return s;
	}

	// 将字节数组转换为十六进制字符串
	private static String byteToStr(byte[] bytearray) {
		String strDigest = "";
		for (int i = 0; i < bytearray.length; i++) {
			strDigest += byteToHexStr(bytearray[i]);
		}
		return strDigest;
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
