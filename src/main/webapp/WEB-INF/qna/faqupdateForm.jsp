<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 


<head>
<link href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.bundle.min.js"></script>
</head>

<div>
	<div>
		<div>
			<div>
				<h2>글 수정</h2>
			</div>
		</div>
		
		<form action="faqupdateProcess.mvc" name="updateForm" id="faqupdateForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="no" value="${faq.no }">
			<input type="hidden" name="pageNum" value="${pageNum != null ? pageNum : 1}">
		
		<c:if test="${searchOption }">
			<input type="hidden" name="type" value="${type }">
			<input type="hidden" name="keyword" value="${keyword }">
		</c:if>
			
			<div>
				<label for="writer" class="form-label">작성자</label>
				<input type="text" class="form-control" name="writer" id="writer" value="${faq.writer }">
			</div>
			
			<!-- 비로그인시 숨기기 -->
			<div>
				<label for="password" class="form-label">비밀번호</label>
				<input type="password" class="form-control" name="pass" id="pass">
			</div>
			
			<div>
				<label for="title" class="form-label">제목</label>
				<input type="text" class="form-control" name="title" id="title" value="${faq.title }">
			</div>
			
			<div>
				<label for="content" class="form-label">내용</label>
				<textarea class="form-control" name="content" id="content" rows="10">${faq.content }</textarea>
			</div>
			
			<div class="col-8 offset-md-2 text-center mt-5">
				<input type="submit" value="수정" class="btn btn-primary">
				<c:if test="${not searchOption }">
					<input class="btn btn-primary" type="button" value="목록" onclick="location.href='faqlist.mvc?pageNum=${pageNum}'">
				</c:if>
				<c:if test="${searchOption }">
				<input class="btn btn-primary" type="button" value="목록" onclick="location.href='faqlist.mvc?pageNum=${pageNum}&type=${type }&keyword=${keyword }'">
				</c:if>
			</div>
		</form>
	</div>
</div>