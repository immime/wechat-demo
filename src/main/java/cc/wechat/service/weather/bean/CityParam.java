package cc.wechat.service.weather.bean;

import cc.wechat.openapi.bean.BasePostEntity;

public class CityParam extends BasePostEntity {
	private static final long serialVersionUID = 8303294991987181396L;
	/**
	 * 城市名称
	 * e.g.北京
	 */
	private String area = "";
	/**
	 * 城市地区id
	 * e.g.101010100
	 */
	private String areaid = "";
	/**
	 * 是否获取一周天气，默认获取最近3天
	 */
	private int needMoreDay = 0;
	/**
	 * 是否获取小提示
	 */
	private int needIndex = 0;
	/**
	 * 是否获取预警信息
	 */
	private int needAlarm = 0;
	/**
	 * 是否获取未来3小时天气情况
	 */
	private int need3HourForcast = 0;
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
	public int getNeedMoreDay() {
		return needMoreDay;
	}
	public void setNeedMoreDay(int needMoreDay) {
		this.needMoreDay = needMoreDay;
	}
	public int getNeedIndex() {
		return needIndex;
	}
	public void setNeedIndex(int needIndex) {
		this.needIndex = needIndex;
	}
	public int getNeedAlarm() {
		return needAlarm;
	}
	public void setNeedAlarm(int needAlarm) {
		this.needAlarm = needAlarm;
	}
	public int getNeed3HourForcast() {
		return need3HourForcast;
	}
	public void setNeed3HourForcast(int need3HourForcast) {
		this.need3HourForcast = need3HourForcast;
	}
	
}
