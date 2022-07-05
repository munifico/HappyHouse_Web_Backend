package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {

//	MemberDto login(Map<String, String> map) throws Exception;
	
	int idCheck(String checkId) throws Exception;
	boolean registerMember(MemberDto memberDto) throws Exception;
	
	List<MemberDto> listMember() throws Exception;
	MemberDto getMember(String id) throws Exception;
	boolean updateMember(MemberDto memberDto) throws Exception;
	boolean deleteMember(String id) throws Exception;

	String searchPassword(MemberDto memberDto);

	void updateMamber(MemberDto memberDto) throws Exception;
	
	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String id) throws Exception;

	
}
