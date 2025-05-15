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

public class RvReplyUpdateAction implements AjaxProcess{

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String r_No = request.getParameter("r_No");
		String rvReplyNo = request.getParameter("rvReplyNo");
		String c_Con = request.getParameter("c_Con");
		
		RvReply reply = new RvReply(Integer.parseInt(rvReplyNo),
				Integer.parseInt(r_No), c_Con, null, null);
		
		RvBoardDao dao = new RvBoardDao();
		dao.updateReply(reply);
		
		ArrayList<RvReply> replyList = dao.getReplyList(Integer.parseInt(r_No));
		
		Gson gson = new Gson();
		
		String result = gson.toJson(replyList);
		System.out.println("RvReplyUpdateAction - result : " + result);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		
	}

}
