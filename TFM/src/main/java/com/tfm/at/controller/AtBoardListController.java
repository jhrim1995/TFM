package com.tfm.at.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.tfm.at.dao.AtBoardDao;
import com.tfm.at.vo.AtBoard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/atBoardList")
public class AtBoardListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		AtBoardDao dao = new AtBoardDao();
		ArrayList<AtBoard> bList = dao.boardList();
		
		req.setAttribute("bList", bList);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/atboard/atBoardList.jsp");
		rd.forward(req, resp);
	}
	
}
