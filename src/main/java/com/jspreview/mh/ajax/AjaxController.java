package com.jspreview.mh.ajax;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="ajaxController", urlPatterns="*.ajax")
public class AjaxController extends HttpServlet{
	
	public void doAjax(
			HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		
		AjaxProcess ajaxAction = null;
		
		String command = request.getRequestURI();
		
		command = command.substring(request.getContextPath().length());
		System.out.println("command : " + command);
		
		if(command.equals("/rvPassCheck.ajax")) {
			ajaxAction = new RvPassCheckAction();
			ajaxAction.ajaxProcess(request,response);
			
		} else if(command.equals("/rvRecommend.ajax")) {
			ajaxAction = new RvRecommendAction();
			ajaxAction.ajaxProcess(request, response);
			
		} else if(command.equals("/rvReplyWrite.ajax")) {
			ajaxAction = new RvReplyWriteAction();
			ajaxAction.ajaxProcess(request, response);
			
		} else if(command.equals("/rvReplyUpdate.ajax")) {
			ajaxAction = new RvReplyUpdateAction();
			ajaxAction.ajaxProcess(request, response);
			
		} else if(command.equals("/rvReplyDelete.ajax")) {
			ajaxAction = new RvReplyDeleteAction();
			ajaxAction.ajaxProcess(request, response);
			
		}
		
		
	}

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		doAjax(request, response);
	
	}

	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		doAjax(request, response);
		
	}
	
	

}
