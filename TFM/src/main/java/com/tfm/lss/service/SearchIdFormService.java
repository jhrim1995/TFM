package com.tfm.lss.service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchIdFormService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String findThing = "id";
		
		request.setAttribute("findThing", findThing);
		
		return "login/searchIdPass";
	}
	
}
