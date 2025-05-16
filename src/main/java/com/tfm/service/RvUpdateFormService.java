package com.tfm.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.UUID;

import com.jspreview.mh.dao.RvBoardDao;
import com.tfm.service.CommandProcess;
import com.tfm.vo.RvBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class RvUpdateFormService implements CommandProcess{

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
			out.println("<script>");
			out.println("alert('잘못된 접근.RvBoardUpdateControllerform');");
			out.println("history.back();");
			out.println("</script>");
			return null;
		}
		
			
		RvBoardDao dao = new RvBoardDao();
		
		int r_no = Integer.parseInt(sNo);
		
		boolean isM_idCheck = dao.isM_idCheck(r_no, m_id);
		
		if(! isM_idCheck) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('ID ㄴㄴ');");
			out.println("history.back();");
			out.println("</script>");			
			return null;
		}
		
		boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals("")) ? false : true;
		
		RvBoard board = dao.getBoard(Integer.valueOf(r_no), false);
		
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
		}
				
		return "rvboard/rvUpdateForm";
	}

}
