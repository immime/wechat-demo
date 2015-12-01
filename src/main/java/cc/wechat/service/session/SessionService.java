package cc.wechat.service.session;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cc.wechat.config.WechatConsts;
import cc.wechat.session.IWechatSession;
import cc.wechat.session.IWechatSessionManager;

@Service
public class SessionService implements ISessionService {
	
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
