package cc.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cc.wechat.service.IWechatService;

@Service
public class WechatServiceImpl implements IWechatService {
	private static final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

	
	@Override
	public boolean checkSignature(String timestamp, String nonce, String signature, String echostr) {
		
		return false;
	}


}
