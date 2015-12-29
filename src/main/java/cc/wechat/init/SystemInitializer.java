package cc.wechat.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import cc.wechat.data.domain.ContextMenu;
import cc.wechat.service.menu.ContextMenuService;

@Component
public class SystemInitializer {
	private static final Logger logger = LoggerFactory.getLogger(SystemInitializer.class);
	
	@Autowired
	private ContextMenuService contextMenuService;
	
	public void initContextMenu() {
		Resource resource = new ClassPathResource("config/menu.json"); 
		ContextMenu menu = null;
		try {
			InputStream inJson = resource.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			menu = mapper.readValue(inJson, ContextMenu.class);
		} catch (IOException e) {
			logger.debug("FAILED to init ContextMenu from json file", e);
			e.printStackTrace();
		}
		Set<ContextMenu> menus = new HashSet<ContextMenu>();
		recursionAdd(menu, menus);
		contextMenuService.save(menus);
		
	}

	private void recursionAdd(ContextMenu menu, Set<ContextMenu> menus) {
		if(menu != null) {
			menus.add(menu);
			Set<ContextMenu> children = menu.getChildren();
			if(CollectionUtils.isNotEmpty(children)) {
				menus.addAll(children);
				for (ContextMenu child : children) {
					recursionAdd(child, menus);
				}
			}
		}
	}
}
