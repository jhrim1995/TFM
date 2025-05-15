package com.tfm.lss.ajax.controller;

import java.io.IOException;

import com.tfm.lss.action.OverlapIdCheckAction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="LoginAjaxController",urlPatterns="*.ajax")
public class LoginAjaxController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAjax(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAjax(request, response);
	}
	
	protected void doAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		AjaxProcess ajaxAction = null;
		
		if(command.equals("/overlapIdCheck.ajax")) {
			ajaxAction = new OverlapIdCheckAction();
			ajaxAction.ajaxProcess(request, response);
		}
		
		
	}

}
