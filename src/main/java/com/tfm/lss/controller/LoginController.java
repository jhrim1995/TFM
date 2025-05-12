package com.tfm.lss.controller;

import java.io.IOException;

import com.tfm.lss.service.CommandProcess;
import com.tfm.lss.service.JoinMemberFormService;
import com.tfm.lss.service.JoinMemberService;
import com.tfm.lss.service.LoginCheckService;
import com.tfm.lss.service.LoginService;
import com.tfm.lss.service.LogoutService;
import com.tfm.lss.service.MainFormService;
import com.tfm.lss.service.MyProfileFormService;
import com.tfm.lss.service.UpdateProfileService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="LoginController",urlPatterns="*.mvc")
public class LoginController extends HttpServlet{
	
	private final String PREFIX = "/WEB-INF/index.jsp?body=";
	private final String SUFFIX = ".jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}
		
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		String viewPage = null;
		CommandProcess service = null;
		
		if(command.equals("/*.mvc") || command.equals("/main.mvc")){
			service = new MainFormService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/loginForm.mvc")) {
			service = new LoginService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/joinMemberForm.mvc")) {
			service = new JoinMemberFormService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/joinMember.mvc")) {
			service = new JoinMemberService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/loginCheck.mvc")) {
			service = new LoginCheckService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/logout.mvc")) {
			service = new LogoutService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/myProfileForm.mvc")) {
			service = new MyProfileFormService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/updateProfile.mvc")) {
			service = new UpdateProfileService();
			viewPage = service.requestProcess(request, response);
		}
		
		
		if(viewPage != null) {
			String view = viewPage.split(":")[0];
			System.out.println("view = " + view);
			if(view.equals("r") || view.equals("redirect")) {
				response.sendRedirect(viewPage.split(":")[1]);
			}else if(view.equals("f")){
				RequestDispatcher rd =  request.getRequestDispatcher(viewPage.split(":")[1]);
				rd.forward(request, response);
			}else {
				RequestDispatcher rd =  request.getRequestDispatcher(PREFIX + view + SUFFIX);
				rd.forward(request, response);
			}
		}
	}
}
