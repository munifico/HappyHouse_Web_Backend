package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.HouseFavor;
import com.ssafy.happyhouse.model.HouseInfoDto;

@Mapper
public interface HouseFavorMapper {

	List<Integer> getFavor(String userid) throws Exception;
	int insertFavor(HouseFavor houseFavor) throws Exception;
	int deleteFavor(HouseFavor houseFavor) throws Exception;
	List<HouseInfoDto> getFavorList(String userid) throws Exception;
	List<HouseInfoDto> getPopularList() throws Exception;
}