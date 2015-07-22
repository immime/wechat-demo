package cc.wechat.sdk.mp.rest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cc.wechat.sdk.mp.rest.entity.Token;
import cc.wechat.sdk.mp.rest.exception.ApiGetTokenFailedException;

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
	 * @throws ApiGetTokenFailedException 
	 */
	public static String getTOKEN() throws ApiGetTokenFailedException {
		if(StringUtils.isEmpty(token)) {
			refreshToken();
		}
		return token;
	}
	
	/**
	 * 每隔2小时刷新一次
	 * @return
	 * @throws ApiGetTokenFailedException 
	 */
	@Scheduled(fixedRate = 1000 * 60 * 60 * 2)
	private static void refreshToken() throws ApiGetTokenFailedException {
		Token tokenEntity = WeixinApi.ForToken.getAccessToken();
		token = tokenEntity.getAccess_token();
		logger.debug(String.format("RefreshToken Successfully, New Token is [%s]", token));
	}

}
