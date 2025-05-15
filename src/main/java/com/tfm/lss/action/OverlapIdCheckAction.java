package com.tfm.lss.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.tfm.bbs.dao.MemberDao;
import com.tfm.lss.ajax.controller.AjaxProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OverlapIdCheckAction implements AjaxProcess{
	
	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		MemberDao dao = new MemberDao();
		boolean result = dao.overlapIDCheck(id);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\"result\" : \""+result+"\"}");
		
		out.println(sb.toString());
		
	}

}
