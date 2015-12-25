package cc.wechat.service.weather;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import cc.wechat.sdk.api.response.UploadMaterialResponse;
import cc.wechat.sdk.message.ArticleMsg;
import cc.wechat.sdk.message.NewsMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.service.weather.bean.CityInfo;
import cc.wechat.service.weather.bean.CityParam;
import cc.wechat.service.weather.bean.resp.CityResult;
import cc.wechat.service.weather.bean.resp.Now;
import cc.wechat.service.weather.bean.resp.Weather;

@Service
public class WeatherServiceImpl implements WeatherService {
	private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
	@Autowired
	private ApiConfigCenter apiConfigCenter;
	
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
	public NewsMsg queryWeatherNewsMsg(CityParam cityInfo) throws ApiStoreException {
		// TODO 跳网页处理，否则5s内无法完成回复
		ApiConfig config = apiConfigCenter.getConfig();
		MaterialAPI materialAPI = new MaterialAPI(config);
		Weather w = this.queryWeather(cityInfo);
		
		CityInfo city = w.getCityInfo();
		String cityName = city.getC9() + city.getC7() + city.getC5() + city.getC3();
		
        Article article = new Article("twcBNCdySHHdz_3ivG6CR3J5mXKlqxvim4Eu_3rWHYI", "测试", "测试", "http://www.baidu.com", "测试新闻。无意义", "测试新闻。无意义", Article.ShowConverPic.YES);
        UploadMaterialResponse response = materialAPI.uploadMaterialNews(Arrays.asList(article));
		response.getMediaId();
		
		
		
		NewsMsg msg = new NewsMsg();
		ArticleMsg articleMsg = new ArticleMsg("测试", "测试", "http://mmbiz.qpic.cn/mmbiz/mwALhW5iaRqfsxEkQJMvPf0QZRiaHCCvibAWTc1jwDYLVJGSHDMMIDtMgAmf7TYZ0XPia0HyNRMWQQL5icC1Pz8xytA/0", "http://mp.weixin.qq.com/s?__biz=MzA5OTIxNTA2Mg==&mid=401010288&idx=1&sn=9a022a3338bd23a283c21c64e3cd17a1#rd");
		msg.setArticles(Arrays.asList(articleMsg));
		return msg;
	}

}
