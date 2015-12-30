package cc.wechat.service.context;

import cc.wechat.data.domain.ContextMenu;
import cc.wechat.service.context.bean.ReqContext;

public interface ContextService {
	
	ReqContext getContext(String fromUsername);
	void updateContext(ReqContext reqCotext);
	String getWelcomeText();
	String getMenuText(ContextMenu menu);
}
