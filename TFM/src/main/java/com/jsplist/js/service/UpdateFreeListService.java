package com.jsplist.js.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import com.jsplist.js.dao.FreeListDao;
import com.jsplist.js.vo.FreeList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class UpdateFreeListService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String sNo = request.getParameter("no");
		String pageNum = request.getParameter("pageNum");
		
		if(sNo == null || sNo.equals("") 
				|| pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근...')");
			out.println("	history.back();");
			out.println("</script>");
		}	
		
		FreeListDao dao = new FreeListDao();
		
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		FreeList fl = new FreeList();
		fl.setNo(Integer.parseInt(sNo));
		fl.setId(id);
		fl.setTitle(title);
		fl.setContent(content);
		
		Part part = request.getPart("file");
		if(part.getSize() > 0) {
			UUID uid = UUID.randomUUID();
			String saveName = uid.toString() + "-" + part.getSubmittedFileName();
			File parentFile = (File) request.getServletContext().getAttribute("parentFile");
			String savePath = parentFile.getAbsolutePath() + File.separator + saveName;
			
			part.write(savePath);
			part.delete();
			fl.setFile(saveName);
		}else {
			System.out.println("파일없음");
		}
		dao.updatefreelist(fl);
		
		String url = "freeList.mvc?pageNum=" + pageNum;
		System.out.println(url);
		
		
		return "r:"+url;
	}

}
