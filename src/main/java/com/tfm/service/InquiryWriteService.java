package com.tfm.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import com.tfm.qna.dao.FaqDao;
import com.tfm.qna.dao.InquiryDao;
import com.tfm.vo.Faq;
import com.tfm.vo.Inquiry;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class InquiryWriteService implements CommandProcess{

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		Inquiry inquiry = new Inquiry();
		System.out.println(System.getProperty("java.io.tmpdir"));
		
		request.setCharacterEncoding("utf-8");
		String contentType = request.getContentType();
		
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("id");
		String nickname = (String) session.getAttribute("nickname");
		
		inquiry.setId(loginId);
		inquiry.setWriter(nickname);
		
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
							inquiry.setTitle(paramValue);
							
						}else if(paramName.equals("writer")) {
							inquiry.setWriter(paramValue);
							
						}else if(paramName.equals("content")) {
							inquiry.setContent(paramValue);
							
						}
					}
				
				}
			
			InquiryDao dao = new InquiryDao();
			dao.insertInquiry(inquiry);
			
			}
		
			return "r:inquirylist.mvc";
		}
	}


