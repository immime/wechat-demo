package cc.wechat.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.wechat.service.session.ISessionService;

@RestController
@RequestMapping("/weixin")
public class WechatController {
	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
	
	@Autowired
	private ISessionService sessionService;
	
	@RequestMapping(produces = "application/xml;charset=UTF-8")
	public String process(HttpServletRequest request) {
		return null;
	}

	/*Session test*/
	
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
