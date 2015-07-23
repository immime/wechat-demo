package cc.wechat.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weixin")
public class WechatController {
	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

	@RequestMapping(produces = "application/xml;charset=UTF-8")
	public String process(HttpServletRequest request) {
		return null;
	}

	@RequestMapping(value = "/test")
	public String test() {
		return "success";
	}

}
