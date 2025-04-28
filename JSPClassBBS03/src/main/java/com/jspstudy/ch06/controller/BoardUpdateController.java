package com.jspstudy.ch06.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

@MultipartConfig(fileSizeThreshold = 1024 * 10,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 10 * 10)
@WebServlet("/updateProcess")
public class BoardUpdateController extends HttpServlet {

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
	

	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
				
		String sNo = request.getParameter("no");
		String pass = request.getParameter("pass");
		String pageNum = request.getParameter("pageNum");
		
		// no, pageNum 이 없으면 우리가 의도한 것이 아님
		// 직접 응답을 만들어서 (js로 응답)알림하고 게시글 리스트로 보냄
		if(sNo == null || sNo.equals("") || pass == null || pass.equals("") 
				|| pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근...')");
			//out.println("	history.back();");
			out.println("	location.href='boardList'");
			out.println("</script>");
		}			
		
		// 비밀번호 체크
		// 사용자가 입력한 비번과 DB 게시글의 비번이 맞는지 체크
		BoardDao dao = new BoardDao();		
		boolean isPassCheck = dao.isPassCheck(Integer.parseInt(sNo), pass);
		
		// 틀리면,- 경고 창 띄우고 종료	
		if(!isPassCheck) {
			// 경고 창을 띄우기 위해서 자바스크립트로 응답
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('비밀번호가 틀립니다.');");
			out.println("	history.back();");
			out.println("</script>");
			out.close();
			return;
		}
				
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		// 하나의 게시 글 정보를 Board 객체로 담아서 전달
		Board board = new Board();
		board.setNo(Integer.parseInt(sNo));
		board.setTitle(title);
		board.setWriter(writer);
		board.setPass(pass);
		board.setContent(content);
		
		// 파일 처리
		Part part = request.getPart("file1");
		if(part.getSize() > 0) { // 파일이 업로드 된 경우
			UUID uid = UUID.randomUUID();
			String saveName = uid.toString() + "_" + part.getSubmittedFileName();
			String savePath = parentFile.getAbsolutePath() + File.separator + saveName;
			
			part.write(savePath);
			part.delete();
			board.setFile1(saveName);
		} else {
			System.out.println("파일이 업로드 되지 않음.");
		}
		
		// DB에 저장한 후에 게시 글을 수정하고 리다이렉트 시킨다.		
		dao.updateBoard(board);
		
		// 현재 요청한 자원이 이동되었다고 응답하면서
		// 이동되는 url을 알려주는것 - 리다이렉트
		response.sendRedirect("boardList?pageNum=" + pageNum);
	}	
}



