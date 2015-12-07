package cc.wechat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.TextReqMsg;
import cc.wechat.service.context.IContextService;
import cc.wechat.service.context.bean.WeixinContext;
import cc.wechat.service.session.ISessionService;

@Service
public class WechatService implements IWechatService {
	private static final Logger logger = LoggerFactory.getLogger(WechatService.class);
	@Autowired
	private ISessionService sessionService;
	@Autowired
	private IContextService contextService;
	
	@Override
	public BaseMsg processTextMsg(TextReqMsg msg) {
		String openId = msg.getFromUserName();
		WeixinContext ctx = contextService.getContext(openId);
		
		if(ctx == null) {
			
			
			return new TextMsg(
					String.format(
							"欢迎光临！\n%s",
							"<a href='http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzA3NDMyNzc5Mg==#wechat_webview_type=1&wechat_redirect'>查看历史消息</a>"));

		}
		WeixinContext newCtx = new WeixinContext();
		contextService.setContext(openId, newCtx);
		return null;

	}

}
