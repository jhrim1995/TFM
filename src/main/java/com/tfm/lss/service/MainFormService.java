package com.tfm.lss.service;

import java.io.IOException;

import com.tfm.at.service.AtCommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainFormService implements CommandProcess, AtCommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "f:WEB-INF/main01.jsp";
	}
	
}
