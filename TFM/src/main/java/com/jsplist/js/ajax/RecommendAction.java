package com.jsplist.js.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.google.gson.Gson;
import com.jsplist.js.dao.FreeListDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RecommendAction implements AjaxProcess{

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String recommend = request.getParameter("recommend");
		String no = request.getParameter("no");
		
		FreeListDao dao = new FreeListDao();
		
		HashMap<String , Integer> map = dao.getRecommend(Integer.parseInt(no), recommend);
		
		Gson gson = new Gson();
		String result = gson.toJson(map);
		System.out.println("result :" + result);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		
	}

}
