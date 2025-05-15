package com.tfm.at.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.google.gson.Gson;
import com.tfm.at.dao.AtBoardDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AtRecmAction implements AtAjaxProcess {
	public void ajaxProcess(
			HttpServletRequest req, HttpServletResponse resp) 
					throws ServletException, IOException {
		String recm = req.getParameter("recm");
		String no = req.getParameter("at_no");
		HashMap<String, Integer> map = null;
		AtBoardDao dao = new AtBoardDao();
		map = dao.atGetRecm(Integer.parseInt(no), recm);
		
		Gson gson = new Gson();
		String result = gson.toJson(map);
		System.out.println("RecmAction - result : "+ result);
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(result);
		
	}
}
