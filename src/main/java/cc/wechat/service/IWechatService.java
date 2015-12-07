package cc.wechat.service;

import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.req.TextReqMsg;


public interface IWechatService {
	BaseMsg processTextMsg(TextReqMsg msg);
}
