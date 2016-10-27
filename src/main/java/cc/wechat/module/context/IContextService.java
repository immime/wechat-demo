package cc.wechat.module.context;

import cc.wechat.module.context.bean.LastReqInfo;

public interface IContextService {
	
	LastReqInfo getLastReqMsg(String openId);
	
	String getTipsByMenuKey(String menuKey);
}
