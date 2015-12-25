package cc.wechat.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cc.wechat.config.ApiConfigCenter;
import cc.wechat.constant.MenuConstant;
import cc.wechat.sdk.api.MenuAPI;
import cc.wechat.sdk.api.config.ApiConfig;
import cc.wechat.sdk.api.entity.Menu;
import cc.wechat.sdk.api.entity.MenuButton;
import cc.wechat.sdk.api.enums.MenuType;
import cc.wechat.sdk.api.enums.ResultType;
import cc.wechat.sdk.exception.WeixinException;

@Component
public class MenuCreator {
	private static final Logger logger = LoggerFactory.getLogger(MenuCreator.class);
	@Autowired
	private ApiConfigCenter apiConfigCenter;
	/**
	 * 每隔2小时（115分钟）刷新一次
	 * @return
	 * @throws FailedToGetTokenException 
	 */
	@Scheduled(fixedRate = 1000 * 60 * 115)
	private void refresh() throws WeixinException {
		
        ApiConfig config = apiConfigCenter.getConfig();
        
        Menu request = new Menu();
        //准备一级主菜单
        MenuButton main1 = new MenuButton();
        main1.setType(MenuType.CLICK);
        main1.setKey(MenuConstant.MENU_CLICK_FUNC);
        main1.setName("功能");
        
        //准备子菜单
        MenuButton sub1 = new MenuButton();
        sub1.setKey(MenuConstant.MENU_CLICK_FUNC_WEATHER);
        sub1.setName("查天气");
        sub1.setType(MenuType.CLICK);
        
        MenuButton sub2 = new MenuButton();
        sub2.setKey(MenuConstant.MENU_CLICK_FUNC_JOKE);
        sub2.setName("讲笑话");
        sub2.setType(MenuType.CLICK);
        
        List<MenuButton> list = new ArrayList<MenuButton>();
        list.add(sub1);
        list.add(sub2);
        //将子菜单放入主菜单里
        main1.setSubButton(list);
        
        //准备一级主菜单
        MenuButton main2 = new MenuButton();
        main2.setType(MenuType.VIEW);
        main2.setKey(MenuConstant.MENU_CLICK_PAGE);
        main2.setName("JS-demo页面");
        main2.setUrl("http://qiqi.localtunnel.me/views/demo.html");
        

        List<MenuButton> mainList = new ArrayList<MenuButton>();
        mainList.add(main1);
        mainList.add(main2);
        //将主菜单加入请求对象
        request.setButton(mainList);
        logger.debug(request.toJsonString());
        //创建菜单
        MenuAPI menuAPI = new MenuAPI(config);
        ResultType resultType = menuAPI.createMenu(request);
        logger.debug(resultType.toString());
		
	}
}
