package com.tfm.ajax.action;

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
		
	//	String recommend = request.getParameter("recommend");
		String r_no = request.getParameter("r_no");
		
		HashMap<String, Integer> map = null;
		RvBoardDao dao = new RvBoardDao();
		
		try {
		map = dao.getRecommend(Integer.parseInt(r_no));
		
		if(map == null) {
			map = new HashMap<>();
			map.put("recommend", 0);
		}
		} catch (Exception e){
			map = new HashMap<>();
			map.put("recommend", 0);
			e.printStackTrace();
		
		}
		
		
		Gson gson = new Gson();
		
		String result = gson.toJson(map);
		System.out.println("RecommendAction - result : " + result);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
		
	}

}
