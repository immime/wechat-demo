package cc.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cc.wechat.sdk.api.TokenStore;
import cc.wechat.service.IWechatService;
import cc.wechat.utils.SignatureUtil;
import cc.wechat.web.WechatController;

@Service
public class WechatServiceImpl implements IWechatService {
	private static final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

	
	@Override
	public boolean checkSignature(String timestamp, String nonce, String signature, String echostr) {
		//首次请求申请验证,返回echostr
        if(echostr!=null){
            return true;
        }

        //验证请求签名
        if(!signature.equals(SignatureUtil.generateEventMessageSignature(TokenStore.get(), timestamp, nonce))){
        	logger.debug("The request signature is invalid");
            return false;
        }
		return false;
	}


}
