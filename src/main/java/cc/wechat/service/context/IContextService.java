package cc.wechat.service.context;

import cc.wechat.service.context.bean.LastReqInfo;

public interface IContextService {
	
	LastReqInfo getLastReqMsg(String openId);
	
	String getTipsByMenuKey(String menuKey);
}
