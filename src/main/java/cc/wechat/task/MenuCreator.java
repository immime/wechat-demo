package cc.wechat.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cc.wechat.config.ApiConfigCenter;
import cc.wechat.data.domain.WxMenu;
import cc.wechat.module.menu.MenuService;
import cc.wechat.sdk.api.MenuAPI;
import cc.wechat.sdk.api.config.ApiConfig;
import cc.wechat.sdk.api.entity.Menu;
import cc.wechat.sdk.api.entity.MenuButton;
import cc.wechat.sdk.api.enums.ResultType;
import cc.wechat.sdk.exception.WeixinException;

@Component
public class MenuCreator {
	private static final Logger logger = LoggerFactory.getLogger(MenuCreator.class);
	@Autowired
	private ApiConfigCenter apiConfigCenter;
	@Autowired
	private MenuService menuService;
	
	/**
	 * 每隔2小时（115分钟）刷新一次
	 * @return
	 * @throws FailedToGetTokenException 
	 */
	@Scheduled(fixedRate = 1000 * 60 * 115)
	private void refresh() throws WeixinException {
		
        ApiConfig config = apiConfigCenter.getConfig();
        
        List<MenuButton> mainMenus = new ArrayList<MenuButton>();
        
        Iterable<WxMenu> menus = menuService.findAll();
        
        for (WxMenu menu : menus) {
        	MenuButton mm = new MenuButton();
        	
            mm.setType(menu.getType());
            mm.setKey(menu.getMenuKey());
            mm.setName(menu.getName());
            
        	List<WxMenu> subs = menu.getSubMenu();
        	if (CollectionUtils.isNotEmpty(subs)) {
        		List<MenuButton> list = new ArrayList<MenuButton>();
        		for (WxMenu sm : subs) {
        			MenuButton sub = new MenuButton();
        			sub.setName(sm.getName());
        	        sub.setKey(sm.getMenuKey());
        	        sub.setType(sm.getType());
        	        list.add(sub);
				}
        		mm.setSubButton(list);
        		mainMenus.add(mm);
        	}
		}
        
        Menu request = new Menu();
        //将主菜单加入请求对象
        request.setButton(mainMenus);
        logger.debug(request.toJsonString());
        //创建菜单
        MenuAPI menuAPI = new MenuAPI(config);
        ResultType resultType = menuAPI.createMenu(request);
        logger.debug(resultType.toString());
		
	}
}
