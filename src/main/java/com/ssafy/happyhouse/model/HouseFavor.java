package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "House(아파트 찜 목록)", description = "유저 아이디와 아파트 넘버를 가진   Domain Class")
public class HouseFavor {
	@ApiModelProperty(value = "user id")
	private String userid;
	@ApiModelProperty(value = "house aptCode")
	private int aptCode;
	private int favorList;

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	public int getFavorList() {
		return favorList;
	}
	public void setFavorList(int favorList) {
		this.favorList = favorList;
	}
	@Override
	public String toString() {
		return "HouseFavor [userid=" + userid + ", aptCode=" + aptCode + ", favorList=" + favorList + "]";
	}
	
}