package cc.wechat.service.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.wechat.constant.MenuConstant;
import cc.wechat.sdk.handle.EventHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.BaseReqEvent;
import cc.wechat.sdk.message.req.MenuEvent;
import cc.wechat.service.context.IContextService;
import cc.wechat.service.joke.IJokeService;
import cc.wechat.service.joke.bean.Joke;
import cc.wechat.service.session.ISessionService;
import cc.wechat.service.weather.IWeatherService;
import cc.wechat.service.weather.bean.CityParam;

@Component
public class MenuEventHandle implements EventHandle<BaseReqEvent> {

	@Autowired
	public ISessionService sessionService;
	@Autowired
	public	IContextService contextService;
	@Autowired
	private IJokeService jokeService;
	@Autowired
	private IWeatherService weatherService;
	
	@Override
	public BaseMsg handle(BaseReqEvent event) {
		// TODO Auto-generated method stub
		System.out.println("in handle...");
		if(event instanceof MenuEvent) {
			// 只处理点击事件
			String key = ((MenuEvent) event).getEventKey();
			System.err.println("eventKey" + "|" + key);
			if(MenuConstant.MENU_CLICK_FUNC_JOKE.equals(key)) {
				
				Joke j = jokeService.getRandomJoke();
				TextMsg msg = new TextMsg();
				msg.add("《").add(j.getTitle()).add("》").add("\n").add(j.getText());
				return msg;
			} 
			if (MenuConstant.MENU_CLICK_FUNC_WEATHER.equals(key)) {
				TextMsg msg = new TextMsg();
				CityParam cityInfo = new CityParam();
				cityInfo.setArea("北京");;
				msg.add(weatherService.queryWeatherByCityInfo(cityInfo).toString());
				return msg;
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
