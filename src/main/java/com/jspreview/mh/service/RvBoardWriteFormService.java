package com.jspreview.mh.service;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RvBoardWriteFormService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 세션을 체크해서 로그인 상태가 아니면 경고창 로그인 폼으로
		HttpSession session = request.getSession();
		boolean isLogin = session.getAttribute("isLogin") != null ?
				(Boolean) session.getAttribute("isLogin") : false;
		if(! isLogin) {
			
			response.setContentType("text/html; charset = utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 ㄱ~..')");
			out.println("location.href='loginForm.mvc'");
			out.println("</script>");
			
			return null;
		}
		
		return "rvboard/writeForm";
	}

}
