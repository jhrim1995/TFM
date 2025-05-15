package com.tfm.at.ajax;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="ajaxController", urlPatterns="*.ajax")
public class AtAjaxController extends HttpServlet {
	public void doAjax (
			HttpServletRequest req, HttpServletResponse resp) 
					throws ServletException, IOException {
		
		AtAjaxProcess ajaxAction = null;
		String command = req.getRequestURI();
		command = command.substring(req.getContextPath().length());
		System.out.println("command : "+command);
		if(command.equals("/passCheck.ajax")) {
			//ajaxAction = new PassCheckAction();
			ajaxAction.ajaxProcess(req, resp);
		} else if(command.equals("/atRecm.ajax")) {
			ajaxAction = new AtRecmAction();
			ajaxAction.ajaxProcess(req, resp);
		} else if(command.equals("/atReplyWrite.ajax")) {
			ajaxAction = new AtReplyWriteAction();
			ajaxAction.ajaxProcess(req, resp);
		} else if(command.equals("/atReplyUpdate.ajax")) {
			ajaxAction = new AtReplyUpdateAction();
			ajaxAction.ajaxProcess(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doAjax(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doAjax(req, resp);
	}
	
}
