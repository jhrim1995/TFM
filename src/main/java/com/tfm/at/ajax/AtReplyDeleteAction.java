package com.tfm.at.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.tfm.at.dao.AtBoardDao;
import com.tfm.at.vo.AtReply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AtReplyDeleteAction implements AtAjaxProcess {
	@Override
	public void ajaxProcess(
			HttpServletRequest req, HttpServletResponse resp) 
					throws ServletException, IOException {
		String at_no = req.getParameter("at_no");
		String c_no = req.getParameter("c_no");
		
		AtReply r = new AtReply(Integer.parseInt(c_no), Integer.parseInt(at_no), null, null, null);
		AtBoardDao dao = new AtBoardDao();
		dao.deleteReply(r);
		
		ArrayList<AtReply> rList = dao.getReplyList(Integer.parseInt(at_no));
		
		Gson gson = new Gson();
		String result = gson.toJson(rList);
		System.out.println("AtReplyDeleteAction - result : "+ result);
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(result);
		
	}
}
