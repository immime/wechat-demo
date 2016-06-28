package cc.wechat.handle;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cc.wechat.sdk.handle.MessageHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.BaseReqMsg;
import cc.wechat.sdk.message.req.TextReqMsg;

@Component(value = "defaultMessageHandle")
public class DefaultMessageHandle implements MessageHandle<BaseReqMsg> {
	
	@Override
	public BaseMsg handle(BaseReqMsg message) {

		System.out.println(">>>>>>>>>>>>>>>defaultMessageHandle on work!");

		String msgType = message.getMsgType();
		TextReqMsg reqMsg = (TextReqMsg) message;
		String content = reqMsg.getContent();

		if (hashieldedInfo(content)) {
			
		}

		if (hasHelpInfo(content)) {
			return new TextMsg("帮助信息");
		}

		return null;
	}

	/**
	 * 显示帮助菜单
	 * 
	 * @param message
	 * @return
	 */
	private boolean hasHelpInfo(String message) {
		String[] HELP_STRS = new String[] {"?", "？", "help", "帮助"};
		for (String s : HELP_STRS) {
			if(StringUtils.containsOnly(message, s)) return true;
		}
		return false;
	}

	/**
	 * 过滤敏感词汇
	 * 
	 * @param message
	 * @return
	 */
	private boolean hashieldedInfo(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean beforeHandle(BaseReqMsg message) {
		// return true
		return true;
	}

}
