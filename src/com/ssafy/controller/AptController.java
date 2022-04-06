package com.ssafy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.dao.HouseInfoDao;
import com.ssafy.model.HouseInfoDto;
import com.ssafy.model.StoreDto;

@WebServlet("/apt")
public class AptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HouseInfoDao hdao = HouseInfoDao.getInstance();
	String root = "";
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		root = request.getContextPath();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("act");
		String action2 = request.getParameter("act2");
		if(action!=null && action.equalsIgnoreCase("goApt")) {
			response.sendRedirect("./Apt/dongSearchAptList.jsp");
		}else if(action!=null && action.equalsIgnoreCase("dongAptlist")) {
			dongAptlist(request,response);
		}else if(action!=null && action.equalsIgnoreCase("Aptlist")) {
			Aptlist(request,response);
		}else if(action2!=null && action2.equalsIgnoreCase("Storelist")) {
			Storelist(request,response);
		}
	}
	private void Storelist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dongName = request.getParameter("dongName");
		List<StoreDto> list = hdao.storelist(dongName);
		request.setAttribute("Stores", list);
		request.setAttribute("dongName", dongName);
		dispatch("./Apt/dongSearchAptList.jsp", request, response);		
	}
	private void Aptlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aptName = request.getParameter("aptName");
		List<HouseInfoDto> list = hdao.aptlist(aptName);
		request.setAttribute("Apts", list);
		request.setAttribute("aptName", aptName);
		dispatch("./Apt/dongSearchAptList.jsp", request, response);		
	}
	private void dongAptlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dongName = request.getParameter("dongName");
		List<HouseInfoDto> list = hdao.donglist(dongName);
		request.setAttribute("Apts", list);
		request.setAttribute("dongName", dongName);
		dispatch("./Apt/dongSearchAptList.jsp", request, response);		
	}
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
}
