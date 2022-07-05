package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.happyhouse.model.Board;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.MemberService;
import com.ssafy.util.CryptoUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//회원 처리용 controller
@Controller
@RequestMapping("/user")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	private CryptoUtil crt = new CryptoUtil("HappyHouse_Algorithm!");

	@GetMapping("/register")
	public String register() {
		return "user/join";
	}

	@GetMapping("/idcheck")
//	@ResponseBody
	public @ResponseBody String idCheck(@RequestParam("ckid") String checkId) throws Exception {
		int idCount = memberService.idCheck(checkId);
		JSONObject json = new JSONObject();
		json.put("idcount", idCount);
		return json.toString();
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody MemberDto memberDto) throws Exception {
		logger.debug("memberDto info : {}", memberDto);
		String pwd = memberDto.getPassword();
		memberDto.setPassword(crt.encrypt(pwd));
		if (memberService.registerMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		memberDto.setPassword(crt.encrypt(memberDto.getPassword()));
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String token = jwtService.create("id", loginUser.getId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
				System.out.println("로그인 성공");
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
				System.out.println("로그인 실패");
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/logout")
	public ResponseEntity<Map<String, Object>> logout() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
			System.out.println("로그아웃 성공");
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

//	@GetMapping("/list")
//	public String list() {
//		return "user/list";
//	}

	@GetMapping("/list")
	public ModelAndView list() throws Exception {
		ModelAndView mav = new ModelAndView();

		List<MemberDto> list = memberService.listMember();
		for (MemberDto member : list) {
			member.setPassword(crt.decrypt(member.getPassword()));
		}
		mav.addObject("members", list);
		mav.setViewName("user/list");
		return mav;
	}

	@GetMapping("/search")
	public String search() {
		return "user/search";
	}

	@PostMapping("/searchpassword")
	public ResponseEntity<Map<String, Object>> searchPassword(@RequestBody MemberDto memberDto)
			throws Exception {
		logger.debug("memberDto info : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		String password = memberService.searchPassword(memberDto);
		System.out.println(password);
		if (password != null) {
			resultMap.put("password", crt.decrypt(password));
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
			System.out.println("회원정보 성공");
		} else {
			resultMap.put("password", null);
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("회원정보 실패");
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/userinfo")
	public String userinfo() {
		return "user/userinfo";
	}

	@ApiOperation(value = "회원정보", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("id") @ApiParam(value = "인증할 회원의 아이디.", required = true) String id,
			HttpServletRequest request) {
//		logger.debug("id : {} ", id);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(id);
				memberDto.setPassword(crt.decrypt(memberDto.getPassword()));
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
				System.out.println("회원정보 성공");
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				System.out.println("회원정보 실패");
			}

		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;

		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> update(@RequestBody MemberDto memberDto) throws Exception {
		memberDto.setPassword(crt.encrypt(memberDto.getPassword()));
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		if (memberService.updateMember(memberDto)) {
			memberDto.setPassword(crt.decrypt(memberDto.getPassword()));
			resultMap.put("userInfo", memberDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
			System.out.println("회원정보 수정 성공");
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("회원정보 수정 실패");
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
//	public String update(MemberDto memberDto, Model model, HttpSession session) throws Exception {
//		logger.debug("memberDto info : {}", memberDto);
//		memberDto.setPassword(crt.encrypt(memberDto.getPassword()));
//		memberService.updateMamber(memberDto);
//		return "redirect:/";
//	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id) throws Exception {
		logger.debug("memberid info : {}", id);

		if (memberService.deleteMember(id)) {
			System.out.println("삭제 성공");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		System.out.println("삭제 실패");
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
