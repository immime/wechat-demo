package cc.wechat.service.weather.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.wechat.openapi.ApiStoreClient;
import cc.wechat.service.weather.IWeatherService;
import cc.wechat.service.weather.bean.CityParam;
import cc.wechat.service.weather.bean.resp.CityResult;
import cc.wechat.service.weather.bean.resp.Weather;
import cc.wechat.web.WechatController;

@Service
public class WeatherService implements IWeatherService {
	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

	@Override
	public String queryCreaidByZhName(String zhName) {
		String path = "/weather_showapi/areaid?area={area}";
		Map<String, String> params = Collections.singletonMap("area", "北京");
		JSONObject bodyJsonObj = ApiStoreClient.get(path, params);
		
		JSONArray result = bodyJsonObj.getJSONArray("list"); 
		List<CityResult> list= JSON.parseArray(result.toJSONString(),CityResult.class);
		if(CollectionUtils.isNotEmpty(list)) {
			CityResult cityResult = list.get(0);
			return cityResult.getAreaid();
		}
		return null;
	}

	@Override
	public Weather queryWeatherByCityInfo(CityParam cityInfo) {
		String path = "/weather_showapi/address?area={area}&areaid={areaid}";
			
		JSONObject bodyJsonObj = ApiStoreClient.get(path, cityInfo);
		Weather w = JSON.toJavaObject(bodyJsonObj, Weather.class);
		return null;
	}

}
