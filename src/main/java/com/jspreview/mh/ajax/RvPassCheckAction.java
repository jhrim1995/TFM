package com.jspreview.mh.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import com.jspreview.mh.dao.MemberDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RvPassCheckAction implements AjaxProcess{

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("m_id");
		String pass = request.getParameter("pass");
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		StringBuilder sb = new StringBuilder();
		
		if(id == null || pass == null) {
			sb.append("<script>");
			sb.append("alert('비정상적 접근');");
			sb.append("</script>");
			
			out.println(sb.toString());
			return;
		}
		
		MemberDao dao = new MemberDao();
		int result = dao.loginCheck(id, pass);
		
		sb.append(result);
		out.println(sb.toString());
		
	}

}
