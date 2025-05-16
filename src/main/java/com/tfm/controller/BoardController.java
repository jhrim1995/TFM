package com.tfm.controller;

import java.io.File;
import java.io.IOException;

import com.tfm.service.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@MultipartConfig(fileSizeThreshold = 1024 * 10, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 10 * 10)
@WebServlet(name="BoardController",urlPatterns="*.mvc")
public class BoardController extends HttpServlet {

	private final String PREFIX = "/WEB-INF/index.jsp?body=";
	private final String SUFFIX = ".jsp";
	
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		String viewPage = null;
		CommandProcess service = null;
		
		if(command.equals("/*.mvc") || command.equals("/main.mvc")){
			service = new MainFormService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/loginForm.mvc")) {  // 로그인
			service = new LoginService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/joinMemberForm.mvc")) {
			service = new JoinMemberFormService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/joinMember.mvc")) {
			service = new JoinMemberService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/loginCheck.mvc")) {
			service = new LoginCheckService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/logout.mvc")) {
			service = new LogoutService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/myProfileForm.mvc")) {
			service = new MyProfileFormService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/updateProfile.mvc")) {
			service = new UpdateProfileService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/cancleMembership.mvc")) {
			service = new CancleMembershipService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/searchIdForm.mvc")) {
			service = new SearchIdFormService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/searchPassForm.mvc")) {
			service = new SearchPassFormService();
			viewPage = service.requestProcess(request, response);
		}else if(command.equals("/searchIdPass.mvc")) {
			service = new SearchIdPassService();
			viewPage = service.requestProcess(request, response);
		}else if (command.equals("/faqlist.mvc")) {  // Q&A
	        service = new FaqService();
	        viewPage = service.requestProcess(request, response);
	    } else if (command.equals("/faqDetail.mvc")) {
			service = new FaqDetailService();
			viewPage = service.requestProcess(request, response);
	    } else if (command.equals("/faqwriteForm.mvc")) {
			service = new FaqWriteFormService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/faqwriteProcess.mvc")) {
			service = new FaqWriteService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/faqupdateForm.mvc")) {
			service = new FaqUpdateFormService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/faqupdateProcess.mvc")) {
			service = new FaqUpdateService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/faqdeleteProcess.mvc")) {
			service = new FaqDeleteService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/inquirylist.mvc")) {
	        service = new InquiryService();
	        viewPage = service.requestProcess(request, response);
		} else if (command.equals("/inquiryDetail.mvc")) {
	        service = new InquiryDetailService();
	        viewPage = service.requestProcess(request, response);
		} else if (command.equals("/inquirywriteForm.mvc")) {
			service = new InquiryWriteFormService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/inquirywriteProcess.mvc")) {
			service = new InquiryWriteService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/inquiryupdateForm.mvc")) {
			service = new InquiryUpdateFormService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/inquiryupdateProcess.mvc")) {
			service = new InquiryUpdateService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/inquirydeleteProcess.mvc")) {
			service = new InquiryDeleteService();
			viewPage = service.requestProcess(request, response);	
		}else if(command.equals("/atBoardList.mvc")) {  // 기사 게시판
			service = new AtBoardListService();
			viewPage = service.requestProcess(request, response);
		} else if(command.equals("/atBoardDetail.mvc")) {
			service = new AtBoardDetailService();
			viewPage = service.requestProcess(request, response);
		} else if(command.equals("/atWriteForm.mvc")) {
			viewPage = "atboard/atWriteForm";
		} else if(command.equals("/atWriteProcess.mvc")) {
			service = new AtBoardWriteService();
			viewPage = service.requestProcess(request, response);
		} else if(command.equals("/atUpdateForm.mvc")) {
			service = new AtUpdateFormService();
			viewPage = service.requestProcess(request, response);
		} else if(command.equals("/atUpdateProcess.mvc")) {
			service = new AtUpdateService();
			viewPage = service.requestProcess(request, response);
		} else if(command.equals("/atDeleteProcess.mvc")) {
			service = new AtDeleteService();
			viewPage = service.requestProcess(request, response);
		} else if(command.equals("/rvBoardList.mvc")){  // 리뷰 게시판
			service = new RvBoardListService();
			viewPage = service.requestProcess(request, response);		
		} else if(command.equals("/rvBoardDetail.mvc")) {
			service = new RvBoardDetailService();
			viewPage = service.requestProcess(request, response);
		} else if(command.equals("/rvWriteForm.mvc")) {
			service = new RvBoardWriteFormService();
			viewPage = service.requestProcess(request, response);
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
		} 
		
		
		if(viewPage != null) {
			String view = viewPage.split(":")[0];
			System.out.println("view = " + view);
			if(view.equals("r") || view.equals("redirect")) {
				response.sendRedirect(viewPage.split(":")[1]);
			}else if(view.equals("f")){
				RequestDispatcher rd =  request.getRequestDispatcher(viewPage.split(":")[1]);
				rd.forward(request, response);
			}else {
				RequestDispatcher rd =  request.getRequestDispatcher(PREFIX + view + SUFFIX);
				rd.forward(request, response);
			}
		}
	
	}
}
