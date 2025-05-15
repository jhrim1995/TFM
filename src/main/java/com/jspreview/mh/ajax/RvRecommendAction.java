package com.jspreview.mh.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.google.gson.Gson;
import com.jspreview.mh.dao.RvBoardDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RvRecommendAction implements AjaxProcess{

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String recommend = request.getParameter("recommend");
		String r_no = request.getParameter("r_no");
		
		HashMap<String, Integer> map = null;
		RvBoardDao dao = new RvBoardDao();
		map = dao.getRecommend(Integer.parseInt(r_no));
		
		Gson gson = new Gson();
		
		String result = gson.toJson(map);
		System.out.println("RecommendAction - result : " + result);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		
	}

}
