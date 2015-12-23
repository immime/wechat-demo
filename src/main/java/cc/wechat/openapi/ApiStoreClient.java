package cc.wechat.openapi;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cc.wechat.constant.WechatConsts;
import cc.wechat.openapi.bean.BasePostEntity;
import cc.wechat.openapi.exception.ApiStoreException;
import cc.wechat.service.weather.bean.CityParam;
import cc.wechat.web.WechatController;

public class ApiStoreClient {
	private static final Logger logger = LoggerFactory.getLogger(ApiStoreClient.class);


	public final static String baseApiPath = "http://apis.baidu.com/showapi_open_bus";

	/**
	 * get请求
	 * 
	 * @param path
	 *            api相对路径
	 * @param urlParms
	 *            url参数
	 * @return
	 * @throws ApiStoreException
	 */
	public static JSONObject get(String path, Map<String, String> urlParms) throws ApiStoreException {

		String url = baseApiPath + path;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept-Charset", "utf-8");
		headers.set("apikey", WechatConsts.BAIDU_API_KEY);
		HttpEntity<?> requestEntity = new HttpEntity<Object>(headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class,
				urlParms);

		JSONObject rawJsonObj = JSON.parseObject(responseEntity.getBody());

		return interceptError(url, rawJsonObj);
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

		String url = baseApiPath + path;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept-Charset", "utf-8");
		headers.set("apikey", WechatConsts.BAIDU_API_KEY);
		HttpEntity<?> requestEntity = new HttpEntity<Object>(headers);
		Map<String, Object> urlParms = new HashMap<String, Object>();

		try {
			urlParms = PropertyUtils.describe(entityParms);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class,
				urlParms);

		JSONObject rawJsonObj = JSON.parseObject(responseEntity.getBody());

		return interceptError(url, rawJsonObj);
	}

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

		return interceptError(url, rawJsonObj);
	}

	/**
	 * 拦截api请求错误
	 * 
	 * @param url
	 * @param rawJsonObj
	 * @return
	 */
	private static JSONObject interceptError(String url, JSONObject rawJsonObj) {
		Integer showapiResCode = rawJsonObj.getInteger("showapi_res_code");
		if (showapiResCode == null || showapiResCode != 0) {
			throw new ApiStoreException(url, rawJsonObj.getString("showapi_res_error"));
		}
		JSONObject bodyJsonObj = rawJsonObj.getJSONObject("showapi_res_body");
		Integer retCode = bodyJsonObj.getInteger("ret_code");
		if (retCode != null && retCode == -1) {
			throw new ApiStoreException(url, bodyJsonObj.getString("remark"));
		}
		return bodyJsonObj;
	}

}
