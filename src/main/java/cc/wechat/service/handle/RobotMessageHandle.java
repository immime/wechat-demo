package cc.wechat.service.handle;

import cc.wechat.sdk.handle.MessageHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.req.BaseReqMsg;

public class RobotMessageHandle implements MessageHandle<BaseReqMsg> {

	@Override
	public BaseMsg handle(BaseReqMsg message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean beforeHandle(BaseReqMsg message) {
		// return true
		return true;
	}

}
