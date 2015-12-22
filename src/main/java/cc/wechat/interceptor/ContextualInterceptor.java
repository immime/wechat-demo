package cc.wechat.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cc.wechat.config.WechatConsts;
import cc.wechat.sdk.message.req.BaseReq;
import cc.wechat.service.context.IContextService;
import cc.wechat.service.context.bean.LastReqInfo;
import cc.wechat.service.session.ISessionService;
import cc.wechat.utils.BeanUtil;
import cc.wechat.web.WechatController;

@Component
public class ContextualInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(ContextualInterceptor.class);

	@Autowired
	private ISessionService sessionService;

	// @Override
	// public boolean preHandle(HttpServletRequest request, HttpServletResponse
	// response, Object handler) throws Exception {
	// System.out.println("===========HandlerInterceptor1 preHandle");
	// return true;
	// }
	//
	// @Override
	// public void postHandle(HttpServletRequest request, HttpServletResponse
	// response, Object handler,
	// ModelAndView modelAndView) throws Exception {
	// System.out.println("===========HandlerInterceptor1 postHandle");
	// }

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Object bean = BeanUtil.getAttributeVlaue(handler, "bean");

		if (bean instanceof WechatController) {
			WechatController ctrl = (WechatController) bean;
			String fromUserName = ctrl.getFromUserName();
			String reqMsgType = ctrl.getReqMsgType();
			BaseReq reqMsg = ctrl.getReqMsg();
			
			LastReqInfo lastMsgInfo = new LastReqInfo(fromUserName, reqMsgType, reqMsg);

			sessionService.put(WechatConsts.SESSION_KEY_LASTREQMSGINFO_PREFIX + fromUserName, lastMsgInfo);

			logger.debug("session put for key:{} & value:{}",
					WechatConsts.SESSION_KEY_LASTREQMSGINFO_PREFIX + fromUserName, lastMsgInfo);
		}
	}
}
