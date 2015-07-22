package cc.wechat.service.impl;

import org.springframework.stereotype.Service;

import cc.wechat.config.WeixinManager;
import cc.wechat.sdk.mp.api.WxMpService;
import cc.wechat.sdk.mp.bean.WxMpXmlMessage;
import cc.wechat.service.IWeixinMsgService;

@Service
public class WeixinMsgServiceImpl implements IWeixinMsgService {
	
	private final static WxMpService wxMpService = WeixinManager.getService();

	@Override
	public boolean checkSignature(String timestamp, String nonce, String signature) {
		return wxMpService.checkSignature(timestamp, nonce, signature);
	}

	@Override
	public String getOutMsgXml(WxMpXmlMessage inMessage) {
		// TODO Auto-generated method stub
		return null;
	}

}
