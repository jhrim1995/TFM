package com.tfm.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.tfm.bbs.dao.MemberDao;
import com.tfm.bbs.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JoinMemberFormService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "login/joinMember";
	}
	
}
