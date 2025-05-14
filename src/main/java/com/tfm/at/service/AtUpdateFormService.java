package com.tfm.at.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.tfm.at.dao.AtBoardDao;
import com.tfm.at.vo.AtBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AtUpdateFormService implements AtCommandProcess {

	public String requestProcess(
			HttpServletRequest req, HttpServletResponse resp) 
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
			return null;
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
			return null;
		}
		boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		AtBoard b = dao.atGetBoard(Integer.valueOf(no), false);
		req.setAttribute("b", b);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("searchOption", searchOption);
		if(searchOption) {
			req.setAttribute("type", type);
			req.setAttribute("keyword", keyword);
		}
		
		return "atboard/atUpdateForm";
	}
	
}
