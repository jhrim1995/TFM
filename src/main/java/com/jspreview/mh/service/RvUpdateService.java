package com.jspreview.mh.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.UUID;

import com.jspreview.mh.dao.RvBoardDao;
import com.jspreview.mh.vo.RvBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class RvUpdateService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		String sNo = request.getParameter("r_no");
		String m_id = request.getParameter("m_id");
		String pageNum = request.getParameter("pageNum");

		if (sNo == null || sNo.equals("") || m_id == null || m_id.equals("") || pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근.RvBoardUpdateService');");
			out.println("history.back();");
			out.println("</script>");
			return null;
		}
		
		RvBoardDao dao = new RvBoardDao();
		
		boolean isM_idCheck = dao.isM_idCheck(Integer.parseInt(sNo), m_id);
		
		if(! isM_idCheck) {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('ID ㄴㄴ RBUC');");
			sb.append("history.back();");
			sb.append("</script>");
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			return null;
		}
		
		RvBoard board = new RvBoard();

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String area = request.getParameter("area");
			
		
		board.setR_no(Integer.parseInt(sNo));
		board.setM_id(m_id);
		board.setTitle(title);
		board.setContent(content);
		board.setArea(area);
				
		Part part = request.getPart("file1");
		
		if(part.getSize() > 0) {
			UUID uid = UUID.randomUUID();
			String saveName = uid.toString() + "_" + part.getSubmittedFileName();
			
			File parentFile =
					(File) request.getServletContext().getAttribute("parentFile");
			String savePath = parentFile.getAbsolutePath() + File.separator + saveName;
			
			part.write(savePath);
			part.delete();
			board.setFile1(saveName);
		} else {
			System.out.println("파일이 업로드 되지 않음");
		}
		
		dao.rvUpdateBoard(board);
		
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals("")) ? false : true;
		
		String url = "rvBoardList.mvc?pageNum=" + pageNum;
		
		if(searchOption) {
			keyword = URLEncoder.encode(keyword, "utf-8");
			url += "&type=" + type + "&keyword=" + keyword;
		}
		System.out.println("keyword : " + keyword);
		System.out.println("url : " + url);
		
		return "r:" + url;
		
	}

}
