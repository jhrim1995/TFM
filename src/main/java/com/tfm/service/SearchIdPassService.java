package com.tfm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.tfm.at.service.AtCommandProcess;
import com.tfm.bbs.dao.MemberDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchIdPassService implements CommandProcess, AtCommandProcess{
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String findThing = request.getParameter("findThing");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String telecom = request.getParameter("telecom");
		String phone = request.getParameter("phone");
		
		MemberDao dao = new MemberDao();
		
		if(findThing.equals("id")) {
			
			ArrayList<String> idList = dao.searchIdList(name, telecom, phone);
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if(idList == null || idList.size() <= 0) {
				out.println("<script>");
				out.println(" alert('아이디가 존재하지 않습니다.');");
				out.println(" history.back();");
				out.println("</script>");
				out.close();
				return null;
			}
		
			out.println("<script>");
			out.print(" alert('아이디 : ");
			for(String ids : idList) {
				out.print(ids + " ");
			}
			out.print("'); ");
			out.println(" location.href='loginForm.mvc';");
			out.println("</script>");
			
			return null;
		}
		
		
		boolean checkId = dao.overlapIDCheck(id);
		
		if(!checkId) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('아이디가 존재하지 않습니다.');");
			out.println(" history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		String pass = dao.getPassword(id, name, telecom, phone);
		
		if(pass.equals("")){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('입력된 정보가 잘못되었습니다.');");
			out.println(" history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println(" alert('비밀번호 : "+pass+"');");
		out.println(" location.href= 'loginForm.mvc'");
		out.println("</script>");
		out.close();
		
		return null;
	}
}
