package cc.wechat.service.menu;

import cc.wechat.data.domain.ContextMenu;

public interface ContextMenuService {
	
	void save(ContextMenu entity);
	void save(Iterable<ContextMenu> entities);
	Iterable<ContextMenu> findAll();
	Iterable<ContextMenu> findChildren(String code);
	ContextMenu findByCode(String code);
	boolean exists(String code);
	boolean isUnderCotextMenu(String input);
}
