package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public int idCheck(String checkId) throws Exception {
		return memberMapper.idCheck(checkId); // 0 or 1
	}

	@Override
	public boolean registerMember(MemberDto memberDto) throws Exception {
		return memberMapper.registerMember(memberDto) == 1;
	}

//	@Override
//	public MemberDto login(Map<String, String> map) throws Exception {
//		return memberMapper.login(map);
//	}
	
	@Override
	public List<MemberDto> listMember() throws Exception {
		return memberMapper.listMember();
	}

	@Override
	public MemberDto getMember(String id) throws Exception {
		return memberMapper.getMember(id);
	}

	@Override
	public boolean updateMember(MemberDto memberDto) throws Exception {
		return memberMapper.updateMember(memberDto) == 1;
	}

	@Override
	public boolean deleteMember(String id) throws Exception {
		return memberMapper.deleteMember(id) == 1;
	}

	@Override
	public String searchPassword(MemberDto memberDto) {
		return memberMapper.searchPassword(memberDto);
		
	}

	@Override
	public void updateMamber(MemberDto memberDto) throws Exception {
		memberMapper.updateMember(memberDto);
		
	}
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getId() == null || memberDto.getPassword() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String id) throws Exception {
		return memberMapper.userInfo(id);
	}


}
