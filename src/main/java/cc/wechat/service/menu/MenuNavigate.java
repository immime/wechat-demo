package cc.wechat.service.menu;

import cc.wechat.data.domain.ContextMenu;

public interface MenuNavigate {
	
	ContextMenu current(String currentCode);
	ContextMenu next(String currentCode, String nextNode);
	ContextMenu previous(String currentCode);
	ContextMenu direct(String targetCode);
	
}
