package com.ssafy.model;

public class StoreDto {
	private String scode;
	private String sname;
	private String type;
	private String dongcode;
	private String dongName;
	private String lng;
	private String lat;
	public StoreDto() {	}
	public StoreDto(String scode, String sname, String type, String dongcode, String dongName, String lng, String lat) {
		super();
		this.scode = scode;
		this.sname = sname;
		this.type = type;
		this.dongcode = dongcode;
		this.dongName = dongName;
		this.lng = lng;
		this.lat = lat;
	}
	public StoreDto(String sname, String type, String dongName, String lng, String lat) {
		super();
		this.sname = sname;
		this.type = type;
		this.dongName = dongName;
		this.lng = lng;
		this.lat = lat;
	}
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	@Override
	public String toString() {
		return "StoreDto [scode=" + scode + ", sname=" + sname + ", type=" + type + ", dongcode=" + dongcode
				+ ", dongName=" + dongName + ", lng=" + lng + ", lat=" + lat + "]";
	}
	
}
