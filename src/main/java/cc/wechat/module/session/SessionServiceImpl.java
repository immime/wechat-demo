package cc.wechat.module.session;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cc.wechat.constant.WechatConsts;
import cc.wechat.session.IWechatSession;
import cc.wechat.session.IWechatSessionManager;

@Service
public class SessionServiceImpl implements SessionService {
	
	@Resource(name="standardSessionManager")
	private IWechatSessionManager sessionManager;

	@Override
	public void put(String key, Object value) {
		IWechatSession wxSession = sessionManager.getSession(WechatConsts.SESSION_ID);
		wxSession.setAttribute(key, value);
	}

	@Override
	public Object get(String key) {
		IWechatSession wxSession = sessionManager.getSession(WechatConsts.SESSION_ID);
		return wxSession.getAttribute(key);
	}

}
