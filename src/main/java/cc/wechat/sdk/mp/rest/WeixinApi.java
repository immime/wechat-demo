package cc.wechat.sdk.mp.rest;

import java.text.MessageFormat;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.adapter.UnknownAdviceTypeException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import cc.wechat.sdk.mp.rest.entity.Token;
import cc.wechat.sdk.mp.rest.entity.msg.send.BaseSendMsg;
import cc.wechat.sdk.mp.rest.entity.msg.send.MediaSendMsg;
import cc.wechat.sdk.mp.rest.exception.ApiGetTokenFailedException;

/**
 * 用于访问官网API
 * 
 * @author weny
 * @datetime 2015年7月22日 下午2:12:12
 * @version V2.0
 */
public class WeixinApi {
	private static final Logger LOG = LoggerFactory.getLogger(WeixinApi.class);
	/**
	 * 公众号appId
	 */
	private static final String APP_ID = "wxe6626fc25736c77e";
	/**
	 * 公众号secret
	 */
	private static final String APP_SECRET = "c5ea13a94c08a1ed07fc4eaeb6ca913b";
	/**
	 * 全局的是否正在刷新access token的锁
	 */
	private final static Object TOKEN_REFRESH_LOCK = new Object();

	/**
	 * 全局的是否正在刷新jsapi_ticket的锁
	 */
	private final static Object JSAPI_TICKET_REFRESH_LOCK = new Object();

	/**
	 * 获取接口调用凭据
	 * 
	 * @author weny
	 */
	public static class ForToken {
		/**
		 * 获取access token</br>
		 * 
		 * @author weny
		 * @return
		 * @throws ApiGetTokenFailedException
		 */
		public static Token getAccessToken() throws ApiGetTokenFailedException {
			Token token = null;
			synchronized (TOKEN_REFRESH_LOCK) {
				String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" + "&appid="
						+ APP_ID + "&secret=" + APP_SECRET;
				RestTemplate restTemplate = new RestTemplate();
				token = restTemplate.getForObject(uri, Token.class);
				if (StringUtils.isNotEmpty(token.getErrcode())) {
					String eMsgTemplate = "获取token失败:[errorcode:{0},errormsg:{1}]";
					String errorMsg = MessageFormat.format(eMsgTemplate, token.getErrcode(), token.getErrmsg());
					LOG.error(errorMsg);
					throw new ApiGetTokenFailedException(errorMsg);
				}
			}
			return token;
		}

		/**
		 * 获取微信服务器IP地址</br>
		 * 
		 * @author weny
		 * @return
		 * @throws ApiGetTokenFailedException
		 */
		public static String getIpList() throws ApiGetTokenFailedException {
			String uri = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=" + TokenStore.getTOKEN();
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(uri, String.class);
			return result;
		}
	}

	/**
	 * 发送消息
	 * 
	 * @author weny
	 */
	public static class ForSendMsg {
		/**
		 * 公众号向用户发消息
		 * 
		 * @param msg
		 * @return BaseSendMsg，包含微信官服返回的状态码
		 * @throws ApiGetTokenFailedException
		 */
		public static BaseSendMsg customSend(BaseSendMsg msg) throws ApiGetTokenFailedException {
			String uri = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + TokenStore.getTOKEN();
			RestTemplate restTemplate = new RestTemplate();
			BaseSendMsg result = restTemplate.postForObject(uri, msg, msg.getClass());
			return result;
		}
	}

	/**
	 * 临时素材管理
	 * 
	 * @author weny
	 */
	public static class ForMedia {
		public static BaseSendMsg upload(BaseSendMsg msg) throws ApiGetTokenFailedException {
			if(!(msg instanceof MediaSendMsg)) {
				throw new UnknownAdviceTypeException("使用了不支持的参数类型");
			}
			MediaSendMsg mediaMsg = (MediaSendMsg) msg;
			String mediaType = mediaMsg.getMediaType();
			String uri = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + TokenStore.getTOKEN() + "&type=" + mediaType;
			RestTemplate restTemplate = new RestTemplate();
			
			Resource resource = new FileSystemResource(mediaMsg.getPath());
			MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
			parts.add("Content-Type", mediaType);
			parts.add("file", resource);
			parts.add("id_product", UUID.randomUUID().toString());

			HttpEntity entity = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<MultiValueMap<String, Object>>(parts), String.class); // make sure to use the generic type argument
			return null;
		}
	}

	/**
	 * 永久素材
	 * 
	 * @author weny
	 */
	public static class ForMaterial {

	}

}
