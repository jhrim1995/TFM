package com.jsplist.js.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.jsplist.js.dao.FreeListDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteFreeListService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String sNo = request.getParameter("no");
		String pageNum = request.getParameter("pageNum");
		
		if(sNo == null || sNo.equals("") || 
				 pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근...')");
			out.println("	history.back();");
			out.println("</script>");
		}	
		
		FreeListDao dao = new FreeListDao();
		int no = Integer.parseInt(sNo);
		dao.deletefreelist(no);
		
		String url = "freeList.mvc?pageNum=" + pageNum;
		System.out.println(url);
		
		return "r:" + url;
	}

}
