package cc.wechat.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
	/**
	 * 每隔2小时（115分钟）刷新一次
	 * @return
	 * @throws FailedToGetTokenException 
	 */
	@Scheduled(fixedRate = 1000 * 60 * 115)
	private static void refresh() throws WeixinException {
		String appid = "wxe6626fc25736c77e";
        String secret = "c5ea13a94c08a1ed07fc4eaeb6ca913b";
        ApiConfig config = new ApiConfig(appid, secret);
        
        Menu request = new Menu();
        //准备一级主菜单
        MenuButton main1 = new MenuButton();
        main1.setType(MenuType.CLICK);
        main1.setKey("MENU_CLICK_FUNC");
        main1.setName("功能");
        //准备子菜单
        MenuButton sub1 = new MenuButton();
        sub1.setKey("MENU_CLICK_AUTH");
        sub1.setName("授权");
        sub1.setType(MenuType.VIEW);
        sub1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxafb7b8f9457b5d50&redirect_uri=http://121.40.140.41/erhuluanzi/app/testGet&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");
        MenuButton sub2 = new MenuButton();
        sub2.setKey("MENU_CLICK_JOKE");
        sub2.setName("讲笑话");
        sub2.setType(MenuType.CLICK);


        List<MenuButton> list = new ArrayList<MenuButton>();
        list.add(sub1);
        list.add(sub2);
        //将子菜单放入主菜单里
        main1.setSubButton(list);

        List<MenuButton> mainList = new ArrayList<MenuButton>();
        mainList.add(main1);
        //将主菜单加入请求对象
        request.setButton(mainList);
        logger.debug(request.toJsonString());
        //创建菜单
        MenuAPI menuAPI = new MenuAPI(config);
        ResultType resultType = menuAPI.createMenu(request);
        logger.debug(resultType.toString());
		
	}
}
