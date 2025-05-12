package com.tfm.lss.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.tfm.bbs.dao.MemberDao;
import com.tfm.bbs.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateProfileService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String oldPass = request.getParameter("oldPass");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String telecom = request.getParameter("telecom");
		String phone = request.getParameter("phone");
		
		MemberDao dao = new MemberDao();
		
		String oldRealPass = dao.getPassword(id);
		
		if(!oldPass.equals(oldRealPass)) {
			PrintWriter out = response.getWriter();
			out.println();
		}
		
		
		return null;
	}
	
}
