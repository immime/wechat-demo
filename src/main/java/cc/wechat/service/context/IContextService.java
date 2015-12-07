package cc.wechat.service.context;

import cc.wechat.service.context.bean.WeixinContext;

public interface IContextService {
	void setContext(String openId, WeixinContext ctx);
	WeixinContext getContext(String openId);
	void invalid(String openId);
}
