package com.jspstudy.ch06.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jspstudy.ch06.dao.BoardDao;
import com.jspstudy.ch06.dao.BoardDao01;
import com.jspstudy.ch06.vo.Board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/boardList")
public class BoardListController extends HttpServlet {
	
	// 한 페이지에 보여줄 게시 글 수 
	private static final int PAGE_SIZE = 10;
	
	// 한 페이지에 보여줄 페이지 그룹의 수
	// [이전] 1 2 3 4 .... 10 [다음]
	private static final int PAGE_GROUP = 10;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {

		// 몇 페이지를 원하는지 알아야	
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		
		// startRow를 계산할 때 어떤 데이터가 필요?
		// 1 -> 1, 10, 2 -> 11, 20,  5 -> 41, 50, 7 -> 61, 70
		// 1 - 1	2 - 11	3 - 21	 5 - 41		7 - 61	 
		// 1 - 10	2 - 20	3 - 30	 5 - 50		7 - 70 	  
		// 1 - 1	2 - 21	3 - 41	 5 - 81
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		int endRow = startRow + PAGE_SIZE - 1;
						
		// DB 코드 = DAO를 이용해서 게시글 리스트 가져오면 됨
		BoardDao dao = new BoardDao();
		ArrayList<Board> bList = dao.boardList(startRow, endRow);
		int listCount = dao.getBoardCount();
		
		// 전체 페이지수 - 계산을 위해서 필요한 데이터
		// 153 / 10 = 15  + 1(어느 때 해야하는지) 
		int pageCount = listCount / PAGE_SIZE 
				+ (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		// 페이지 네이션 시작 페이지
		// 1~10 - 1, 11~20 - 11  21~30 - 21
		// 1 / 10 * 10 = 0, 5 / 10 * 10 = 0   9 / 10 * 10 = 0 + 1
		// 21 / 10 * 10 = 20, 25 / 10 * 10 = 20   29 / 10 * 10 = 20 + 1
		// 10 / 10 * 10 + 1 = 11(1)   30 / 10 * 10 + 1 = 31(21)
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
				- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		// 페이지 네이션 마지막 페이지
		int endPage = startPage + PAGE_GROUP - 1;
		
		// 전체 페이지가 17페이지 = endPage ?
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		// DB에 가서 게시글 리스트(모델)를 읽어왔음
		// 뷰에가서 출력할 모델을 담는다.
		request.setAttribute("bList", bList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageGroup", PAGE_GROUP);		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);		
		
		// 뷰로 제어를 이동한다.
		RequestDispatcher rd = 
				request.getRequestDispatcher("/WEB-INF/board/boardList.jsp");
		rd.forward(request, response);
		
	} // end doGet()
	
} // end BoardListController01
