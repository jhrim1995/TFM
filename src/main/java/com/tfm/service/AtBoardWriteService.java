package com.tfm.service;

import java.io.IOException;

import com.tfm.at.dao.AtBoardDao;
import com.tfm.vo.AtBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AtBoardWriteService implements CommandProcess {

	public String requestProcess(
			HttpServletRequest req, HttpServletResponse resp) 
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
		
		return "r:atBoardList.mvc";
	}
}
