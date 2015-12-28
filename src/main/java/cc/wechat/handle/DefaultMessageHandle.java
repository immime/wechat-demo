package cc.wechat.handle;

import org.springframework.stereotype.Component;

import cc.wechat.sdk.handle.MessageHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.req.BaseReqMsg;

@Component("defaultMessageHandle")
public class DefaultMessageHandle implements MessageHandle<BaseReqMsg> {
	
	@Override
	public BaseMsg handle(BaseReqMsg message) {
		// TODO Auto-generated method stub
		System.err.println("*****************cc.wechat.sdk.handle.MessageHandle<BaseReqMsg>****************");
		
		return null;
	}

	@Override
	public boolean beforeHandle(BaseReqMsg message) {
		// return true
		return true;
	}

}
