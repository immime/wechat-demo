package cc.wechat.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wechat.data.domain.ContextMenu;

@Service
public class MenuNavigateImpl implements MenuNavigate {
	
	@Autowired
	private ContextMenuService contextMenuService;

	@Override
	public ContextMenu current(String currentCrumb) {
		return contextMenuService.findByCode(currentCrumb);
	}

	@Override
	public ContextMenu next(String currentCrumb, String nextNode) {
		String targetCode = currentCrumb.concat(nextNode);
		return contextMenuService.findByCode(targetCode);
	}

	@Override
	public ContextMenu previous(String currentCrumb) {
		ContextMenu current = contextMenuService.findByCode(currentCrumb);
		if(current != null && current.getParent() != null) {
			return current.getParent();
		}
		return null;
	}

	@Override
	public ContextMenu direct(String targetCrumb) {
		return contextMenuService.findByCode(targetCrumb);
	}

}
