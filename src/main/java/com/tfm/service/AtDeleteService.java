package com.tfm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import com.tfm.at.dao.AtBoardDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AtDeleteService implements CommandProcess {

	public String requestProcess(
			HttpServletRequest req, HttpServletResponse resp) 
					throws ServletException, IOException {
		
		String sNo = req.getParameter("at_no");
		String pass = req.getParameter("pass");
		String pageNum = req.getParameter("pageNum");
		String type = req.getParameter("type");
		String keyword = req.getParameter("keyword");
		
		
		if(sNo == null || sNo.equals("") || pass == null || pass.equals("")|| pageNum == null || pageNum.equals("")) {
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println(" alert('잘못된 접근입니다.');");
			out.println(" history.back();");
			out.println("</script>");
			return null;
			}
		
		int no = Integer.parseInt(sNo);
		
		AtBoardDao dao = new AtBoardDao();
		boolean isPassCheck = dao.isPassCheck(no, pass);
		if(! isPassCheck) {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('비밀번호가 틀립니다.');");
			sb.append("history.back();");
			sb.append("</script>");
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("비밀번호가 틀립니다.");
			return null;
		}
		dao.deleteBoard(no);
		
		boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		String url = "atBoardList.mvc?pageNum="+pageNum;
		if(searchOption) {
			keyword = URLEncoder.encode(keyword,"utf-8");
			url += "&type=" + type + "&keyword="+keyword;
		}
		return "r:" + url;
	}
}
