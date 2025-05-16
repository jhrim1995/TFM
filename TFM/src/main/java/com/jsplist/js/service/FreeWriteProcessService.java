package com.jsplist.js.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import com.jsplist.js.dao.FreeListDao;
import com.jsplist.js.vo.FreeList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FreeWriteProcessService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FreeList f = new FreeList();
		System.out.println(System.getProperty("java.io.tmpdir"));
		
		request.setCharacterEncoding("utf-8");
		String contentType = request.getContentType();
		
		if(contentType != null &&
				contentType.toLowerCase().startsWith("multipart/")) {
			
			Collection<Part> parts = request.getParts();
			
			for(Part part : parts) {
				String partHeader = part.getHeader("Content-Disposition");
				System.out.println(partHeader);
				System.out.printf("파라미터 : %s, contentType : %s, size : %dByte, \n" ,
						part.getName(), part.getContentType(), part.getSize());
				
				if(partHeader.contains("filename=")) {
					if(part.getSize() > 0) {
						UUID uid = UUID.randomUUID();
						String saveName = uid.toString() + "_" + part.getSubmittedFileName();
						
						File parentFile = 
								(File) request.getServletContext().getAttribute("parentFile");
						
						String savePath = parentFile.getAbsolutePath()
								+File.separator + saveName;
						
						part.write(savePath);
						f.setFile(saveName);
						part.delete();
						
					}else {
						System.out.println("파일이 업로드 되지 않음");
					}
				}else {
					String paramName = part.getName();
					String paramValue = request.getParameter(paramName);
					
					if(paramName.equals("fid")) {
						f.setId(paramValue);
						
					} else if(paramName.equals("ftitle")) {
						f.setTitle(paramValue);
						
					} else if(paramName.equals("fcontent")) {
						f.setContent(paramValue);
					
					} 
				}
				
			}
			
			
		} else {
			System.out.println("전송된 데이터가 multipart/form-data가 아님");
		}
		
		FreeListDao dao = new FreeListDao();
		dao.insertfreelist(f);
		
		
		
		return "r:freeList.mvc";
	}
	
}
