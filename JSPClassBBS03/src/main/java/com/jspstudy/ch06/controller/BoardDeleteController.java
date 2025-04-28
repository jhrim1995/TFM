package com.jspstudy.ch06.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.jspstudy.ch06.dao.BoardDao;
import com.jspstudy.ch06.vo.Board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteProcess")
public class BoardDeleteController extends HttpServlet {
	/*
	 * 5. 상세보기에서 삭제하기 버튼이 클릭되어 들어온 데이터를 받아서
	 * 	  비번 체크
	 * 		- DB에서 삭제한 후에 게시 글 리스트로 리다이렉트 시킨다.
	 * */	
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
		int no = Integer.parseInt(sNo);
		boolean isPassCheck = dao.isPassCheck(no, pass);
		
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
				
		// DB에서 삭제하고 리다이렉트 시킨다.		
		dao.deleteBoard(no);
		
		// 현재 요청한 자원이 이동되었다고 응답하면서
		// 이동되는 url을 알려주는것 - 리다이렉트
		response.sendRedirect("boardList?pageNum=" + pageNum);
	}	
}



