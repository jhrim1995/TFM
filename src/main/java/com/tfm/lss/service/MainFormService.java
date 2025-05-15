package com.tfm.lss.service;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainFormService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		return "f:WEB-INF/main01.jsp";
	}

	
}
