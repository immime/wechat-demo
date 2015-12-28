package cc.wechat.service.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wechat.constant.WechatConsts;
import cc.wechat.service.context.bean.LastReqInfo;
import cc.wechat.service.session.SessionService;

@Service
public class ContextServiceImpl implements ContextService {

	@Autowired
	private SessionService sessionService;

	@Override
	public LastReqInfo getLastReqMsg(String fromUsername) {
		LastReqInfo lastReqInfo = null;
		Object obj = sessionService.get(WechatConsts.SESSION_KEY_LASTREQMSGINFO_PREFIX + fromUsername);
		if(obj != null && obj instanceof LastReqInfo) {
			lastReqInfo = (LastReqInfo) obj;
		}
		return lastReqInfo;
	}

}
