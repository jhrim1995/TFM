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
		AtBoard b = dao.atGetBoard(no);
		req.setAttribute("b", b);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/atboard/atUpdateForm.jsp");
		rd.forward(req, resp);
		
	}
	
}
