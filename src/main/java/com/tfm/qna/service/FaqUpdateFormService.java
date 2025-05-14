package com.tfm.qna.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.tfm.qna.dao.FaqDao;
import com.tfm.qna.vo.Faq;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FaqUpdateFormService implements CommandProcess{

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		String fNo = request.getParameter("no");
		String pass = request.getParameter("pass");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		if(fNo == null || fNo.equals("") || pass == null || pass.equals("") || pageNum == null || pageNum.equals("")) {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('비정상적인 접근');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}
		
		FaqDao dao = new FaqDao();
		int no = Integer.parseInt(fNo);
		boolean isPassCheck = dao.isPassCheck(no, pass);
		
		if(! isPassCheck) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('비밀번호 틀림');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}
	
		boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		
		Faq faq = dao.getFaq(Integer.valueOf(no), false);
		
		request.setAttribute("faq", faq);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);
		
		
		if(searchOption) {
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
		}
		return "qna/faqupdateForm";
	}
}
