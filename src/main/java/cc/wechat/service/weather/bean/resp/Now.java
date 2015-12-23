package cc.wechat.service.weather.bean.resp;

public class Now {
	
	private String sd;
	private String temperature_time;
	private String aqi;
	private String temperature;
	private String weather;
	private AqiDetail aqiDetail;
	private String wind_direction;
	private String weather_pic;
	private String weather_code;
	private String wind_power;
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	public String getTemperature_time() {
		return temperature_time;
	}
	public void setTemperature_time(String temperature_time) {
		this.temperature_time = temperature_time;
	}
	public String getAqi() {
		return aqi;
	}
	public void setAqi(String aqi) {
		this.aqi = aqi;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public AqiDetail getAqiDetail() {
		return aqiDetail;
	}
	public void setAqiDetail(AqiDetail aqiDetail) {
		this.aqiDetail = aqiDetail;
	}
	public String getWind_direction() {
		return wind_direction;
	}
	public void setWind_direction(String wind_direction) {
		this.wind_direction = wind_direction;
	}
	public String getWeather_pic() {
		return weather_pic;
	}
	public void setWeather_pic(String weather_pic) {
		this.weather_pic = weather_pic;
	}
	public String getWeather_code() {
		return weather_code;
	}
	public void setWeather_code(String weather_code) {
		this.weather_code = weather_code;
	}
	public String getWind_power() {
		return wind_power;
	}
	public void setWind_power(String wind_power) {
		this.wind_power = wind_power;
	}

}
