package com.jspreview.mh.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.jspreview.mh.dao.RvBoardDao;
import com.jspreview.mh.vo.RvReply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RvReplyDeleteAction implements AjaxProcess{

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String r_No = request.getParameter("r_No");
		String replyC_no = request.getParameter("replyC_no");
		
		RvReply reply = new RvReply(Integer.parseInt(replyC_no),
				Integer.parseInt(r_No), null, null, null);
		
		RvBoardDao dao = new RvBoardDao();
		dao.deleteReply(reply);
		
		ArrayList<RvReply> replyList = dao.getReplyList(Integer.parseInt(r_No));
		
		Gson gson = new Gson();
		
		String result = gson.toJson(replyList);
		System.out.println("RvReplyDeleteAction - result : " + result);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
