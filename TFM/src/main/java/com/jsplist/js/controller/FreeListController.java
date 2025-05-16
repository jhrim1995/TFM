package com.jsplist.js.controller;

import java.io.File;
import java.io.IOException;

import com.jsplist.js.service.CommandProcess;
import com.jsplist.js.service.DeleteFreeListService;
import com.jsplist.js.service.FreeDetailService;
import com.jsplist.js.service.FreeListService;
import com.jsplist.js.service.FreeWriteFormService;
import com.jsplist.js.service.FreeWriteProcessService;
import com.jsplist.js.service.UpdateFreeListFormService;
import com.jsplist.js.service.UpdateFreeListService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@MultipartConfig(fileSizeThreshold = 10 * 1024,
maxFileSize = 1024 * 1024 * 10,
maxRequestSize = 1024 * 1024 * 100)

@WebServlet(name="freeListController" , urlPatterns="*.mvc"
//,initParams=@WebInitParam(name="uploadDir", value="upload")
)
public class FreeListController extends HttpServlet{
	
	private final String PREFIX = "/WEB-INF/";
	private final String SUFFIX = ".jsp";
	
	
//	@Override
//	public void init() throws ServletException {
//		ServletContext sc = getServletContext();
//		String uploadDir = sc.getInitParameter("uploardDir");
//		String realPath = sc.getRealPath(uploadDir);
//		System.out.println(realPath);
//		
//		File parentFile = new File(realPath);
//		if(! (parentFile.exists() && parentFile.isDirectory())) {
//			parentFile.mkdir();
//		}
//		sc.setAttribute("uploarDir", uploadDir);
//		sc.setAttribute("parentFile", parentFile);
//		System.out.println("init - " + parentFile);
//	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
		
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		System.out.println("URI : " + requestURI + ", ctxPath :"+ contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command :"+command );
		
		String viewPage = null;
		CommandProcess service = null;
		
		if(command.equals("/*.mvc") || command.equals("/freeList.mvc")) {
			service = new FreeListService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/freeDetail.mvc")) {
			service = new FreeDetailService();
			viewPage = service.requestProcess(request, response);
		
		} else if(command.equals("/updateFreeListProcess.mvc")) {
			service = new UpdateFreeListService();
			viewPage = service.requestProcess(request, response);
		
		}else if(command.equals("/updateFreeListForm.mvc")) {
			service = new UpdateFreeListFormService();
			viewPage = service.requestProcess(request, response);
		
		} else if(command.equals("/deleteFreeList.mvc")) {
			service = new DeleteFreeListService();
			viewPage = service.requestProcess(request, response);
		
		}else if(command.equals("/freewriteForm.mvc")) {
			service = new FreeWriteFormService();
			viewPage = service.requestProcess(request, response);
		
		} else if(command.equals("/freewriteProcess.mvc")) {
			service = new FreeWriteProcessService();
			viewPage = service.requestProcess(request, response);
		
		}
		
		
		
		if(viewPage != null) {
			 String view = viewPage.split(":")[0];
			 System.out.println("view : "+ view);
			 
			 if(view.equals("r") || view.equals("redirect")) {
				 response.sendRedirect(viewPage.split(":")[1]);
			 }else if (view.equals("f") || view.equals("foward")) {
				 RequestDispatcher rd = 
				 request.getRequestDispatcher(viewPage.split(":")[1]);
				 rd.forward(request, response);
			 } else {
				 RequestDispatcher rd = 
						 request.getRequestDispatcher(PREFIX +view+SUFFIX);
						 rd.forward(request, response);
			 }
		}
		
	}
	
	
	
}
