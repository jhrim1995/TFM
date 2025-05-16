package com.tfm.lss.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.tfm.bbs.dao.MemberDao;
import com.tfm.bbs.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCheckService implements CommandProcess{
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDao dao = new MemberDao();
		
		int checkLogin =  dao.loginCheck(id, pass);
		
		System.out.println("로그인 = " + checkLogin);
		
		if(checkLogin == -1) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('아이디가 존재 하지 않습니다.');");
			out.println(" history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else if(checkLogin == 0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('비밀번호가 일치 하지 않습니다.');");
			out.println(" history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		Member member = dao.myProfileAll(id);
		
		request.getSession().setAttribute("isLogin", true);
		request.getSession().setAttribute("id", id);
		request.getSession().setAttribute("nickname", member.getNickname());
		
		return "r:main.mvc";
	}

}
