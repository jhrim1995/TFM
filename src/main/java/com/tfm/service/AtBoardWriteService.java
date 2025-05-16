package com.tfm.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.tfm.at.dao.AtBoardDao;
import com.tfm.vo.AtBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AtBoardWriteService implements CommandProcess {

	public String requestProcess(
			HttpServletRequest req, HttpServletResponse resp) 
					throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		HttpSession session = req.getSession();
		boolean isLogin = session.getAttribute("isLogin") != null ?
				(Boolean) session.getAttribute("isLogin") : false;
		
		if(! isLogin) {
			
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("	alert('로그인이 필요한 서비스 입니다.')");			
			out.println("	location.href='loginForm.mvc'");
			out.println("</script>");
			
			return null;
		}
		
		
		String m_id = req.getParameter("m_id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String pass = req.getParameter("pass");
		
		AtBoard b = new AtBoard();
		
		b.setM_id(m_id);
		b.setTitle(title);
		b.setContent(content);
		b.setPass(pass);
		
		AtBoardDao dao = new AtBoardDao();
		dao.insertBoard(b);
		
		return "r:atBoardList.mvc";
	}
}
