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
		String pageNum = req.getParameter("pageNum");
		String type = req.getParameter("type");
		String keyword = req.getParameter("keyword");
		
		if(at_no == null || at_no.equals("") || pageNum == null || pageNum.equals("")) {
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println(" alert('잘못된 접근입니다.');");
			out.println(" history.back();");
			out.println("</script>");
			return;
		}
		boolean searchOption = ( type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		
		AtBoardDao dao = new AtBoardDao();
		AtBoard b = dao.atGetBoard(Integer.valueOf(at_no));
		req.setAttribute("b", b);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("searchOption", searchOption);
		if(searchOption) {
			req.setAttribute("keyword", keyword );
			req.setAttribute("type", type );
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/atboard/atBoardDetail.jsp");
		rd.forward(req, resp);
	}
	
}
