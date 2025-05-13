package com.tfm.at.controller;

import java.io.*;

import com.tfm.at.dao.*;
import com.tfm.at.vo.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/atUpdateForm")
public class AtBoardUpdateFormController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String sNo = req.getParameter("at_no");
		String pass = req.getParameter("pass");
		String pageNum = req.getParameter("pageNum");
		String type = req.getParameter("type");
		String keyword = req.getParameter("keyword");
		
		if(sNo == null || sNo.equals("") || pass == null || pass.equals("")
			|| pageNum == null || pageNum.equals("")) {
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println(" alert('잘못된 접근입니다.');");
			out.println(" history.back();");
			out.println("</script>");
			return;
			}
		
		AtBoardDao dao = new AtBoardDao();
		int no = Integer.parseInt(sNo);
		boolean isPassCheck = dao.isPassCheck(no, pass);
		
		if(!isPassCheck) {
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return;
		}
		boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		AtBoard b = dao.atGetBoard(no);
		req.setAttribute("b", b);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("searchOption", searchOption);
		if(searchOption) {
			req.setAttribute("type", type);
			req.setAttribute("keyword", keyword);
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/atboard/atUpdateForm.jsp");
		rd.forward(req, resp);
		
	}
	
}
