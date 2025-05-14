package com.tfm.qna.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import com.tfm.qna.dao.FaqDao;
import com.tfm.qna.vo.Faq;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FaqUpdateService implements CommandProcess{

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String fNo = request.getParameter("no");
		String pass = request.getParameter("pass");
		String pageNum = request.getParameter("pageNum");
		
		if(fNo == null || fNo.equals("") || pass == null || pass.equals("") || pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('잘못된 접근');");
			out.println(" history.back();");
			out.println("</script>");
			return null;
		}
		
		FaqDao dao = new FaqDao();
		boolean isPassCheck = dao.isPassCheck(Integer.parseInt(fNo), pass);
		if(! isPassCheck) {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append(" alert('비밀번호가 다름');");
			sb.append(" history.back();");
			sb.append("</script>");
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			return null;
		}
		
		Faq faq = new Faq();
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		faq.setNo(Integer.parseInt(fNo));
		faq.setTitle(title);
		faq.setWriter(writer);
		faq.setPass(pass);
		faq.setContent(content);
		
		dao.updateFaq(faq);
		
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		
		String url = "faqlist.mvc?pageNum=" + pageNum;
		
		if(searchOption) {
			keyword = URLEncoder.encode(keyword, "utf-8");
			url += "&type=" + type + "&keyword=" + keyword;
		}
		
		return "r:" + url;
	}

}
