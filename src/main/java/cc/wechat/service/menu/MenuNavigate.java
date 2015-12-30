package cc.wechat.service.menu;

import cc.wechat.data.domain.ContextMenu;

public interface MenuNavigate {
	
	ContextMenu current(String currentCrumb);
	ContextMenu next(String currentCrumb, String nextNode);
	ContextMenu previous(String currentCrumb);
	ContextMenu direct(String targetCrumb);
	
}
