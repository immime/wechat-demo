package cc.wechat.service.context;

import cc.wechat.service.context.bean.LastReqInfo;

public interface ContextService {
	
	LastReqInfo getLastReqMsg(String fromUsername);
}
