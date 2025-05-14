package com.tfm.at.service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AtCommandProcess {
	public abstract String requestProcess(
			HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException;
}
