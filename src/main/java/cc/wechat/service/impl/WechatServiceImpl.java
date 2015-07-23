package cc.wechat.service.impl;

import org.springframework.stereotype.Service;

import cc.wechat.service.IWechatService;

@Service
public class WechatServiceImpl implements IWechatService {
	
	@Override
	public boolean checkSignature(String timestamp, String nonce, String signature) {
		// TODO
		return false;
	}


}
