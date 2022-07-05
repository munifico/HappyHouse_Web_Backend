package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<HouseInfoDto> getAptList(String dong) throws SQLException;
	List<HouseInfoDto> getAptListInfo(String aptlist) throws SQLException;

	List<HouseInfoDto> getAptByText(String text) throws SQLException;
	List<HouseInfoDto> getAptByAptCode(String aptCode) throws SQLException;
	List<HouseInfoDto> getSearchBygugun(String gugun, String userid);
	
}