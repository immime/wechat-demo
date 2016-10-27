package cc.wechat.module.weather;

import cc.wechat.module.weather.vo.CityParam;
import cc.wechat.module.weather.vo.resp.Weather;
import cc.wechat.openapi.exception.ApiStoreException;
import cc.wechat.sdk.api.response.BaseResponse;
import cc.wechat.sdk.message.NewsMsg;
import cc.wechat.sdk.message.TextMsg;

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
