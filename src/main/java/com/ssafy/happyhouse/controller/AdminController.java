package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.MemberService;
import com.ssafy.util.CryptoUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@Api("어드민 컨트롤러  API V1")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private MemberService memberService;
	
	private CryptoUtil crt = new CryptoUtil("HappyHouse_Algorithm!");
	
//	@Autowired
//	private GuestBookService guestBookService;

	
	@GetMapping(value = "/user")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> userList() throws Exception {
		List<MemberDto> list = memberService.listMember();
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		for(MemberDto m : list) {
			m.setPassword(crt.decrypt(m.getPassword()));
		}
		if(list != null && !list.isEmpty()) {
			resultMap.put("userList", list);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
			System.out.println("회원목록 성공");
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
			System.out.println("회원목록 실패");
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원등록", notes = "회원의 정보를 받아 처리.")
	@PostMapping(value = "/user")
	@ResponseBody
	public ResponseEntity<?> userRegister(@RequestBody MemberDto memberDto) throws Exception {
		memberService.registerMember(memberDto);
		List<MemberDto> list = memberService.listMember();
		return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원정보", notes = "회원한명에 대한 정보.")
	@GetMapping(value = "/user/{userid}")
	@ResponseBody
	public ResponseEntity<?> userInfo(@PathVariable("userid") String userid) throws Exception {
		logger.debug("파라미터 : {}", userid);
		MemberDto memberDto = memberService.getMember(userid);
		if(memberDto != null)
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "회원정보수정", notes = "회원정보를 수정합니다.")
	@PutMapping(value = "/user")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> userModify(@RequestBody MemberDto memberDto) throws Exception {
		memberDto.setPassword(crt.encrypt(memberDto.getPassword()));
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		if (memberService.updateMember(memberDto)) {
			return userList();
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("회원정보 수정 실패");
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		
	}
	
	@ApiOperation(value = "회원정보삭제", notes = "회원정보를 삭제합니다.")
	@DeleteMapping(value = "/user/{userid}")
	@ResponseBody
	public ResponseEntity<?> userDelete(@PathVariable("userid") String userid) throws Exception {
		memberService.deleteMember(userid);
		List<MemberDto> list = memberService.listMember();
		return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
	}
	
//	@ApiOperation(value = "글목록", notes = "방명록의 <big>전체 목록</big>을 반환해 줍니다.")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "글목록 OK!!"),
//		@ApiResponse(code = 404, message = "페이지없어!!"),
//		@ApiResponse(code = 500, message = "서버에러!!")
//	})
//	@GetMapping(value = "/listArticle2")
//	public ResponseEntity<?> listArticle2() throws Exception {
//		List<GuestBookDto> list = guestBookService.listArticle2();
//		if(list != null && !list.isEmpty()) {
//			return new ResponseEntity<List<GuestBookDto>>(list, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//	}

}
