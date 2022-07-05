package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.Board;
import com.ssafy.happyhouse.model.MemberDto;

public interface MemberMapper {

//	MemberDto login(Map<String, String> map) throws Exception;
	
	int idCheck(String checkId) throws Exception;
	int registerMember(MemberDto memberDto) throws Exception;
	
	List<MemberDto> listMember() throws Exception;
	MemberDto getMember(String id) throws Exception;
	int updateMember(MemberDto memberDto) throws Exception;
	int deleteMember(String id) throws Exception;

	String searchPassword(MemberDto memberDto);
	
	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String id) throws SQLException;
	
}
