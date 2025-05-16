package com.tfm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import com.tfm.qna.dao.FaqDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FaqDeleteService implements CommandProcess{

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		String fNo = request.getParameter("no");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		if(fNo == null || fNo.equals("") || pageNum == null || pageNum.equals("")) {
			
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
		dao.deleteFaq(no);
		
		boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		
		String url = "faqlist.mvc?pageNum=" + pageNum;
		
		if(searchOption) {
			keyword = URLEncoder.encode(keyword, "utf-8");
			url += "&type=" + type + "&keyword=" + keyword;
		}
				
		return "r:" + url;
	}

}
