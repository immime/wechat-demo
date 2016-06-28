package cc.wechat.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.wechat.data.domain.Joke;
import cc.wechat.sdk.handle.MessageHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.BaseReqMsg;
import cc.wechat.service.joke.JokeService;

@Component(value = "robotMessageHandle")
public class RobotMessageHandle implements MessageHandle<BaseReqMsg> {

	@Autowired
	private JokeService jokeService;

	@Override
	public BaseMsg handle(BaseReqMsg message) {
		System.out.println(">>>>>>>>>>>>>>>defaultMessageHandle handle!");

		String resp = "无可奉告！";
		Joke joke = jokeService.findRandomOne();
		if (joke != null) {
			resp = joke.getText();
		}
		return new TextMsg(resp);
	}

	@Override
	public boolean beforeHandle(BaseReqMsg message) {
		System.out.println(">>>>>>>>>>>>>>>defaultMessageHandle beforeHandle!");
		// return true
		return true;
	}

}
