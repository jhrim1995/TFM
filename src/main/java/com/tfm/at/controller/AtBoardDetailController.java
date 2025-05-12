package com.tfm.at.controller;

import java.io.*;

import com.tfm.at.dao.AtBoardDao;
import com.tfm.at.vo.AtBoard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/atBoardDetail")
public class AtBoardDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String at_no = req.getParameter("at_no");
		
		AtBoardDao dao = new AtBoardDao();
		AtBoard b = dao.atGetBoard(Integer.valueOf(at_no));
		
		req.setAttribute("b", b);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/atboard/atBoardDetail.jsp");
		rd.forward(req, resp);
	}
	
}
