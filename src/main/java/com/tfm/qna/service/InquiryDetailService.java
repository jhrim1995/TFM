package com.tfm.qna.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.tfm.qna.dao.FaqDao;
import com.tfm.qna.dao.InquiryDao;
import com.tfm.qna.vo.Faq;
import com.tfm.qna.vo.Inquiry;

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
		
		// 로그인상태 확인 코드 어떻게 활용할지 파악
		/*
		 * if(! isLogin) { response.setContentType("text/html; charset=utf-8");
		 * PrintWriter out = response.getWriter(); out.println("<script>");
		 * out.println("	alert('로그인이 필요한 기능입니다.')");
		 * out.println("	location.href='loginForm.mvc'"); out.println("</script>"); }
			return null;
	}
*/	
		 	
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
