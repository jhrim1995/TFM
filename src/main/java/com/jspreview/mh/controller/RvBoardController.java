package com.jspreview.mh.controller;

import java.io.File;
import java.io.IOException;

import com.jspreview.mh.service.RvBoardDetailService;
import com.jspreview.mh.service.RvBoardListService;
import com.jspreview.mh.service.RvBoardWriteService;
import com.jspreview.mh.service.CommandProcess;
import com.jspreview.mh.service.RvDeleteService;
import com.jspreview.mh.service.RvUpdateFormService;
import com.jspreview.mh.service.RvUpdateService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(fileSizeThreshold = 1024 * 10,
						maxFileSize = 1024 * 1024 * 10,
						maxRequestSize = 1024 * 1024 * 10 * 10)
@WebServlet(name="rvBoardController", urlPatterns="*.mvc")	
public class RvBoardController extends HttpServlet{
		
	private final String PREFIX = "/WEB-INF/index.jsp?body=";
	private final String SUFFIX = ".jsp";
	
	// 서블릿 초기화 메서드
	@Override
	public void init() throws ServletException {
		
		ServletContext sc = getServletContext();
		String uploadDir = sc.getInitParameter("uploadDir");	
		String realPath = sc.getRealPath(uploadDir);
		File parentFile = new File(realPath);
		
		if(! (parentFile.exists() && parentFile.isDirectory())) {
			parentFile.mkdir();
		}
		sc.setAttribute("uploadDir", uploadDir);
		sc.setAttribute("parentFile", parentFile);
		System.out.println("init - " + parentFile);		
	}

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		doProcess(request, response);	
	}

	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);	
	}
	
	
	protected void doProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		System.out.println("uri : " + requestURI + ", ctxPath : " + contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : " + command);
		
		CommandProcess service = null;
		String viewPage = null;
		
		if(command.equals("/rvBoardList.mvc") || command.equals("/*.mvc") || command.equals("index.mvc")){
			service = new RvBoardListService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/rvBoardDetail.mvc")) {
			service = new RvBoardDetailService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/rvWriteForm.mvc")) {
			/* service = new RvBoardWriteFormService(); */
			viewPage = "rvboard/rvWriteForm";
			
		} else if(command.equals("/rvWriteProcess.mvc")) {
			service = new RvBoardWriteService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/rvUpdateForm.mvc")) {
			service = new RvUpdateFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/rvUpdateProcess.mvc")) {
			service = new RvUpdateService();
			viewPage = service.requestProcess(request,response);
			
		} else if(command.equals("/rvDeleteProcess.mvc")) {
			service = new RvDeleteService();
			viewPage = service.requestProcess(request, response);
			
		} /*
			 * else if (command.equals("/loginForm.mvc")) {
			 *  service = new LoginFormService(); 
			 *  viewPage = service.requestProcess(request, response);
			 * 
			 * } else if (command.equals("/login.mvc")) { 
			 * service = new LoginService();
			 * viewPage = service.requestProcess(request, response);
			 * 
			 * } else if (command.equals("/logout.mvc")) { 
			 * service = new LogoutService();
			 * viewPage = service.requestProcess(request, response);
			 * 
			 * }
			 */
			 
		
		
		
		if(viewPage != null) {
			String view = viewPage.split(":")[0];
			System.out.println("view : " + view);
			
			if(view.equals("r") || view.equals("redirect")) {
				response.sendRedirect(viewPage.split(":")[1]);
			} else {
				RequestDispatcher rd = 
						request.getRequestDispatcher(PREFIX + view + SUFFIX);
				rd.forward(request, response);
			}
		}
		
	}
	
	
	

}
