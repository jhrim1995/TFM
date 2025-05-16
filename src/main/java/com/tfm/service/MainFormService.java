package com.tfm.service;

import java.io.IOException;
import java.util.Iterator;

<<<<<<< HEAD:src/main/java/com/tfm/lss/service/MainFormService.java

import com.tfm.at.service.AtCommandProcess;

=======
>>>>>>> Seok:src/main/java/com/tfm/service/MainFormService.java
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainFormService implements CommandProcess, AtCommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		return "f:WEB-INF/main01.jsp";
	}

	
}
