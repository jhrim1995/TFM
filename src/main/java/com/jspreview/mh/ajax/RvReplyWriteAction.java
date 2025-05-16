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

public class RvReplyWriteAction implements AjaxProcess{

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String r_no = request.getParameter("r_no");
		String m_id = request.getParameter("m_id");
		String c_con = request.getParameter("c_con");
		
		RvReply reply = new RvReply(
				Integer.parseInt(r_no), m_id, c_con);
		
		RvBoardDao dao = new RvBoardDao();
		dao.addReply(reply);
		
		ArrayList<RvReply> replyList = dao.getReplyList(Integer.parseInt(r_no));
		
		Gson gson = new Gson();
		
		String result = gson.toJson(replyList);
		System.out.println("RvReplyWriteAction - result : " + result);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		
	}

}
