package com.tfm.lss.service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) {
			
		return "login/login";
	}
	
}
