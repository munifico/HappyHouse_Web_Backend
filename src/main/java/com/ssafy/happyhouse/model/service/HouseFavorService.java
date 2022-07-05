package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HouseFavor;
import com.ssafy.happyhouse.model.HouseInfoDto;

public interface HouseFavorService {

	List<Integer> getFavor(String userid) throws Exception;
	int insertFavor(HouseFavor houseFavor) throws Exception;
	int deleteFavor(HouseFavor houseFavor) throws Exception;
	List<HouseInfoDto> getFavorList(String userid) throws Exception;
	List<HouseInfoDto> getPopularList() throws Exception;
}