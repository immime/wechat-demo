package cc.wechat.sdk.api;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cc.wechat.sdk.bean.msg.in.Token;
import cc.wechat.sdk.exception.FailedToGetTokenException;

/**
 * 处理Token
 * @author weny
 * @datetime 2015年7月22日 下午4:21:11
 */
@Component
public class TokenStore {
	private static final Logger logger = LoggerFactory.getLogger(TokenStore.class);
	private static String token = "";
	
	/**
	 * 获取token
	 * @return
	 * @throws FailedToGetTokenException 
	 */
	public static String get() throws FailedToGetTokenException {
		if(StringUtils.isEmpty(token)) {
			refresh();
		}
		return token;
	}
	
	/**
	 * 每隔2小时（115分钟）刷新一次
	 * @return
	 * @throws FailedToGetTokenException 
	 */
	@Scheduled(fixedRate = 1000 * 60 * 115)
	private static void refresh() throws FailedToGetTokenException {
		Token tokenEntity = WechatApi.ForToken.getAccessToken();
		token = tokenEntity.getAccess_token();
		logger.debug(String.format("RefreshToken Successfully, New Token is [%s]", token));
	}

}
