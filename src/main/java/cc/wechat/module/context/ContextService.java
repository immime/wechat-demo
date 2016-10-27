package cc.wechat.module.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wechat.constant.MenuConstant;
import cc.wechat.constant.WechatConsts;
import cc.wechat.data.domain.Joke;
import cc.wechat.module.context.bean.LastReqInfo;
import cc.wechat.module.joke.JokeService;
import cc.wechat.module.session.SessionService;

@Service
public class ContextService implements IContextService {

	@Autowired
	private SessionService sessionService;
	@Autowired
	private JokeService jokeService;

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
			Joke j = jokeService.findRandomOne();
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
