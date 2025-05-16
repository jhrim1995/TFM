package com.tfm.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import com.jspreview.mh.dao.RvBoardDao;
import com.tfm.service.CommandProcess;
import com.tfm.vo.RvBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class RvBoardWriteService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RvBoard board = new RvBoard();
		System.out.println(System.getProperty("java.io.tmpdir"));
		
		request.setCharacterEncoding("utf-8");
		String contentType = request.getContentType();
		
		if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			
			Collection<Part> parts = request.getParts();
			
			for(Part part : parts) {
				String partHeader = part.getHeader("Content-Disposition");
				System.out.println(partHeader);
				System.out.printf("파라미터 : %s, contentType : %s, size : %dByte, \n",
						part.getName(), part.getContentType(), part.getSize());
				
				if(partHeader.contains("filename=")) {
					if(part.getSize() > 0) {
						UUID uid = UUID.randomUUID();												
						String saveName = uid.toString() + "_" +part.getSubmittedFileName();
						
						File parentFile = 
								(File) request.getServletContext().getAttribute("parentFile");
						String savePath = parentFile.getAbsolutePath() + File.separator + saveName;
						
						part.write(savePath);
						part.delete();
						board.setFile1(saveName);
					} else {
						System.out.println("파일이 업로드 되지 않음");
					}
				} else {
					String paramName = part.getName();
					String paramValue = request.getParameter(paramName);
					
					if(paramName.equals("m_id")) {
						board.setM_id(paramValue);
						
					} else if(paramName.equals("title")) {
						board.setTitle(paramValue);
						
					} else if(paramName.equals("content")) {
						board.setContent(paramValue);
						
					} else if(paramName.equals("area")) {
						board.setArea(paramValue);
					}
				}
			}
			System.out.println(board.getTitle() + "," + board.getM_id());
		} else {
			System.out.println("폼에서 전송된 요청이 multipart/form-data가 아님");
		}
		RvBoardDao dao = new RvBoardDao();
		dao.insertBoard(board);
		
		return "r:rvBoardList.mvc";
	}

}
