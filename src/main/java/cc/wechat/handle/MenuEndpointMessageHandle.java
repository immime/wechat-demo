package cc.wechat.handle;

import org.springframework.stereotype.Component;

import cc.wechat.sdk.handle.MessageHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.req.BaseReqMsg;

@Component(value="menuTextMessageHandle")
public class MenuEndpointMessageHandle implements MessageHandle<BaseReqMsg> {

	@Override
	public BaseMsg handle(BaseReqMsg message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean beforeHandle(BaseReqMsg message) {
		// TODO Auto-generated method stub
		return false;
	}

}
