package cc.wechat.interceptor;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cc.wechat.constant.WechatConsts;
import cc.wechat.data.domain.ContextMenu;
import cc.wechat.sdk.message.req.BaseReq;
import cc.wechat.sdk.message.req.ReqType;
import cc.wechat.sdk.message.req.TextReqMsg;
import cc.wechat.service.context.ContextService;
import cc.wechat.service.context.bean.ReqContext;
import cc.wechat.service.menu.ContextMenuService;
import cc.wechat.service.session.SessionService;
import cc.wechat.utils.BeanUtil;
import cc.wechat.web.WechatController;

@Component
public class ContextualInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(ContextualInterceptor.class);

	@Autowired
	private ContextService contextService;
	@Autowired
	private ContextMenuService contextMenuService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("===========HandlerInterceptor1 preHandle");
		
		Object bean = BeanUtil.getAttributeVlaue(handler, "bean");
		if (bean instanceof WechatController) {
			WechatController ctrl = (WechatController) bean;
			String fromUserName = ctrl.getFromUserName();
			ReqType reqMsgType = ctrl.getReqMsgType();
			BaseReq reqMsg = ctrl.getReqMsg();
			ReqContext context = contextService.getContext(fromUserName);
			// reqMsg != null时为非验证请求
			if (reqMsg != null) {
				if (reqMsg instanceof TextReqMsg) {
					TextReqMsg msg = (TextReqMsg) reqMsg;
					String inStr = msg.getContent();
					if (context == null) {
						context = new ReqContext(fromUserName, reqMsgType, reqMsg);
						context.setIsActive(false);
						if (StringUtils.isNotEmpty(inStr)) {
							if ("m".equalsIgnoreCase(inStr) || "h".equalsIgnoreCase(inStr)) {
								String crumb = inStr + "|";
								context.setCrumb(crumb);
								context.setIsActive(true);
								System.err.println(crumb);
							}
						}
					} else {
						String inputCrumb = context.getCrumb().concat(inStr);
						if(contextMenuService.isUnderCotextMenu(inputCrumb)) {
							context.setCrumb(inputCrumb);
							System.err.println(inputCrumb);
						}
					}
					
					contextService.updateContext(context);
					logger.debug("MenuContext put for key:{} & value:{}",
							WechatConsts.SESSION_KEY_LASTREQMSGINFO_PREFIX + fromUserName, context);
				}
			}
		}
		return true;
	}

	// @Override
	// public void postHandle(HttpServletRequest request, HttpServletResponse
	// response, Object handler,
	// ModelAndView modelAndView) throws Exception {
	// System.out.println("===========HandlerInterceptor1 postHandle");
	// }

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		Object bean = BeanUtil.getAttributeVlaue(handler, "bean");
//
//		if (bean instanceof WechatController) {
//			WechatController ctrl = (WechatController) bean;
//			String fromUserName = ctrl.getFromUserName();
//			ReqType reqMsgType = ctrl.getReqMsgType();
//			BaseReq reqMsg = ctrl.getReqMsg();
//			ReqContext context = contextService.getContext(fromUserName);
//			// reqMsg != null时为非验证请求
//			if (reqMsg != null) {
//				if (reqMsg instanceof TextReqMsg) {
//					TextReqMsg msg = (TextReqMsg) reqMsg;
//					String inStr = msg.getContent();
//					String crumb = null;
//					if (context == null) {
//						context = new ReqContext(fromUserName, reqMsgType, reqMsg);
//						context.setIsActive(false);
//						if (StringUtils.isNotEmpty(inStr)) {
//							if ("m".equalsIgnoreCase(inStr) || "h".equalsIgnoreCase(inStr)) {
//								crumb = inStr + "|";
//								context.setIsActive(true);
//							}
//						}
//					} else {
//						if(isValidInput(context, inStr)) {
//							crumb = context.getCrumb().concat(inStr).concat("|");	
//						}
//					}
//					context.setCrumb(crumb);
//					contextService.updateContext(context);
//					logger.debug("MenuContext put for key:{} & value:{}",
//							WechatConsts.SESSION_KEY_LASTREQMSGINFO_PREFIX + fromUserName, context);
//				}
//			}
//		}
	}

}
