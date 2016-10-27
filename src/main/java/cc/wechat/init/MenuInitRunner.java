package cc.wechat.init;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cc.wechat.constant.MenuConstant;
import cc.wechat.data.domain.WxMenu;
import cc.wechat.module.menu.MenuService;
import cc.wechat.sdk.api.enums.MenuType;

@Component
@Order(value=1)
public class MenuInitRunner implements CommandLineRunner {
	
	@Autowired
	private MenuService menuService;

	@Override
	public void run(String... args) throws Exception {
		long count = menuService.count();
		if(count == 0) {
			List<WxMenu> menus = prepareMenuData();
			menuService.save(menus);
		}
	}

	private List<WxMenu> prepareMenuData() {
		
		List<WxMenu> mainList = new ArrayList<WxMenu>();
		//准备一级主菜单
		WxMenu main1 = new WxMenu();
		main1.setType(MenuType.CLICK);
		main1.setMenuKey(MenuConstant.MENU_CLICK_FUNC);
		main1.setName("功能");
		
		//准备子菜单
		WxMenu main1Sub1 = new WxMenu();
		main1Sub1.setMenuKey(MenuConstant.MENU_CLICK_FUNC_WEATHER);
		main1Sub1.setName("查文章");
		main1Sub1.setType(MenuType.CLICK);
		
		WxMenu main1Sub2 = new WxMenu();
		main1Sub2.setMenuKey(MenuConstant.MENU_CLICK_FUNC_JOKE);
		main1Sub2.setName("讲笑话");
		main1Sub2.setType(MenuType.CLICK);
		
		//将子菜单放入主菜单里
		main1.getSubMenu().add(main1Sub1);
		main1.getSubMenu().add(main1Sub2);
		
		//准备一级主菜单
		WxMenu main2 = new WxMenu();
		main2.setType(MenuType.CLICK);
		main2.setMenuKey(MenuConstant.MENU_CLICK_PAGE);
		main2.setName("JS-demo页面");
		
		//准备子菜单
		WxMenu main2Sub1 = new WxMenu();
		main2Sub1.setMenuKey(MenuConstant.MENU_CLICK_PAGE_DEMO);
		main2Sub1.setName("JS-demo页面");
		main2Sub1.setType(MenuType.VIEW);
		main2Sub1.setUrl("http://wechat.liekkas.me/views/demo.html");
		
		WxMenu main2Sub2 = new WxMenu();
		main2Sub2.setMenuKey(MenuConstant.MENU_CLICK_PAGE_DEVICE);
		main2Sub2.setName("智能设备");
		main2Sub2.setType(MenuType.VIEW);
		main2Sub2.setUrl("http://wechat.liekkas.me/views/device_index.html");
		
		//将子菜单放入主菜单里
		main2.getSubMenu().add(main2Sub1);
		main2.getSubMenu().add(main2Sub2);
		
		mainList.add(main1);
		mainList.add(main2);
		
		return mainList;
	}

}
