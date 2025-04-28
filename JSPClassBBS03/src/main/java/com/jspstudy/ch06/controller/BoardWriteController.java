package com.jspstudy.ch06.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import com.jspstudy.ch06.dao.BoardDao;
import com.jspstudy.ch06.vo.Board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

// Servlet 3.0 부터 지원하는 Part API 사용 -> 파일 업로드 처리
@MultipartConfig(fileSizeThreshold = 10 * 1024,
			maxFileSize = 1024 * 1024 * 10,
			maxRequestSize = 1024 * 1024 * 100)
@WebServlet("/writeProcess")
public class BoardWriteController extends HttpServlet {
	
	private static String uploadDir;
	private static File parentFile;
	
	// 서블릿 초기화 메서드 사용
	@Override
	public void init() throws ServletException {
		
		// jsp - application => ServletContext
		uploadDir = getServletContext().getInitParameter("uploadDir");
		String realPath = getServletContext().getRealPath(uploadDir);
		System.out.println("realPath : " + realPath);
		
		parentFile = new File(realPath);
		if(! (parentFile.exists() && parentFile.isDirectory())) {
			parentFile.mkdir();
		}
		System.out.println("init - " + parentFile);
	}
	
	/* 5. 폼에서 들어온 데이터를 받아서 DB에 저장한다.
	 * 		- writeProcess
	 * 		- 사용자 폼에 입력한 데이터를 받아서
	 * 		- DB에 저장한 후에 게시 글 리스트로 리다이렉트 시킨다.
	 * */	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		Board board = new Board();
		System.out.println(System.getProperty("java.io.tmpdir"));
		
		// 파일이 업로드 되는 경우, 그렇지 않은 경우
		request.setCharacterEncoding("utf-8");
		String contentType = request.getContentType();
		
		if(contentType != null 
				&& contentType.toLowerCase().startsWith("multipart/")) {
			
			// 요청 본문으로 들어오는 데이터를 Part 리스트로 받음
			Collection<Part> parts = request.getParts();
			
			for(Part part : parts) {
			
				String partHeader = part.getHeader("Content-Disposition");
				System.out.println(partHeader);
				System.out.printf("파라미터 : %s, contentType : %s, size : %dByte, \n",
						part.getName(), part.getContentType(), part.getSize());
				
				// 파일 데이터라면 - file 컨트롤에서 전송되 데이터
				if(partHeader.contains("filename=")) { // 현재 part 파일 데이터면
					if(part.getSize() > 0) {
						
						// 파일 이름이 중복 방지
						UUID uid = UUID.randomUUID();
						String saveName = uid.toString() + "_" + part.getSubmittedFileName();
						String savePath = parentFile.getAbsolutePath() 
								+ File.separator + saveName;

						part.write(savePath);						
						board.setFile1(saveName);
						part.delete();
						
					} else {
						System.out.println("파일이 업로드 되지 않음");
					}					
					
				} else { // 현재 part가 일반 폼 컨트롤에 입력된 데이터
				
					String paramName = part.getName();
					String paramValue = request.getParameter(paramName);
					
					if(paramName.equals("title")) {
						board.setTitle(paramValue);
						
					} else if(paramName.equals("writer")) {
						board.setWriter(paramValue);
						
					} else if(paramName.equals("content")) {
						board.setContent(paramValue);
						
					} else if(paramName.equals("pass")) {
						board.setPass(paramValue);
					}										
				}				
			} // end for(Part part : parts)		
			
		} else { // multipart/form-data 가 아니라면
			System.out.println("전송된 데이터가 multipart/form-data가 아님");
		}		
			
		// DB에 저장한 후에 게시 글 리스트로 리다이렉트 시킨다.
		BoardDao dao = new BoardDao();
		dao.insertBoard(board);
		
		// 현재 요청한 자원이 이동되었다고 응답하면서
		// 이동되는 url을 알려주는것 - 리다이렉트
		response.sendRedirect("boardList");
	}
}



