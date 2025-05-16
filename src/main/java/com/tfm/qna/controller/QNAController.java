package com.tfm.qna.controller;

import java.io.File;
import java.io.IOException;
import com.tfm.qna.service.CancleMembershipService;
import com.tfm.qna.service.CommandProcess;
import com.tfm.qna.service.FaqDeleteService;
import com.tfm.qna.service.FaqDetailService;
import com.tfm.qna.service.FaqService;
import com.tfm.qna.service.FaqUpdateFormService;
import com.tfm.qna.service.FaqUpdateService;
import com.tfm.qna.service.FaqWriteFormService;
import com.tfm.qna.service.FaqWriteService;
import com.tfm.qna.service.InquiryDeleteService;
import com.tfm.qna.service.InquiryDetailService;
import com.tfm.qna.service.InquiryService;
import com.tfm.qna.service.InquiryUpdateFormService;
import com.tfm.qna.service.InquiryUpdateService;
import com.tfm.qna.service.InquiryWriteFormService;
import com.tfm.qna.service.InquiryWriteService;
import com.tfm.qna.service.JoinMemberFormService;
import com.tfm.qna.service.JoinMemberService;
import com.tfm.qna.service.LoginCheckService;
import com.tfm.qna.service.LoginService;
import com.tfm.qna.service.LogoutService;
import com.tfm.qna.service.MainFormService;
import com.tfm.qna.service.MyProfileFormService;
import com.tfm.qna.service.SearchIdFormService;
import com.tfm.qna.service.SearchIdPassService;
import com.tfm.qna.service.SearchPassFormService;
import com.tfm.qna.service.UpdateProfileService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(fileSizeThreshold = 10 * 1024,
				maxFileSize = 1024 * 1024 * 10,
				maxRequestSize = 1024 * 1024 * 100)

@WebServlet(name="QNAController", urlPatterns="*.mvc")
public class QNAController extends HttpServlet{

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
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		doProcess(request,response);
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
		String command = requestURI.substring(contextPath.length());
		
		
		String viewPage = null;
		CommandProcess service = null;
		
		
		if (command.equals("/*.mvc") || command.equals("/main.mvc")) {
	        service = new MainFormService();
	        viewPage = service.requestProcess(request, response);
	    
	        
		}else if (command.equals("/faqlist.mvc")) {
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
			
			
		}else if(command.equals("/loginForm.mvc")) {
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
		}
		
		
		if (viewPage != null) {
			// 반환값에 접두어가 있으면 처리 (r:, f:)
			if (viewPage.startsWith("r:") || viewPage.startsWith("redirect:")) {
				response.sendRedirect(viewPage.substring(viewPage.indexOf(":") + 1));
			} else if (viewPage.startsWith("f:")) {
				RequestDispatcher rd = request.getRequestDispatcher(viewPage.substring(2));
				rd.forward(request, response);
			} else {
				// index.jsp 템플릿 방식 사용
				RequestDispatcher rd = request.getRequestDispatcher(PREFIX + viewPage + SUFFIX);
				rd.forward(request, response);
			}
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

	}
	
}