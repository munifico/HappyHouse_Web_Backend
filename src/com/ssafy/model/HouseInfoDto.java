package com.ssafy.model;

public class HouseInfoDto {
	private String dongName;
	private String aptName;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private String area;
	private String floor;
	private String lat;
	private String lng;
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	public HouseInfoDto() {}

	public HouseInfoDto(String dongName, String aptName, String dealAmount, int dealYear, int dealMonth, String area,
			String floor) {
		super();
		this.dongName = dongName;
		this.aptName = aptName;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.area = area;
		this.floor = floor;
	}
	public HouseInfoDto(String dongName, String aptName, String dealAmount, String lat, String lng) {
		super();
		this.dongName = dongName;
		this.aptName = aptName;
		this.dealAmount = dealAmount;
		this.lat=lat;
		this.lng=lng;
	}
	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "HouseInfoDto [dongName=" + dongName + ", aptName=" + aptName + ", dealAmount=" + dealAmount
				+ ", dealYear=" + dealYear + ", dealMonth=" + dealMonth + ", area=" + area + ", floor=" + floor + "]";
	}
	

}
