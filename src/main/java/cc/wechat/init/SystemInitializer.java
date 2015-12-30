package cc.wechat.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
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
		if(menu == null) {
			return;
		}
		// 持久化
		Set<ContextMenu> menus = new HashSet<ContextMenu>();
		recursionAdd(menu, menus);
		contextMenuService.save(menus);
		
	}

	private void recursionAdd(final ContextMenu menu, Set<ContextMenu> menus) {
		String code = StringUtils.isNotEmpty(menu.getCode()) ? menu.getCode() : menu.getNode();
		if(!code.endsWith("_")) {
			code = code.concat("_");
		}
		menu.setCode(code);
		Set<ContextMenu> children = menu.getChildren();
		if(CollectionUtils.isNotEmpty(children)) {
			for (ContextMenu child : children) {
				code = menu.getCode().concat(child.getNode());
				child.setCode(code);
				recursionAdd(child, menus);
			}
		}
		
		menus.add(menu);
	}

//	private void recursionAdd2(final ContextMenu menu, Set<ContextMenu> menus) {
//		Set<ContextMenu> children = menu.getChildren();
//		if(CollectionUtils.isNotEmpty(children)) {
//			menus.addAll(children);
//			for (ContextMenu child : children) {
//				recursionAdd2(child, menus);
//			}
//		}
//		menus.add(menu);
//	}
}
