package cc.wechat.service.weather.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.wechat.config.ApiConfigCenter;
import cc.wechat.openapi.ApiStoreClient;
import cc.wechat.openapi.exception.ApiStoreException;
import cc.wechat.sdk.api.MaterialAPI;
import cc.wechat.sdk.api.config.ApiConfig;
import cc.wechat.sdk.api.entity.Article;
import cc.wechat.sdk.api.response.BaseResponse;
import cc.wechat.sdk.api.response.UploadMaterialResponse;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.service.weather.IWeatherService;
import cc.wechat.service.weather.bean.CityParam;
import cc.wechat.service.weather.bean.resp.CityResult;
import cc.wechat.service.weather.bean.resp.Now;
import cc.wechat.service.weather.bean.resp.Weather;

@Service
public class WeatherService implements IWeatherService {
	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

	@Override
	public String queryCreaid(String zhName) {
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
	public Weather queryWeather(CityParam cityInfo) {
		String path = "/weather_showapi/address?area={area}&areaid={areaid}";
			
		JSONObject bodyJsonObj = ApiStoreClient.get(path, cityInfo);
		Weather w = JSON.toJavaObject(bodyJsonObj, Weather.class);
		return w;
	}

	@Override
	public TextMsg queryWeatherTextMsg(CityParam cityInfo) throws ApiStoreException {
		TextMsg msg = new TextMsg();
		Weather w = this.queryWeather(cityInfo);
		Now now = w.getNow();
		msg.add("《").add(cityInfo.getArea()).add("天气").add("》").add("\n")
			.add("当前：").add("<img ").add("src=\"").add(now.getWeather_pic()).add("\"").add("/>\n")
			.add("温度：").add(now.getTemperature()).add("℃").add("\n")
			.add("湿度：").add(now.getSd()).add("\n")
			.add("AQI：").add(now.getAqi()).add("\n")
			.add("天气：").add(now.getWeather()).add("\n")
			.add("风向：").add(now.getWind_direction()).add("\n")
			.add("风力：").add(now.getWind_power())
			.add("风力：").add(now.getWind_power())
			.add("风力：").add(now.getWind_power())
			.add("风力：").add(now.getWind_power());
		return msg;
	}

	@Override
	public BaseResponse queryWeatherBaseResponse(CityParam cityInfo) throws ApiStoreException {
		// TODO Auto-generated method stub
		ApiConfig config = ApiConfigCenter.getCongig();
		MaterialAPI materialAPI = new MaterialAPI(config);
        Article article = new Article("VnzJFSwv05ezhWSlU3kV6fmFYxHXaIHQMxx2SjX87fg", "测试", "测试", "http://www.baidu.com", "测试新闻。无意义", "测试新闻。无意义", Article.ShowConverPic.YES);
        UploadMaterialResponse response = materialAPI.uploadMaterialNews(Arrays.asList(article));
		Weather w = this.queryWeather(cityInfo);
		response.getMediaId();
		return null;
	}

}
