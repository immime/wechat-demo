package cc.wechat.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wechat.data.domain.ContextMenu;

@Service
public class MenuNavigateImpl implements MenuNavigate {
	
	@Autowired
	private ContextMenuService contextMenuService;

	@Override
	public ContextMenu current(String currentCode) {
		return contextMenuService.findByCode(currentCode);
	}

	@Override
	public ContextMenu next(String currentCode, String nextNode) {
		String targetCode = currentCode.concat(nextNode);
		return contextMenuService.findByCode(targetCode);
	}

	@Override
	public ContextMenu previous(String currentCode) {
		ContextMenu current = contextMenuService.findByCode(currentCode);
		if(current != null && current.getParent() != null) {
			return current.getParent();
		}
		return null;
	}

	@Override
	public ContextMenu direct(String targetCode) {
		return contextMenuService.findByCode(targetCode);
	}

}
