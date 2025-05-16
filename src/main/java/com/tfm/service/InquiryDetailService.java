package com.tfm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.tfm.qna.dao.FaqDao;
import com.tfm.qna.dao.InquiryDao;
import com.tfm.vo.Faq;
import com.tfm.vo.Inquiry;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class InquiryDetailService implements CommandProcess{

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		boolean isLogin = session.getAttribute("isLogin") != null ? (Boolean) session.getAttribute("isLogin") : false;
		
			
	String no = request.getParameter("no");
	String pageNum = request.getParameter("pageNum");
	String type = request.getParameter("type");
	String keyword = request.getParameter("keyword");
	
	if(no == null || no.equals("") || pageNum == null || pageNum.equals("")) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("	alert('잘못된 접근')");
		out.println("	location.href='inquirylist.mvc'");
		out.println("</script>");
		
		return null;
	}
	
	boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
	
	InquiryDao dao = new InquiryDao();
	
	Inquiry i = dao.getInquiry(Integer.parseInt(no), true);

	if (i == null) {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("	alert('해당 글이 존재하지 않습니다.')");
        out.println("	history.back();");
        out.println("</script>");
        return null;
    }	
	
	request.setAttribute("inquiry", i);
	request.setAttribute("pageNum", pageNum);
	request.setAttribute("searchOption", searchOption);
	
	if(searchOption) {
		request.setAttribute("type", type);
		request.setAttribute("keyword", keyword);
	}
	
	return "qna/inquiryDetail";
	}
}
