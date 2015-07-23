package cc.wechat.sdk.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import cc.wechat.config.WechatConsts;
import cc.wechat.sdk.bean.IpList;
import cc.wechat.sdk.bean.Token;
import cc.wechat.sdk.bean.msg.in.BaseInMsg;
import cc.wechat.sdk.bean.msg.in.MaterialInMsg;
import cc.wechat.sdk.bean.msg.in.MediaInMsg;
import cc.wechat.sdk.bean.msg.out.ImageOutMsg;
import cc.wechat.sdk.bean.msg.out.MaterialOutMsg;
import cc.wechat.sdk.bean.msg.out.MediaOutMsg;
import cc.wechat.sdk.bean.msg.out.TextOutMsg;
import cc.wechat.sdk.exception.ApiException;
import cc.wechat.sdk.exception.ConnectException;
import cc.wechat.sdk.exception.FailedToGetTokenException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	 * 素材存放临时目录
	 */
	private final static String FILE_TMP_PATH = WechatConsts.FILE_TMP_PATH;

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
		public static Token getAccessToken() throws ApiException {
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
					throw new FailedToGetTokenException(errorMsg);
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
		public static IpList getIpList() throws ApiException {
			String uri = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=" + TokenStore.getTOKEN();
			RestTemplate restTemplate = new RestTemplate();
			IpList result = restTemplate.getForObject(uri, IpList.class);
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
		 * 公众号向用户发消息-文字
		 * 
		 * @param msg
		 * @return BaseSendMsg，包含微信官服返回的状态码
		 * @throws ApiGetTokenFailedException
		 */
		public static BaseInMsg customSend(TextOutMsg msg) throws ApiException {
			String uri = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + TokenStore.getTOKEN();
			RestTemplate restTemplate = new RestTemplate();
			BaseInMsg result = restTemplate.postForObject(uri, msg, BaseInMsg.class);
			return result;
		}
		
		/**
		 * 公众号向用户发消息-图片
		 * 
		 * @param msg
		 * @return BaseSendMsg，包含微信官服返回的状态码
		 * @throws ApiGetTokenFailedException
		 */
		public static BaseInMsg customSend(ImageOutMsg msg) throws ApiException {
			String uri = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + TokenStore.getTOKEN();
			RestTemplate restTemplate = new RestTemplate();
			BaseInMsg result = restTemplate.postForObject(uri, msg, BaseInMsg.class);
			return result;
		}
	}

	/**
	 * 临时素材管理
	 * 
	 * @author weny
	 */
	public static class ForMedia {
		/**
		 * 新增临时素材
		 * @param msg
		 * @return
		 * @throws ApiGetTokenFailedException
		 */
		public static MediaInMsg upload(MediaOutMsg msg) throws ApiException {
			MediaInMsg inMsg = null;
			String mediaType = msg.getMediaType();
			String uri = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + TokenStore.getTOKEN() + "&type=" + mediaType;
			RestTemplate restTemplate = new RestTemplate();
			
			Resource resource = new FileSystemResource(msg.getPath());
			MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
			parts.add("Content-Type", mediaType);
			parts.add("file", resource);
			parts.add("id_product", UUID.randomUUID().toString());

			ResponseEntity<String> entity = restTemplate.exchange(uri, HttpMethod.POST, 
					new HttpEntity<MultiValueMap<String, Object>>(parts), String.class); 
			if(entity.getStatusCode().is2xxSuccessful()) {
				String body = entity.getBody();
				if(StringUtils.isNotEmpty(body) && body.contains("media_id")) {
					ObjectMapper mapper = new ObjectMapper();
					try {
						inMsg =  mapper.readValue(body, MediaInMsg.class);
					} catch (IOException e) {
						LOG.error("IOException：新增临时素材", e);
					}
				}
			} else {
				throw new ConnectException("微信服务器未正确响应");
			}
			return inMsg;
		}
		
		/**
		 * 获取临时素材
		 * @param msg
		 * @return OutputStream
		 * @throws ApiGetTokenFailedException
		 */
		public static OutputStream getStream(MediaOutMsg msg) throws ApiException {
			FileOutputStream output = null;
			String mediaId = msg.getMedia_id();
			String uri = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + TokenStore.getTOKEN() + "&media_id=" + mediaId;
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ResponseEntity<byte[]> response = restTemplate.exchange(uri, HttpMethod.GET, entity, byte[].class);
			
			Collection<String> dispositions = response.getHeaders().get("Content-disposition");
			String fileName = "";
			if(CollectionUtils.isNotEmpty(dispositions)) {
				String nameStr = dispositions.toString();
				int begin = nameStr.indexOf("\"") + 1;
				int end = nameStr.lastIndexOf("\"");
				fileName = nameStr.substring(begin, end);
				fileName = FILE_TMP_PATH.concat("\\").concat(fileName);
				if(response.getStatusCode().is2xxSuccessful()) {
					HttpHeaders responseHead = response.getHeaders();
					MediaType mediaType = responseHead.getContentType();
					if(mediaType.equals(MediaType.IMAGE_JPEG)) {
						try {
							output = new FileOutputStream(new File(fileName));
							IOUtils.write(response.getBody(), output);
						} catch (FileNotFoundException e) {
							LOG.error("FileNotFoundException：获取临时素材", e);
						} catch (IOException e) {
							LOG.error("IOException：获取临时素材", e);
						}
					}
				} else {
					throw new ConnectException("微信服务器未正确响应");
				}
			}
			return output;
		}
		
		/**
		 * 获取临时素材
		 * @param outMsg
		 * @return MediaInMsg, MediaInMsg.getLocalPath()可以获取本地存储路径
		 * @throws ApiException
		 */
		public static MediaInMsg get(MediaOutMsg outMsg) throws ApiException {
			MediaInMsg inMsg = new MediaInMsg();
			String mediaId = outMsg.getMedia_id();
			String uri = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + TokenStore.getTOKEN() + "&media_id=" + mediaId;
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ResponseEntity<byte[]> response = restTemplate.exchange(uri, HttpMethod.GET, entity, byte[].class);
			
			Collection<String> dispositions = response.getHeaders().get("Content-disposition");
			String fileName = "";
			if(CollectionUtils.isNotEmpty(dispositions)) {
				String nameStr = dispositions.toString();
				int begin = nameStr.indexOf("\"") + 1;
				int end = nameStr.lastIndexOf("\"");
				fileName = nameStr.substring(begin, end);
				fileName = FILE_TMP_PATH.concat(fileName);
				if(response.getStatusCode().is2xxSuccessful()) {
					HttpHeaders responseHead = response.getHeaders();
					MediaType mediaType = responseHead.getContentType();
					if(mediaType.equals(MediaType.IMAGE_JPEG)) {
						try {
							FileOutputStream output = new FileOutputStream(new File(fileName));
							IOUtils.write(response.getBody(), output);
						} catch (FileNotFoundException e) {
							LOG.error("FileNotFoundException：获取临时素材", e);
						} catch (IOException e) {
							LOG.error("IOException：获取临时素材", e);
						}
					}
				} else {
					throw new ConnectException("微信服务器未正确响应");
				}
			}
			inMsg.setMedia_id(mediaId);
			inMsg.setLocalPath(fileName);
			return inMsg;
		}
	}

	/**
	 * 永久素材
	 * 
	 * @author weny
	 */
	public static class ForMaterial {
		
		/**
		 * 新增其他类型永久素材（上传图文中的媒体文件）
		 * @param outMsg
		 * @return
		 * @throws ApiException
		 */
		public static MaterialInMsg addMaterial(MaterialOutMsg outMsg) throws ApiException {
			return null;
		}
		
		/**
		 * 新增永久图文素材
		 * @param outMsg
		 * @return
		 * @throws ApiException
		 */
		public static MaterialInMsg addNews(MediaOutMsg outMsg) throws ApiException {
			return null;
		}
		
		
		
	}

}
