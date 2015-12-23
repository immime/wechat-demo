package cc.wechat.service.weather.bean.resp;

import cc.wechat.service.weather.bean.CityInfo;

public class CityResult {
	private String area;
	private String areaid;
	private CityInfo cityInfo;
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public CityInfo getCityInfo() {
		return cityInfo;
	}
	public void setCityInfo(CityInfo cityInfo) {
		this.cityInfo = cityInfo;
	}

}
