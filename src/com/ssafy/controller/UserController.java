package com.ssafy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.model.UserDto;
import com.ssafy.model.dao.UserDao;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao = new UserDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "/index.jsp";
		if("mvregister".equals(act)) {	//회원가입 페이지
			response.sendRedirect(request.getContextPath() + "/user/join.jsp");
		} else if("register".equals(act)) {	//회원가입
			path = registerMember(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("idcheck".equals(act)) {	//id 체크
			int cnt = idCheck(request, response);
			response.getWriter().append(cnt + "");
		} else if("mvlogin".equals(act)) {	//로그인 페이지
			response.sendRedirect(request.getContextPath() + "/user/login.jsp");
		} else if("login".equals(act)) {	//로그인
			path = loginMember(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("logout".equals(act)) {	//로그아웃
			path = loginOutMember(request, response);
			response.sendRedirect(request.getContextPath() + path);
		} else if("main".equals(act)) {
			request.getRequestDispatcher(path).forward(request, response);
		} else if("showinfo".equals(act)) {	//회원정보 조회
			path = "/user/userinfo.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		} else if("mvupdate".equals(act)) {	//회원정보 수정 페이지
			path = "/user/updateuser.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		} else if("updateUser".equals(act)) {	//회원정보 수정
			path = updateMember(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("deleteUser".equals(act)) {	//회원탈퇴
				path = deletetMember(request, response);
				response.sendRedirect(request.getContextPath() + path);
		}
		 
	}

	private String deletetMember(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("userid");
		
		try {
			boolean isS = userDao.deleteUser(id);
			if(isS) { // 탈퇴 성공
				Cookie cookie = new Cookie("loginid", null);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				HttpSession session = request.getSession();
				session.invalidate();
				return "/index.jsp";
			} else { // 탈퇴 실패
				request.setAttribute("msg", "탈퇴 실패.");
				return "/index.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "탈퇴중 문제 발생!!");
			return "/error/error.jsp";
		}
	}

	private String updateMember(HttpServletRequest request, HttpServletResponse response) {
		UserDto userDto = new UserDto();
		userDto.setName(request.getParameter("username"));
		userDto.setId(request.getParameter("userid"));
		userDto.setPassword(request.getParameter("userpwd"));
		userDto.setEmail(request.getParameter("useremail"));
		userDto.setAddress(request.getParameter("useraddress"));
		try {
			userDao.updateUser(userDto);
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userDto);
			return "/user/userinfo.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원정보 수정중 문제가 발생했습니다.");
			return "/error/error.jsp";
		}
	}

	private int idCheck(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 1;
		String id = request.getParameter("ckid");
		cnt = userDao.idCheck(id);
		return cnt;
	}

	private String registerMember(HttpServletRequest request, HttpServletResponse response) {
		UserDto userDto = new UserDto();
		userDto.setName(request.getParameter("username"));
		userDto.setId(request.getParameter("userid"));
		userDto.setPassword(request.getParameter("userpwd"));
		userDto.setEmail(request.getParameter("emailid") + "@" + request.getParameter("emaildomain"));
		userDto.setAddress(request.getParameter("useraddress"));
		try {
			userDao.register(userDto);
			return "/user/login.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원 가입 중 문제가 발생했습니다.");
			return "/error/error.jsp";
		}
	}

	private String loginMember(HttpServletRequest request, HttpServletResponse response) {
		UserDto userDto = null;
		
		String id = request.getParameter("userid");
		String password = request.getParameter("userpwd");
		
		try {
			userDto = userDao.login(id, password);
			if(userDto != null) { // 로그인 성공
//				session setting
				HttpSession session = request.getSession();
				session.setAttribute("userInfo", userDto);
				
				String idsv = request.getParameter("idsave");
				if("saveok".equals(idsv)) { // 아이디 저장 체크
	//				Cookie setting
					Cookie cookie = new Cookie("loginid", id);
					cookie.setMaxAge(60*60*24*365*20);
					cookie.setPath(request.getContextPath());
					
					response.addCookie(cookie);
				} else { // 아이디 저장 체크 X
					Cookie[] cookies = request.getCookies();
					if(cookies != null) {
						for(int i=0;i<cookies.length;i++) {
							if(cookies[i].getName().equals("loginid")) {
								cookies[i].setMaxAge(0);
								response.addCookie(cookies[i]);
								break;
							}
						}
					}
				}
				
				return "/index.jsp";
			} else { // 로그인 실패
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
				return "/user/login.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 처리중 문제 발생!!");
			return "/error/error.jsp";
		}
	}

	private String loginOutMember(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		session.setAttribute("userInfo", null);
//		session.removeAttribute("userInfo");
		session.invalidate();
		return "/index.jsp";
	}

}
