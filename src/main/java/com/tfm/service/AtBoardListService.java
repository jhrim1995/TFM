package com.tfm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.tfm.at.dao.AtBoardDao;
import com.tfm.vo.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AtBoardListService implements CommandProcess {
	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;

	public String requestProcess(
			HttpServletRequest req, HttpServletResponse resp) {
	
		String pageNum = req.getParameter("pageNum");
		String type = req.getParameter("type");
		String keyword = req.getParameter("keyword");
		
		if(pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		int endRow = startRow + PAGE_SIZE - 1;
		int listCount = 0;
		ArrayList<AtBoard> bList = null;
		AtBoardDao dao = new AtBoardDao();
		
		boolean searchOption = ( type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
		if(! searchOption) {
			listCount = dao.getBoardCount();
			bList = dao.boardList(startRow, endRow);
		} else {
			listCount = dao.getBoardCount(type, keyword);
			bList = dao.searchList(type, keyword, startRow, endRow);
		}
		
		int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1 - (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		int endPage = startPage + PAGE_GROUP - 1;
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		req.setAttribute("bList", bList);
		req.setAttribute("currentPage", currentPage );
		req.setAttribute("pageGroup", PAGE_GROUP );
		req.setAttribute("pageCount", pageCount );
		req.setAttribute("startPage", startPage );
		req.setAttribute("endPage", endPage );
		req.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
			req.setAttribute("keyword", keyword);
			req.setAttribute("type", type );
		}
		return "atboard/atBoardList";
		
	}
}
