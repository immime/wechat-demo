package cc.wechat.service.weather.bean.resp;

import cc.wechat.service.weather.bean.CityInfo;

public class Weather {
	
	private String time;
	private int ret_code;
	private Now now;
	private CityInfo cityInfo;
	private F1 f1;
	private F2 f2;
	private F3 f3;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getRet_code() {
		return ret_code;
	}
	public void setRet_code(int ret_code) {
		this.ret_code = ret_code;
	}
	public Now getNow() {
		return now;
	}
	public void setNow(Now now) {
		this.now = now;
	}
	public CityInfo getCityInfo() {
		return cityInfo;
	}
	public void setCityInfo(CityInfo cityInfo) {
		this.cityInfo = cityInfo;
	}
	public F1 getF1() {
		return f1;
	}
	public void setF1(F1 f1) {
		this.f1 = f1;
	}
	public F2 getF2() {
		return f2;
	}
	public void setF2(F2 f2) {
		this.f2 = f2;
	}
	public F3 getF3() {
		return f3;
	}
	public void setF3(F3 f3) {
		this.f3 = f3;
	}
	
}
