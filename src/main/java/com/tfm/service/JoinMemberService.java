package com.tfm.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.tfm.bbs.dao.MemberDao;
import com.tfm.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JoinMemberService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member mb = new Member();
		MemberDao dao = new MemberDao();
		
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		String foreign = request.getParameter("foreign");
		String telecom = request.getParameter("telecom");
		String phone = request.getParameter("phone");
		
		boolean overlapId = dao.overlapIDCheck(id);
		
		if(overlapId){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("  alert('이미 가입된 아이디입니다.');");
			out.println("  history.back();");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		mb.setM_id(id);
		mb.setPass(pass);  
		mb.setEmail(email);  
		mb.setM_name(name);
		mb.setNickname(nickname);
		mb.setBirthday(birthday);
		mb.setGender(gender); 
		mb.setForeignyn( foreign); 
		mb.setTelecom(telecom); 
		mb.setPhone(phone);
		
		
		dao.insertMember(mb);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("	alert('회원가입이 완료되었습니다.');");
		out.println("	location.href='loginForm.mvc';");
		out.println("</script>");
		out.close();
		
		return null;
	}
	
}
