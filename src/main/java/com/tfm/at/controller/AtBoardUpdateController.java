package com.tfm.at.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.tfm.at.dao.AtBoardDao;
import com.tfm.at.vo.AtBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/atUpdateProcess")
public class AtBoardUpdateController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String pass = null, m_id = null, title = null, content = null;
		int at_no = 0;
		
		at_no = Integer.parseInt(req.getParameter("at_no"));
		pass = req.getParameter("pass");
		
		AtBoardDao dao = new AtBoardDao();
		boolean isPassCheck = dao.isPassCheck(at_no, pass);
		if(! isPassCheck) {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('비밀번호가 틀립니다.');");
			sb.append("history.back();");
			sb.append("</script>");
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("비밀번호가 틀립니다.");
			return;
		}
		
		m_id = req.getParameter("m_id");
		title = req.getParameter("title");
		content = req.getParameter("content");
		
		AtBoard b = new AtBoard();
		b.setAt_no(at_no);
		b.setM_id(m_id);
		b.setTitle(title);
		b.setContent(content);
		b.setPass(pass);
		
		dao.updateBoard(b);
		resp.sendRedirect("atBoardList");
	}
}
