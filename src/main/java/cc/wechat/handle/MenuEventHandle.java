package cc.wechat.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.wechat.constant.MenuConstant;
import cc.wechat.module.context.IContextService;
import cc.wechat.module.joke.JokeService;
import cc.wechat.module.session.SessionService;
import cc.wechat.module.weather.WeatherService;
import cc.wechat.module.weather.vo.CityParam;
import cc.wechat.sdk.handle.EventHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.req.BaseReqEvent;
import cc.wechat.sdk.message.req.MenuEvent;

@Component
public class MenuEventHandle implements EventHandle<BaseReqEvent> {

	@Autowired
	public SessionService sessionService;
	@Autowired
	public	IContextService contextService;
	@Autowired
	private JokeService jokeService;
	@Autowired
	private WeatherService weatherService;
	
	@Override
	public BaseMsg handle(BaseReqEvent event) {
		// TODO Auto-generated method stub
		System.out.println("in handle...");
		if(event instanceof MenuEvent) {
			// 只处理点击事件
			String key = ((MenuEvent) event).getEventKey();
			System.err.println("eventKey" + "|" + key);
			if(MenuConstant.MENU_CLICK_FUNC_JOKE.equals(key)) {
				return jokeService.queryOneJokeTextMsg();
			} 
			if (MenuConstant.MENU_CLICK_FUNC_WEATHER.equals(key)) {
				CityParam cityInfo = new CityParam();
				cityInfo.setArea("北京");
				
				return weatherService.queryWeatherNewsMsg(cityInfo);
				
//				return weatherService.queryWeatherTextMsg(cityInfo);
			}
		}
		return null;
	}

	@Override
	public boolean beforeHandle(BaseReqEvent event) {
		// TODO Auto-generated method stub
		System.out.println("before handle...");
		
		String type = event.getMsgType();
		System.out.println(type);
		return true;
	}

}
