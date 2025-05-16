package com.jsplist.js.ajax;

import java.io.IOException;

import com.jsplist.js.ajax.AjaxProcess;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="ajaxController", urlPatterns="*.ajax")
public class AjaxController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAjax(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAjax(request,response);
	}
	
	protected void doAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		
		// /JSPClassMvcBBS01
		String contextPath = request.getContextPath();
		System.out.println("URI : " + requestURI + ", ctxPath : " + contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : " + command);
				
		AjaxProcess ajaxAction = null;
		
		
		if(command.equals("/recommend.ajax")) {
			ajaxAction = new RecommendAction();
			ajaxAction.ajaxProcess(request, response);
			
		} else if(command.equals("/replyDelete.ajax")) {
			ajaxAction = new ReplyDeleteAction();
			ajaxAction.ajaxProcess(request, response);
			
		}
		
	}
	
}
