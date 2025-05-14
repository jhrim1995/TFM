package com.tfm.lss.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.tfm.at.service.AtCommandProcess;
import com.tfm.bbs.dao.MemberDao;
import com.tfm.bbs.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateProfileService implements CommandProcess, AtCommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String oldPass = request.getParameter("oldPass");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String telecom = request.getParameter("telecom");
		String phone = request.getParameter("phone");
		
		MemberDao dao = new MemberDao();
		
		String oldRealPass = dao.getPassword(id);
		
		if(!oldPass.equals(oldRealPass)) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('비밀번호가 일치 하지 않습니다.');");
			out.println(" history.back();");
			out.println("</script>");
			
			return null;
		}
		
		Member member = new Member(id, pass, email, name, nickname, null, null, null, telecom, phone, null);
		
		dao.updateProfile(member);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf-8");
		out.println("<script>");
		out.println(" alert('수정이 완료되었습니다.');");
		out.println(" location.href='main.mvc';");
		out.println("</script>");
		
		return null;
	}
	
}
