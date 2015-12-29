package cc.wechat.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.wechat.sdk.handle.MessageHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.BaseReqMsg;
import cc.wechat.sdk.message.req.TextReqMsg;
import cc.wechat.service.turing.TuringService;

/**
 * 聊天机器人处理器
 * 
 * <pre>
 * 机器人回复消息
 * </pre>
 * 
 * @author weny
 * @datetime 2015年12月29日 下午1:49:53
 */
@Component("robotMessageHandle")
public class RobotMessageHandle implements MessageHandle<BaseReqMsg> {

	@Autowired
	private TuringService turingService;

	@Override
	public BaseMsg handle(BaseReqMsg message) {
		// 处理文本消息请求，未处理返回null待下一个handle处理
		TextMsg msg = null;
		if (message instanceof TextReqMsg) {
			msg = turingService.queryRobotMsg(((TextReqMsg) message).getContent());
		}
		return msg;
	}

	@Override
	public boolean beforeHandle(BaseReqMsg message) {
		// return true
		return true;
	}

}
