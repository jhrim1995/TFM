package com.tfm.at.controller;

import java.io.IOException;

import com.tfm.at.service.*;
import com.tfm.lss.service.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="AtBoardController", urlPatterns="*.mvc")
public class AtBoardController extends HttpServlet {
	
	private final String PREFIX = "/WEB-INF/index.jsp?body=";
	private final String SUFFIX = ".jsp";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String reqURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		System.out.println("uri : "+reqURI+", ctxPath : "+contextPath);
		
		String command = reqURI.substring(contextPath.length());
		System.out.println("command : "+command);
		
		AtCommandProcess service = null;
		String viewPage = null;
		
		if(command.equals("/atBoardList.mvc") || command.equals("/*mvc") || command.equals("/index.mvc")) {
			service = new AtBoardListService();
			viewPage = service.requestProcess(req, resp);
		} else if(command.equals("/atBoardDetail.mvc")) {
			service = new AtBoardDetailService();
			viewPage = service.requestProcess(req, resp);
		} else if(command.equals("/atWriteForm.mvc")) {
			viewPage = "atboard/atWriteForm";
		} else if(command.equals("/atWriteProcess.mvc")) {
			service = new AtBoardWriteService();
			viewPage = service.requestProcess(req, resp);
		} else if(command.equals("/atUpdateForm.mvc")) {
			service = new AtUpdateFormService();
			viewPage = service.requestProcess(req, resp);
		} else if(command.equals("/atUpdateProcess.mvc")) {
			service = new AtUpdateService();
			viewPage = service.requestProcess(req, resp);
		} else if(command.equals("/atDeleteProcess.mvc")) {
			service = new AtDeleteService();
			viewPage = service.requestProcess(req, resp);
		} else if(command.equals("/*.mvc") || command.equals("/main.mvc")){
			service = new MainFormService();
			viewPage = service.requestProcess(req, resp);
		}else if(command.equals("/loginForm.mvc")) {
			service = new LoginService();
			viewPage = service.requestProcess(req, resp);
		}else if(command.equals("/joinMemberForm.mvc")) {
			service = new JoinMemberFormService();
			viewPage = service.requestProcess(req, resp);
		}else if(command.equals("/joinMember.mvc")) {
			service = new JoinMemberService();
			viewPage = service.requestProcess(req, resp);
		}else if(command.equals("/loginCheck.mvc")) {
			service = new LoginCheckService();
			viewPage = service.requestProcess(req, resp);
		}else if(command.equals("/logout.mvc")) {
			service = new LogoutService();
			viewPage = service.requestProcess(req, resp);
		}else if(command.equals("/myProfileForm.mvc")) {
			service = new MyProfileFormService();
			viewPage = service.requestProcess(req, resp);
		}else if(command.equals("/updateProfile.mvc")) {
			service = new UpdateProfileService();
			viewPage = service.requestProcess(req, resp);
		}else if(command.equals("/cancleMembership.mvc")) {
			service = new CancleMembershipService();
			viewPage = service.requestProcess(req, resp);
		}else if(command.equals("/searchIdForm.mvc")) {
			service = new SearchIdFormService();
			viewPage = service.requestProcess(req, resp);
		}else if(command.equals("/searchPassForm.mvc")) {
			service = new SearchPassFormService();
			viewPage = service.requestProcess(req, resp);
		}else if(command.equals("/searchIdPass.mvc")) {
			service = new SearchIdPassService();
			viewPage = service.requestProcess(req, resp);
		}
		
		if(viewPage != null) {
			String view = viewPage.split(":")[0];
			System.out.println("view : " + view);
			if(view.equals("r") || view.equals("redirect")) {
				resp.sendRedirect(viewPage.split(":")[1]);
			} else {
				RequestDispatcher rd = req.getRequestDispatcher(PREFIX + view + SUFFIX);
				rd.forward(req, resp);
			}
		}
	}
}
