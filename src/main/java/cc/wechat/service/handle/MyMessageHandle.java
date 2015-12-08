package cc.wechat.service.handle;

import org.springframework.beans.factory.annotation.Autowired;

import cc.wechat.sdk.handle.MessageHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.req.BaseReqMsg;
import cc.wechat.service.context.IContextService;
import cc.wechat.service.joke.IJokeService;

public class MyMessageHandle implements MessageHandle<BaseReqMsg> {
	
	@Autowired
	private IJokeService jokeService;
	@Autowired
	private	IContextService contextService;
	
	@Override
	public BaseMsg handle(BaseReqMsg message) {
		// TODO Auto-generated method stub
		System.err.println("*****************cc.wechat.service.handle.MyMessageHandle****************");
		
		return null;
	}

	@Override
	public boolean beforeHandle(BaseReqMsg message) {
		// return true
		return true;
	}

}
