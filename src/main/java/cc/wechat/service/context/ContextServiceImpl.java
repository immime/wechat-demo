package cc.wechat.service.context;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wechat.constant.WechatConsts;
import cc.wechat.data.domain.ContextMenu;
import cc.wechat.service.context.bean.ReqContext;
import cc.wechat.service.menu.ContextMenuService;
import cc.wechat.service.session.SessionService;

@Service
public class ContextServiceImpl implements ContextService {

	@Autowired
	private SessionService sessionService;
	@Autowired
	private ContextMenuService contextMenuService;

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
	public String getHelpText() {
		StringBuilder sb = new StringBuilder("========帮助=======\n");
		sb.append("1.回复m显示菜单\n")
		.append("2.回复h显示帮助\n")
		.append("3.回复q退出.n");
		return sb.toString();
	}

	@Override
	public String getMenuText() {
		StringBuilder sb = new StringBuilder("========菜单=======\n");
		Iterable<ContextMenu> it = contextMenuService.findAll();
		Iterator<ContextMenu> menus = it.iterator();
		while(menus.hasNext()) {
			ContextMenu m = menus.next();
			sb.append(m.getCode()).append("-").append(m.getDisplayName()).append("\n");
		}
		sb.append("===回复对应的序号===");
		return sb.toString();
	}

}
