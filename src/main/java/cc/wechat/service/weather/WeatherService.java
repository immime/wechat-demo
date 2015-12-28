package cc.wechat.service.weather;

import cc.wechat.openapi.exception.ApiStoreException;
import cc.wechat.sdk.message.NewsMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.service.weather.bean.CityParam;
import cc.wechat.service.weather.bean.resp.Weather;

public interface WeatherService {
	
	/**
	 * 根据地名查询对应的地名id
	 * @param zhName 城市名称
	 * @return
	 */
	String queryCreaid (String zhName) throws ApiStoreException;
	
	/**
	 * 根据城市信息获取天气
	 * @param cityInfo
	 * @return
	 */
	Weather queryWeather(CityParam cityInfo) throws ApiStoreException;
	
	TextMsg queryWeatherTextMsg(CityParam cityInfo) throws ApiStoreException;
	
	NewsMsg queryWeatherNewsMsg(CityParam cityInfo) throws ApiStoreException;

}
