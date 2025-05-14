package com.tfm.at.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import com.tfm.at.dao.AtBoardDao;
import com.tfm.at.vo.AtBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AtUpdateService implements AtCommandProcess {

	public String requestProcess(
			HttpServletRequest req, HttpServletResponse resp) 
					throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String sNo = req.getParameter("at_no");
		String pass = req.getParameter("pass");
		String pageNum = req.getParameter("pageNum");
		
		if(sNo == null || sNo.equals("") || pass == null || pass.equals("") || pageNum == null || pageNum.equals("")) {
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println(" alert('잘못된 접근입니다.');");
			out.println(" history.back();");
			out.println("</script>");
			return null;
		}
		
		AtBoardDao dao = new AtBoardDao();
		boolean isPassCheck = dao.isPassCheck(Integer.parseInt(sNo), pass);
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
		AtBoard b = new AtBoard();
		
		String m_id = req.getParameter("m_id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		b.setAt_no(Integer.parseInt(sNo));
		b.setM_id(m_id);
		b.setTitle(title);
		b.setContent(content);
		b.setPass(pass);
		
		dao.updateBoard(b);
		
		String type = req.getParameter("type");
		String keyword = req.getParameter("keyword");
		
		boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		String url = "atBoardList.mvc?pageNum="+pageNum;
		if(searchOption) {
			keyword = URLEncoder.encode(keyword,"utf-8");
			url += "&type=" + type + "&keyword="+keyword;
		}
		
		return "r:" + url;
	}
	
}
