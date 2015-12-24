package cc.wechat.service.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wechat.constant.MenuConstant;
import cc.wechat.constant.WechatConsts;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.service.context.bean.LastReqInfo;
import cc.wechat.service.joke.IJokeService;
import cc.wechat.service.joke.bean.Joke;
import cc.wechat.service.session.ISessionService;

@Service
public class ContextService implements IContextService {

	@Autowired
	private ISessionService sessionService;
	@Autowired
	private IJokeService jokeService;

	@Override
	public LastReqInfo getLastReqMsg(String openId) {
		LastReqInfo lastReqInfo = null;
		Object obj = sessionService.get(WechatConsts.SESSION_KEY_LASTREQMSGINFO_PREFIX + openId);
		if(obj != null && obj instanceof LastReqInfo) {
			lastReqInfo = (LastReqInfo) obj;
		}
		return lastReqInfo;
	}

	@Override
	public String getTipsByMenuKey(String menuKey) {
		StringBuilder weatherMenu = new StringBuilder();
		switch (menuKey) {
		case MenuConstant.MENU_CLICK_FUNC_WEATHER:
			weatherMenu.append("1.输入城市名称拼音或汉字\n");
			weatherMenu.append("2.发送你的地理位置");
			break;
		case MenuConstant.MENU_CLICK_FUNC_JOKE:
			Joke j = jokeService.queryOneJoke();
			weatherMenu.append("《")
					   .append(j.getTitle())
					   .append("\n")
					   .append(j.getText());
			break;
		default:
			break;
		}
		return weatherMenu.toString();
	}

}
