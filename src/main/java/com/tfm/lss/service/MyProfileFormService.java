package com.tfm.lss.service;

import java.io.IOException;

import com.tfm.bbs.dao.MemberDao;
import com.tfm.bbs.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyProfileFormService implements CommandProcess{
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String id = (String) request.getSession().getAttribute("id");
		
		System.out.println("id = " + id);
		
		MemberDao dao = new MemberDao();
		Member member = dao.myProfileAll(id);
		
		request.setAttribute("Member", member);
		
		
		return "login/updateMember";
	}

}
