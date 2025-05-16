package com.tfm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import com.jspreview.mh.dao.RvBoardDao;
import com.tfm.service.CommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RvDeleteService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String sNo = request.getParameter("r_no");
		String m_id = request.getParameter("m_id");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		if(sNo == null || sNo.equals("") || m_id == null || m_id.equals("")
				|| pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<scirpt>");
			out.println("alert('잘못된 접근.RvBoardDeleteService');");
			out.println("location.href='rvBoardList'");
			out.println("</sciprt>");
			return null;
		}
		
		int r_no = Integer.parseInt(sNo);
		
		RvBoardDao dao = new RvBoardDao();
		boolean isM_idCheck = dao.isM_idCheck(r_no, m_id);
		
		if(! isM_idCheck) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('ID가 맞지 않습니다.RBDC');");
			out.println("history.back();");
			out.println("</script>");				
			return null;
		}
		dao.deleteBoard(r_no);
		
		boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals("")) ? false : true;
		
		String url = "rvBoardList.mvc?pageNum=" + pageNum;
		
		if(searchOption) {
			keyword = URLEncoder.encode(keyword, "utf-8");
			url += "&type=" + type + "&keyword=" + keyword;
		}
		
		return "r:" + url;
		
	}

}
