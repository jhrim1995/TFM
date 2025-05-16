package com.tfm.ajax.controller;

import java.io.IOException;

import com.tfm.ajax.action.AjaxProcess;
import com.tfm.ajax.action.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="AjaxController")
public class AjaxController extends HttpServlet {
	
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
		}else if(command.equals("/atRecm.ajax")) {
			ajaxAction = new AtRecmAction();
			ajaxAction.ajaxProcess(request, response);
		} else if(command.equals("/atReplyWrite.ajax")) {
			ajaxAction = new AtReplyWriteAction();
			ajaxAction.ajaxProcess(request, response);
		} else if(command.equals("/atReplyUpdate.ajax")) {
			ajaxAction = new AtReplyUpdateAction();
			ajaxAction.ajaxProcess(request, response);
		} else if(command.equals("/atReplyDelete.ajax")) {
			ajaxAction = new AtReplyDeleteAction();
			ajaxAction.ajaxProcess(request, response);
		}
	}

}
