<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 


<head>
<link href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/qnaformcheck.js"></script>
  <style>
    body {
      background-color: #fdfaf6;
    }

    .custom-title {
      background-color: #fef6e4;
      color: #5e4637;
      border: 2px solid #f0e2c1;
    }

    .form-label {
      font-weight: 600;
      color: #5a4231;
    }

    .form-control:focus {
      border-color: #d2b48c;
      box-shadow: 0 0 0 0.2rem rgba(210, 180, 140, 0.25);
    }

    .btn-primary {
      background-color: #a38c6b;
      border-color: #a38c6b;
    }

    .btn-primary:hover {
      background-color: #8c765a;
    }

    .btn-outline-secondary {
      border-color: #a38c6b;
      color: #a38c6b;
    }

    .btn-outline-secondary:hover {
      background-color: #f5e6ca;
      color: #5a3f2b;
    }
  </style>
</head>


<div class="container my-5">
	<h2 class="text-center mb-4 fw-bold py-3 rounded shadow-sm custom-title">글 작성</h2>
		
		<form action="inquirywriteProcess.mvc" name="writeForm" id="inquirywriteForm" 
					class="row g-3 justify-content-center" method="post" enctype="multipart/form-data">
			
			<div class="col-10 md-10">
				<label for="writer" class="form-label">작성자</label>
				<input type="text" class="form-control" id="writer" name="writer" placeholder="작성자를 입력하기">
			</div>
			
			<div class="col-10 md-10">
				<label for="title" class="form-label">제 목</label>
				<input type="text" class="form-control" id="title" name="title">
			</div>
			
			<div class="col-10 md-10">
				<label for="content" class="form-label">내 용</label>
				<textarea class="form-control" id="content" name="content" rows="10"></textarea>
			</div>
			
			<div class="col-10 md-10 text-center mt-4">
				<input type="submit" class="btn btn-primary me-2" value="등록">
				<input type="button" class="btn btn-outline-secondary" value="목록" onclick="location.href='inquirylist.mvc'">
			</div>
		</form>
	
</div>