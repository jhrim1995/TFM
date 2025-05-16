package com.jsplist.js.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.jsplist.js.dao.FreeListDao;
import com.jsplist.js.vo.FreeList;
import com.jsplist.js.vo.Reply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FreeDetailService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String no = request.getParameter("no");
			String pageNum = request.getParameter("pageNum");
			String type = request.getParameter("type");
			String keyword = request.getParameter("keyword");
			
			if(no == null || no.equals("") 
					|| pageNum == null || pageNum.equals("")
					) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("	alert('잘못된 접근...')");
				out.println("	history.back();");
				out.println("</script>");
				
				return null;
			}
			
			boolean searchOption = (type == null || type.equals("") ||
					keyword == null || keyword.equals("")) ? false : true;
			
			
			FreeListDao dao = new FreeListDao();
			
			FreeList f = dao.getfreeList(Integer.parseInt(no), true);
			
			ArrayList<Reply> replyList = dao.getreplyList(f.getNo());
			
			request.setAttribute("f", f);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("replyList", replyList);
			request.setAttribute("searchOption", searchOption);
			
			if(searchOption) {
				request.setAttribute("type", type);
				request.setAttribute("keyword", keyword);
			}
		
		return "free/freeDetail";
	}

}
