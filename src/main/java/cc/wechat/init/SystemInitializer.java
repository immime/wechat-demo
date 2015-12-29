package cc.wechat.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
		try {
			InputStream inJson = resource.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			List<ContextMenu> entities = mapper.readValue(inJson, mapper .getTypeFactory().constructCollectionType(List.class, ContextMenu.class));
			if(CollectionUtils.isNotEmpty(entities)) {
				contextMenuService.save(entities);
			}
		} catch (IOException e) {
			logger.debug("FAILED to init ContextMenu from json file", e);
			e.printStackTrace();
		}
	}
}
