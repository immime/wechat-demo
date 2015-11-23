package cc.wechat.service;


public interface IWechatService {
	/**
	 * 验证微信服务器指纹
	 * @author weny
	 * @date 2015年7月22日 下午1:38:19	
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @param echostr
	 * @return
	 */
	boolean checkSignature(String timestamp, String nonce, String signature, String echostr);
	

}
