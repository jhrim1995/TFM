package com.tfm.ajax.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.jspreview.mh.dao.RvBoardDao;
import com.tfm.vo.RvReply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RvReplyUpdateAction implements AjaxProcess{

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String r_No = request.getParameter("r_No");
		String replyM_id = request.getParameter("replyM_id");
		String replyC_con = request.getParameter("replyC_con");
		String replyC_no = request.getParameter("replyC_no");
		
		RvReply reply = new RvReply(Integer.parseInt(r_No), replyM_id, replyC_con);
		reply.setC_no(Integer.parseInt(replyC_no));
		
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
