package cc.wechat.service.handle;

import cc.wechat.sdk.handle.EventHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.req.BaseEvent;

public class MyEventHandle implements EventHandle {

	@Override
	public BaseMsg handle(BaseEvent event) {
		// TODO Auto-generated method stub
		System.out.println("in handle...");
		String type = event.getMsgType();
		System.out.println(type);
		return null;
	}

	@Override
	public boolean beforeHandle(BaseEvent event) {
		// TODO Auto-generated method stub
		System.out.println("before handle...");
		
		String type = event.getMsgType();
		System.out.println(type);
		return true;
	}

}