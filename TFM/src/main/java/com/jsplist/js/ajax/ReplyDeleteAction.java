package com.jsplist.js.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.jsplist.js.dao.FreeListDao;
import com.jsplist.js.vo.Reply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReplyDeleteAction implements AjaxProcess{

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cNo = request.getParameter("replyNo");
		System.out.println("cNo :"+cNo);
		String fNo = request.getParameter("fNo");
		System.out.println("fNo :" +fNo);
	
		
		Reply reply = new Reply(Integer.parseInt(cNo), Integer.parseInt(fNo) ,null , null, null);
		
		FreeListDao dao = new FreeListDao();
		dao.deleteReply(reply);
		System.out.println("1");
		
		List<Reply> replyList = dao.getreplyList(Integer.parseInt(fNo));
		System.out.println("2");
		
		Gson gson = new Gson();
		String result = gson.toJson(replyList);
		System.out.println(result);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}

}
