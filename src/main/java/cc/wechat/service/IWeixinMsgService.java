package cc.wechat.service;

import cc.wechat.sdk.mp.bean.WxMpXmlMessage;

public interface IWeixinMsgService {
	/**
	 * 验证微信服务器指纹
	 * @author weny
	 * @date 2015年7月22日 下午1:38:19	
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @return
	 */
	boolean checkSignature(String timestamp, String nonce, String signature);
	
	/**
	 * 处理接收到的消息，返回xml格式消息
	 * @author weny
	 * @date 2015年7月22日 下午1:40:38	
	 * @param inMessage
	 * @return
	 */
	String getOutMsgXml(WxMpXmlMessage inMessage);

}
