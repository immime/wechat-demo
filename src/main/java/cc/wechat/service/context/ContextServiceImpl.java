package cc.wechat.service.context;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wechat.constant.WechatConsts;
import cc.wechat.data.domain.ContextMenu;
import cc.wechat.service.context.bean.ReqContext;
import cc.wechat.service.session.SessionService;

@Service
public class ContextServiceImpl implements ContextService {

	@Autowired
	private SessionService sessionService;

	@Override
	public ReqContext getContext(String fromUsername) {
		ReqContext lastReqInfo = null;
		Object obj = sessionService.get(WechatConsts.SESSION_KEY_LASTREQMSGINFO_PREFIX + fromUsername);
		if(obj != null && obj instanceof ReqContext) {
			lastReqInfo = (ReqContext) obj;
		}
		return lastReqInfo;
	}

	@Override
	public void updateContext(ReqContext lastReqInfo) {
		sessionService.put(WechatConsts.SESSION_KEY_LASTREQMSGINFO_PREFIX + lastReqInfo.getFromUserName(), lastReqInfo);
	}

	@Override
	public String getWelcomeText() {
		StringBuilder sb = new StringBuilder("========欢迎=======\n");
		sb.append("1.回复m显示菜单\n")
		.append("2.回复h显示帮助\n");
		return sb.toString();
	}

	@Override
	public String getMenuText(ContextMenu menu) {
		StringBuilder sb = new StringBuilder("========").append(menu.getDisplayName()).append("=======\n");
		Set<ContextMenu> children = menu.getChildren();
		for (ContextMenu m : children) {
			sb.append(m.getNode()).append(".").append(m.getDisplayName()).append("\n");
		}
		sb.append("(回复对应的序号)");
		return sb.toString();
	}

}
