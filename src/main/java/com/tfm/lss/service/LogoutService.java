package com.tfm.lss.service;

import java.io.IOException;

import com.tfm.at.service.AtCommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutService implements CommandProcess, AtCommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().invalidate();
		
		return "r:main.mvc";
	}
	
}
