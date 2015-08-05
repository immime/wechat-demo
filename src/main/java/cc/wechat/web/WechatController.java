package cc.wechat.web;

import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.EnumerationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.wechat.sdk.session.IWechatSession;
import cc.wechat.sdk.session.IWechatSessionManager;

@RestController
@RequestMapping("/weixin")
public class WechatController {
	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
	
	@Resource(name="standardSessionManager")
	private IWechatSessionManager sessionManager;
	
	@RequestMapping(produces = "application/xml;charset=UTF-8")
	public String process(HttpServletRequest request) {
		return null;
	}

	/*Session test*/
	
	@RequestMapping(value = "/test/{key}/{value}")
	public String test(@PathVariable("key") String key, @PathVariable("value") String value) {
		IWechatSession wxSession = sessionManager.getSession("openId");
		wxSession.setAttribute(key, value);
		return "success";
	}
	
	@RequestMapping(value = "/test1")
	public List<String> test1() {
		IWechatSession wxSession = sessionManager.getSession("openId");
		Enumeration<String> keys = wxSession.getAttributeNames();
		List<String> strs = EnumerationUtils.toList(keys);
		return strs;
	}

}
