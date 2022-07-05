package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseFavor;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.mapper.HouseFavorMapper;

@Service
public class HouseFavorServiceImpl implements HouseFavorService {
	
	@Autowired
	private HouseFavorMapper houseFavorMapper;

	@Override
	public List<Integer> getFavor(String userid) throws Exception {
		return houseFavorMapper.getFavor(userid);
	}

	@Override
	public int insertFavor(HouseFavor houseFavor) throws Exception {
		return houseFavorMapper.insertFavor(houseFavor);
	}

	@Override
	public int deleteFavor(HouseFavor houseFavor) throws Exception {
		return houseFavorMapper.deleteFavor(houseFavor);
	}

	@Override
	public List<HouseInfoDto> getFavorList(String userid) throws Exception {
		return houseFavorMapper.getFavorList(userid);
	}

	@Override
	public List<HouseInfoDto> getPopularList() throws Exception {
		return houseFavorMapper.getPopularList();
	}


}