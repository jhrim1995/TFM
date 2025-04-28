package com.jspstudy.ch06.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.jspstudy.ch06.dao.BoardDao;
import com.jspstudy.ch06.vo.Board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/boardDetail")
public class BoardDetailController extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		// 사용자가 몇 번 게시글을 보고 싶은지 파악
		String no = request.getParameter("no");
		String pageNum = request.getParameter("pageNum");
		
		// no, pageNum 이 없으면 우리가 의도한 것이 아님
		// 직접 응답을 만들어서 (js로 응답)알림하고 게시글 리스트로 보냄
		if(no == null || no.equals("") || pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근...')");
			//out.println("	history.back();");
			out.println("	location.href='boardList'");
			out.println("</script>");
		}
		
		// 그 게시 글을 DB에서 가져와서 뷰에서 출력
		// DB 코드 = DAO를 이용해서 게시 글 하나를 가져오면 됨
		BoardDao dao = new BoardDao();
		
		// DB에 가서 no에 해당하는 게시 글 하나를 읽어왔음
		Board b = dao.getBoard(Integer.parseInt(no));
		
		// 뷰에가서 출력할 결과 데이터 => 모델
		request.setAttribute("board", b);
		request.setAttribute("pageNum", pageNum);
		
		// 뷰로 제어를 이동한다.
		RequestDispatcher rd = 
				request.getRequestDispatcher("/WEB-INF/board/boardDetail.jsp");
		rd.forward(request, response);
		
	}
	
	
}
