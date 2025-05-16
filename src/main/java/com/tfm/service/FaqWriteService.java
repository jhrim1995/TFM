package com.tfm.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.UUID;

import com.tfm.qna.dao.FaqDao;
import com.tfm.qna.vo.Faq;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FaqWriteService implements CommandProcess{

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		Faq faq = new Faq();
		
		request.setCharacterEncoding("utf-8");
		
		String contentType = request.getContentType();
		
		if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			Collection<Part> parts = request.getParts();
			
			for(Part part : parts) {
				String partHeader = part.getHeader("Content-Disposition");
				
				if(partHeader == null) continue;
				
				if(partHeader.contains("filename=")) {
					if(part.getSize() > 0) {
						UUID uid = UUID.randomUUID();
						String saveName = uid.toString() + "_" + part.getSubmittedFileName();
						File parentFile = (File) request.getServletContext().getAttribute("parentFile");
						String savePath = parentFile.getAbsolutePath() + File.separator + saveName;
						part.write(savePath);
						part.delete();
					}
					}else {
						String paramName = part.getName();
						String paramValue = request.getParameter(paramName);
						
						if(paramName.equals("title")) {
							faq.setTitle(paramValue);
							
						}else if(paramName.equals("writer")) {
							faq.setWriter(paramValue);
							
						}else if(paramName.equals("content")) {
							faq.setContent(paramValue);
							
						}
					
				
				}
			}
		
			FaqDao dao = new FaqDao();
			dao.insertFaq(faq);
		
			return "r:faqlist.mvc";
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("	alert('multipart/form-data 형식이 아닙니다.');");
		out.println("	history.back();");
		out.println("</script>");
		return null;
	
	}

}
