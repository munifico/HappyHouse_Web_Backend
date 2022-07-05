package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return houseMapMapper.getAptInDong(dong);
	}
	@Override
	public List<HouseInfoDto> getAptList(String dong) throws Exception{
		return houseMapMapper.getAptList(dong);
	}
	
	@Override
	public List<HouseInfoDto> getAptListInfo(String aptlist) throws Exception{
		return houseMapMapper.getAptListInfo(aptlist);
	}


	@Override
	public List<HouseInfoDto> getAptByText(String text) throws Exception{
		// TODO Auto-generated method stub
		//포함 단어 검색하기 위해 앞뒤로 퍼센트 추가
		return houseMapMapper.getAptByText(text);
	}

	@Override
	public List<HouseInfoDto> getAptByAptCode(String aptCode) throws Exception {
		// TODO Auto-generated method stub
		return houseMapMapper.getAptByAptCode(aptCode);
	}

	@Override
	public List<HouseInfoDto> getSearchBygugun(String gugun, String userid) {
		// TODO Auto-generated method stub
		return houseMapMapper.getSearchBygugun(gugun, userid);
	}

}