package com.tfm.at.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.tfm.at.dao.AtBoardDao;
import com.tfm.at.vo.AtBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AtBoardDetailService implements AtCommandProcess {

	public String requestProcess(
			HttpServletRequest req, HttpServletResponse resp)
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
			return null;
		}
		boolean searchOption = ( type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		
		AtBoardDao dao = new AtBoardDao();
		AtBoard b = dao.atGetBoard(Integer.valueOf(at_no), true);
		req.setAttribute("b", b);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("searchOption", searchOption);
		if(searchOption) {
			req.setAttribute("keyword", keyword );
			req.setAttribute("type", type );
		}
		return "atboard/atBoardDetail";
	}
}
