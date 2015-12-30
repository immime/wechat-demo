package cc.wechat.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.wechat.constant.MenuConstant;
import cc.wechat.sdk.handle.EventHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.req.BaseReqEvent;
import cc.wechat.sdk.message.req.MenuEvent;
import cc.wechat.sdk.message.req.ReqType;
import cc.wechat.service.context.ContextService;
import cc.wechat.service.joke.JokeService;
import cc.wechat.service.session.SessionService;
import cc.wechat.service.weather.WeatherService;
import cc.wechat.service.weather.bean.CityParam;

/**
 * 自定义菜单点击处理器
 * 
 * <pre>
 * 处理自定义菜单的event消息
 * </pre>
 * 
 * @author weny
 * @datetime 2015年12月29日 下午1:47:26
 */
@Component(value="menuEventHandle")
public class MenuEventHandle implements EventHandle<BaseReqEvent> {

	@Autowired
	public SessionService sessionService;
	@Autowired
	public ContextService contextService;
	@Autowired
	private JokeService jokeService;
	@Autowired
	private WeatherService weatherService;

	@Override
	public BaseMsg handle(BaseReqEvent event) {
		// TODO Auto-generated method stub
		System.out.println("in handle...");
		if (event instanceof MenuEvent) {
			// 只处理点击事件
			String key = ((MenuEvent) event).getEventKey();
			System.err.println("eventKey" + "|" + key);
			if (MenuConstant.MENU_CLICK_FUNC_JOKE.equals(key)) {
				return jokeService.queryOneJokeTextMsg();
			}
			if (MenuConstant.MENU_CLICK_FUNC_WEATHER.equals(key)) {
				CityParam cityInfo = new CityParam();
				cityInfo.setArea("北京");

				return weatherService.queryWeatherNewsMsg(cityInfo);

				// return weatherService.queryWeatherTextMsg(cityInfo);
			}
		}
		return null;
	}

	@Override
	public boolean beforeHandle(BaseReqEvent event) {
		// TODO Auto-generated method stub
		System.out.println("before handle...");

		ReqType type = event.getMsgType();
		System.out.println(type.name());
		return true;
	}

}
