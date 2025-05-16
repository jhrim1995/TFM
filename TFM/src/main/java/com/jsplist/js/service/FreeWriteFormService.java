package com.jsplist.js.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.jsplist.js.dao.FreeListDao;
import com.jsplist.js.vo.FreeList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FreeWriteFormService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		
		return "free/freeWriteForm";
	}

}
