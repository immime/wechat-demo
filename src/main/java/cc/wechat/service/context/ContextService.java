package cc.wechat.service.context;

import cc.wechat.service.context.bean.ReqContext;

public interface ContextService {
	
	ReqContext getContext(String fromUsername);
	void updateContext(ReqContext reqCotext);
	String getWelcomeText();
	String getHelpText();
	String getMenuText();
}
