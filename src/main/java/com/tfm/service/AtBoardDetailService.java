package com.tfm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.tfm.at.dao.AtBoardDao;
import com.tfm.vo.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AtBoardDetailService implements CommandProcess {

	public String requestProcess(
			HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		boolean isLogin = session.getAttribute("isLogin") != null ?
				(Boolean) session.getAttribute("isLogin") : false;
		
		if(! isLogin) {
			
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("	alert('로그인이 필요한 서비스 입니다.')");			
			out.println("	location.href='loginForm.mvc'");
			out.println("</script>");
			
			return null;
		}
		
		String at_no = req.getParameter("at_no");
		String pageNum = req.getParameter("pageNum");
		String type = req.getParameter("type");
		String keyword = req.getParameter("keyword");
		
		System.out.println("넘어왔는지 확인");
		
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
		ArrayList<AtReply> rList = dao.getReplyList(Integer.valueOf(at_no));
		
		req.setAttribute("b", b);
		req.setAttribute("rList", rList);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("searchOption", searchOption);
		if(searchOption) {
			req.setAttribute("keyword", keyword );
			req.setAttribute("type", type );
		}
		return "atboard/atBoardDetail";
	}
}
