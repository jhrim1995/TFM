package com.tfm.at.controller;

import java.io.IOException;

import com.tfm.at.dao.AtBoardDao;
import com.tfm.at.vo.AtBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/atWriteProcess")
public class AtBoardWriteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
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
		
		resp.sendRedirect("atBoardList");
	}
	
}
