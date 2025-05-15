package com.tfm.at.ajax;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AtAjaxProcess {
	public void ajaxProcess(
			HttpServletRequest req, HttpServletResponse resp) 
					throws ServletException, IOException;
}
