package com.tfm.lss.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.tfm.bbs.dao.MemberDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CancleMembershipService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String id = request.getParameter("cancleId");
		String pass = request.getParameter("canclePass");
		
		MemberDao dao = new MemberDao();
		
		String oldPass = dao.getPassword(id);
		
		if(!pass.equals(oldPass)) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('비밀번호가 일치 하지 않습니다.');");
			out.println(" history.back();");
			out.println("</script>");
			
			return null;
		}
		
		
		dao.cancleMembership(id);
		
		return "r:logout.mvc";
	}
	
}
