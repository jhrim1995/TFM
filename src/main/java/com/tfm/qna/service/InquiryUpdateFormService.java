package com.tfm.qna.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.tfm.qna.dao.FaqDao;
import com.tfm.qna.dao.InquiryDao;
import com.tfm.qna.vo.Faq;
import com.tfm.qna.vo.Inquiry;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InquiryUpdateFormService implements CommandProcess{

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		String iNo = request.getParameter("no");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		if(iNo == null || iNo.equals("") || pageNum == null || pageNum.equals("")) {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('비정상적인 접근');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}
		
		InquiryDao dao = new InquiryDao();
		int no = Integer.parseInt(iNo);
		
		boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		
		Inquiry inquiry = dao.getInquiry(Integer.valueOf(no), false);
		
		request.setAttribute("inquiry", inquiry);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);
		
		
		if(searchOption) {
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
		}
		return "qna/inquiryupdateForm";
	}
}
