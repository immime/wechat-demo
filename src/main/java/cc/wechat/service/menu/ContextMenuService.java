package cc.wechat.service.menu;

import cc.wechat.data.domain.ContextMenu;

public interface ContextMenuService {
	
	void save(Iterable<ContextMenu> entities);
	Iterable<ContextMenu> findAll();
	Iterable<ContextMenu> findChildren(String code);
	ContextMenu findByCode(String code);
	boolean isUnderCotextMenu(String input);
}
