package cc.wechat.service.menu;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cc.wechat.data.domain.ContextMenu;
import cc.wechat.data.repository.ContextMenuRepository;

@Service
public class ContextMenuServiceImpl implements ContextMenuService {

	@Autowired
	private ContextMenuRepository contextMenuRepository;
	
	@Override
	public Iterable<ContextMenu> findAll() {
		return contextMenuRepository.findAll();
	}
	
	@Override
	public ContextMenu findByCode(String code) {
		// TODO Auto-generated method stub
		Assert.notNull(code, "Parameter 'code' must not be null");
		ContextMenu menu = contextMenuRepository.findByCode(code);
		return menu;
	}
	
	@Override
	public Iterable<ContextMenu> findChildren(String code) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void save(Iterable<ContextMenu> entities) {
		contextMenuRepository.save(entities);
	}

	@Override
	public boolean isUnderCotextMenu(String input) {
		Iterable<ContextMenu> menus = this.findAll();
		Iterator<ContextMenu> iterator = menus.iterator();
		while (iterator.hasNext()) {
			ContextMenu m = (ContextMenu) iterator.next();
			if(m.getCode().equals(input)) {
				return true;
			}
		}
		return false;
	}


}
