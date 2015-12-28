package cc.wechat.service.turing;

import cc.wechat.sdk.message.TextMsg;

public interface TuringService {
	
	TextMsg queryRobotMsg(String inputMsg);
	
}
