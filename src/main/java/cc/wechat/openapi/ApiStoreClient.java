package cc.wechat.openapi;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cc.wechat.constant.WechatConsts;
import cc.wechat.openapi.bean.BasePostEntity;
import cc.wechat.openapi.exception.ApiStoreException;

public class ApiStoreClient {
	private static final Logger logger = LoggerFactory.getLogger(ApiStoreClient.class);

	public final static String baseApiPath = "http://apis.baidu.com";

	/**
	 * get请求
	 * 
	 * @param path
	 * @param urlParams
	 * @return
	 * @throws ApiStoreException
	 */
	public static JSONObject get(String path, Map<String, String> urlParams) throws ApiStoreException {
		return executeGet(path, null, urlParams);
	}

	/**
	 * get请求
	 * 
	 * @param path
	 *            api相对路径
	 * @param hearParams
	 *            header参数
	 * @param urlParams
	 *            url参数
	 * @return
	 * @throws ApiStoreException
	 */
	public static JSONObject get(String path, Map<String, String> hearParams, Map<String, String> urlParams)
			throws ApiStoreException {
		return executeGet(path, hearParams, urlParams);
	}

	/**
	 * get请求
	 * 
	 * @param path
	 *            api相对路径
	 * @param entityParms
	 *            entity参数
	 * @return
	 * @throws ApiStoreException
	 */
	public static JSONObject get(String path, BasePostEntity entityParms) throws ApiStoreException {

		Map<String, Object> urlParams = new HashMap<String, Object>();

		try {
			urlParams = PropertyUtils.describe(entityParms);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		if (MapUtils.isNotEmpty(urlParams)) {
			for (Entry<String, Object> entry : urlParams.entrySet()) {
				MapUtils.multiValueMap(urlParams);
				entry.getKey();
			}
		}

		JSONObject rawJsonObj = executeGet(path, null, urlParams);

		return rawJsonObj;
	}

	/**
	 * POST请求
	 * @param path
	 * @param postEntity
	 * @return
	 * @throws ApiStoreException
	 */
	public static JSONObject post(String path, BasePostEntity postEntity) throws ApiStoreException {
		String url = baseApiPath + path;
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Accept-Charset", "utf-8");
		headers.set("apikey", WechatConsts.BAIDU_API_KEY);

		HttpEntity<BasePostEntity> request = new HttpEntity<BasePostEntity>(postEntity, headers);

		String rawJsonObjStr = restTemplate.postForObject(url, request, String.class);

		JSONObject rawJsonObj = JSON.parseObject(rawJsonObjStr);

		return rawJsonObj;
	}

	/**
	 * =============================================</br>
	 * private methods</br>
	 * =============================================
	 */
	private static JSONObject executeGet(String path, Map<String, String> hearParams, Map<String, ?> urlParams) {
		String url = baseApiPath + path;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept-Charset", "utf-8");
		headers.set("apikey", WechatConsts.BAIDU_API_KEY);
		
		if (MapUtils.isNotEmpty(hearParams)) {
			for (Entry<String, String> entry : hearParams.entrySet()) {
				headers.set(entry.getKey(), entry.getValue());
			}
		}
		
		HttpEntity<?> requestEntity = new HttpEntity<Object>(headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class, urlParams);

		JSONObject rawJsonObj = JSON.parseObject(responseEntity.getBody());
		return rawJsonObj;
	}

}
