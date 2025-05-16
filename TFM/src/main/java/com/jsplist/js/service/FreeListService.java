package com.jsplist.js.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jsplist.js.dao.FreeListDao;
import com.jsplist.js.vo.FreeList;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FreeListService  implements CommandProcess{
	
	private static final int PAGE_SIZE = 10;
	
	private static final int PAGE_GROUP =10;
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNum = request.getParameter("pageNum");
		String keyword = request.getParameter("keyword");
		String type = request.getParameter("type");
		
		
		if(pageNum == null) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
							//1        //10    -      //9
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE -1);//1
		int endRow = startRow + PAGE_SIZE -1;
		                 //1      //9  = 10 
		FreeListDao dao = new FreeListDao();
		ArrayList<FreeList> fList= null;
		int listCount = 0;
		
		boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals(""))? false : true;
		
		if(!searchOption) {
			fList = dao.freeList(startRow, endRow);
			listCount = dao.getfreelistCount();
		}else {
			
			fList = dao.searchList(type, keyword, startRow, endRow);
			listCount = dao.getfreelistCount();
			
		}
		
		int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP  + 1 
				- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		int endPage = startPage + PAGE_GROUP -1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		request.setAttribute("fList", fList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageGroup", PAGE_GROUP);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
		}
		
		return "free/freeList"; 
	}
	
	
	
	

}
