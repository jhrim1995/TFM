package com.tfm.at.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="AtBoardController", urlPatterns="*mvc")
public class AtBoardController extends HttpServlet {
	
	private final String PREFIX = "/WEB-INF/";
	private final String SUFFIX = ".jsp";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String reqURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		System.out.println("uri : "+reqURI+", ctxPath : "+contextPath);
		
		String command = reqURI.substring(contextPath.length());
		System.out.println("command : "+command);
		
		String viewPage = null;
		
		if(command.equals("/atBoardList.mvc") || command.equals("/*mvc")) {
			AtBoardListService list = new AtBoardListService();
			viewPage = list.requestProcess(req, resp);
		} else if(command.equals("/atBoardDetail.mvc")) {
			AtBoardDetailService service = new AtBoardDetailService();
			viewPage = service.requestProcess(req, resp);
		} else if(command.equals("/atBoardDetail.mvc")) {
			AtBoardDetailService service = new AtBoardDetailService();
			viewPage = service.requestProcess(req, resp);
		} else if(command.equals("/atBoardDetail.mvc")) {
			AtBoardDetailService service = new AtBoardDetailService();
			viewPage = service.requestProcess(req, resp);
		} else if(command.equals("/atBoardDetail.mvc")) {
			AtBoardDetailService service = new AtBoardDetailService();
			viewPage = service.requestProcess(req, resp);
		}
		
	}
	
}





















