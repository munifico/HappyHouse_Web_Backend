package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class MemberDto {

	private String id;
	private String password;
	private String name;
	private String email;
	private String address;
	private int role;
	

	public int getRole() {
		return role;
	}



	public void setRole(int role) {
		this.role = role;
	}



	public MemberDto(String id, String password, String name, String email, String address, int role) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.address = address;
		this.role = role;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", address="
				+ address + "]";
	}



	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}



}
