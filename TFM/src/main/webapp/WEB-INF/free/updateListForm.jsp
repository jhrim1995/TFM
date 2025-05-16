<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.7.1.min.js"></script>
  	<script src="js/freelistCheck.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="row my-5" id="global-content">
			<div class="col">					
				<div class="row my-5 text-center">
					<div class="col">				
						<h2 class="fs-3 fw-bold">게시 글 수정하기</h2>
					</div>
				</div>
				
				<form action="updateFreeListProcess.mvc" name="updateListForm" id="updateListForm"
					class="row border-primary g-3" method="post"
					enctype="multipart/form-data">		
					<input type="hidden" name="no" value="${fl.no}">	
					<input type="hidden" name="pageNum" value="${pageNum}">
								
					<div class="col-4 offset-2">
				    <label for="writer" class="form-label">작성자</label>
				    <input type="text" class="form-control" id="id" name="id"
				    	placeholder="작성자를 입력해 주세요" value="${fl.id}">
				  </div>
				  
				 	<div class="col-8 offset-2">
				    <label for="title" class="form-label">제 목</label>
				    <input type="text" class="form-control" id="title" name="title"
				    	value="${fl.title}">
				  </div>
				  
				  <div class="col-8 offset-2">
				    <label for="content" class="form-label">내 용</label>
				    <textarea class="form-control" id="content" name="content" 
				    rows="10">${fl.content}</textarea>
				  </div>
				  
				 	<div class="col-8 offset-2">				   
				    <input type="file" class="form-control" id="file" name="file">
				  </div>		
				  		  
					<div class="col-8 offset-2 text-center mt-5">
						<input type="submit" class="btn btn-primary" value="수정하기">
						&nbsp;&nbsp;
					
					
						<!-- 일반 리스트에서 넘어온 경우 다시 일반 리스트로 보내야함 -->
							<input type="button" class="btn btn-warning" value="목록보기"
								onclick="location.href='freeList.mvc?pageNum=${pageNum}'">
					</div>
				</form>		
			</div>
		</div>
 
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>