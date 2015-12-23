package cc.wechat.service.weather;

import cc.wechat.openapi.exception.ApiStoreException;
import cc.wechat.service.weather.bean.CityParam;
import cc.wechat.service.weather.bean.resp.Weather;

public interface IWeatherService {
	
	/**
	 * 根据地名查询对应的地名id
	 * @param zhName
	 * @return
	 */
	String queryCreaidByZhName (String zhName) throws ApiStoreException;
	
	/**
	 * 根据城市信息获取天气
	 * @param cityPinyin
	 * @return
	 */
	Weather queryWeatherByCityInfo(CityParam cityInfo) throws ApiStoreException;
}
