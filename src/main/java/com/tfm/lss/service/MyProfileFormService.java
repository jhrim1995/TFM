package com.tfm.lss.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.tfm.at.service.AtCommandProcess;
import com.tfm.bbs.dao.MemberDao;
import com.tfm.bbs.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyProfileFormService implements CommandProcess, AtCommandProcess{
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String id = (String) request.getSession().getAttribute("id");
		
		boolean isLogin = request.getSession().getAttribute("isLogin") != null ? (boolean) request.getSession().getAttribute("isLogin") : false;
		
		if(! isLogin){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('잘못된 접근입니다.');");
			out.println(" history.back();");
			out.println("</script>");
			
			return null;
		}
		
		System.out.println("id = " + id);
		
		MemberDao dao = new MemberDao();
		Member member = dao.myProfileAll(id);
		
		request.setAttribute("Member", member);
		
		
		return "login/updateMember";
	}

}
