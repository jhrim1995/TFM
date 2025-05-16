package com.jspreview.mh.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.jspreview.mh.dao.RvBoardDao;
import com.jspreview.mh.vo.RvBoard;
import com.jspreview.mh.vo.RvReply;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RvBoardDetailService implements CommandProcess{

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 세션을 체크해서 로그인 상태가 아니명 - 경고창 - 로그인 폼으로
		
		  HttpSession session = request.getSession(); boolean isLogin =
		  session.getAttribute("isLogin") != null? (Boolean)
		  session.getAttribute("isLogin") : false; if(! isLogin) { // 로그인 상태가 아니면 직접 응답
		  
		  response.setContentType("text/html; charset= utf-8"); PrintWriter out =
		  response.getWriter(); out.println("<script>");
		  out.println("alert('로그인이 필요한 서비스...')");
		  out.println("location.href='loginForm.mvc'"); out.println("</script>");
		  
		  return null; }
		 
		
		
		
		String r_no = request.getParameter("r_no");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		if(r_no == null || r_no.equals("") || pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근.RvBoardDetailService')");
			out.println("location.href='rvBoardList.mvc'");
			out.println("</script>");
			return null;
		}
		// 검색 게시 글 리스트 - type, keyword 동시에 있으면
		boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals("")) ? false : true;
		
		//게시글을 DB에서 가져와서 뷰에서 출력
		RvBoardDao dao = new RvBoardDao();
		
		// DB에서 r_no에 해당하는 게시 글 하나를 읽어옴
		RvBoard board = dao.getBoard(Integer.valueOf(r_no), true);
		
		// 현재 게시글에 해당하는 댓글 리스트
		ArrayList<RvReply> replyList = dao.getReplyList(Integer.valueOf(r_no));
		
				
		request.setAttribute("board", board);
		request.setAttribute("replyList", replyList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
			request.setAttribute("keyword", keyword);
			request.setAttribute("type", type);
		}
		
		return "rvboard/rvBoardDetail";
		
	}

}
