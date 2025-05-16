<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>TFM LIST</title>
  <!-- Bootstrap CDN -->
  	<script src="js/jquery-3.7.1.min.js"></script>
  	<script src="js/freelistCheck.js"></script>
  	<script src="js/replyCheck.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- 상단바 -->
<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom fixed-top">
  <div class="container-fluid">
    <a class="navbar-brand fw-bold" href="index.html">TFM LOGO</a>
    <div class="d-flex gap-3">
      <a href="#" class="btn btn-outline-dark">TFM</a>
      <a href="#" class="btn btn-outline-dark">TFM</a>
      <a href="#" class="btn btn-outline-dark">TFM</a>
      <a href="freeList.mvc" class="btn btn-outline-dark">자유게시판</a>
    </div>
    <div>
      <a href="login.html" class="btn btn-dark me-2">로그인</a>
      <a href="signup.html" class="btn btn-dark">회원가입</a>
    </div>
  </div>
</nav>

<!-- 본문 -->
<div class="container" style="margin-top: 120px;">
  <h2 class="text-center fw-bold mb-4">CONTENT DETAIL</h2>
	
	<form id="checkForm">
		<input type="hidden" name="no" value="${f.no}" id="no">
		<input type="hidden" name="pageNum" value="${pageNum }">
	</form>
  <div class="card mb-4">
    <div class="card-body">
      <h5 class="card-title">${f.no} 번째 글 - ${f.title}</h5>
      <h6 class="card-subtitle mb-2 text-muted">작성자: ${f.id}</h6>
      <p class="card-text">${f.content}</p>
      <p class="card-text"><strong>조회수:</strong> ${f.view}</p>
      <p class="card-text"><strong>첨부파일:</strong>
      	<c:if test="${empty f.file}">
				첨부파일 없음
				</c:if>	
      </p>

    	<div class="row my-3">
    			<div>
          <input type="button" class="btn" id="commend" value="추천 ( ${f.good} )">
    			</div>
					
					<div class="col text-center">
          <input type="button" class="btn btn-primary me-2" id="listupdate" value="수정하기">
          <input type="button" class="btn btn-danger" id="listDelete" value="삭제하기">
        	</div>
      </div>
      
    </div>
  </div>
	
  <!-- 검색이아닌 목록/댓글 버튼 -->
  <c:if test="${searchOption }">
  <div class="text-center mb-4">
    <a href="freeList.mvc?pageNum=${pageNum }&type=${type}&keyword=${keyword } " class="btn btn-outline-secondary me-2">목록보기</a>
    <a href="freelist.mvc" class="btn btn-outline-secondary">댓글</a>
  </div>
  </c:if>
  
  <c:if test="${not searchOption }">
  <div class="text-center mb-4">
    <a href="freeList.mvc" class="btn btn-outline-secondary me-2">목록보기</a>
    <a href="freelist.mvc" class="btn btn-outline-secondary">댓글</a>
  </div>
  </c:if>
  
  

  <!-- 댓글 목록 -->
  <c:if test="${not empty replyList}">
    <div class="list-group mb-5" id="replyList">
      <c:forEach var="r" items="${replyList}">
        <div class="list-group-item">
          <div class="d-flex justify-content-between">
            <span><strong>${r.mid}</strong></span>
            <small>
              <fmt:formatDate value="${r.date}" pattern="yyyy-MM-dd HH:mm:ss" />
            </small>
          </div>
          <pre class="mt-2 mb-2">${r.ccon}</pre>
          <div class="text-end">
            <button class="btn btn-outline-success btn-sm me-1 modifyReply" data-no="${r.cno}">수정</button>
            <button class="btn btn-outline-warning btn-sm me-1 deleteReply" data-no="${r.cno}">삭제</button>
            <button class="btn btn-outline-danger btn-sm" onclick="reportReply('${r.cno}')">신고</button>
          </div>
        </div>
      </c:forEach>
    </div>
  </c:if>

  <!-- 댓글 없음 -->
  <c:if test="${empty replyList}">
    <div class="alert alert-info text-center">이 게시글에 대한 댓글이 존재하지 않음</div>
  </c:if>

  <!-- 댓글 작성 폼 -->
  <div class="d-none" id="replyForm">
    <form name="replyWriteForm" id="replyWriteForm" action="#">
      <input type="hidden" name="fno" value="${f.no }">
      <input type="hidden" name="replyid" value="${f.id }">
      <div class="mb-3">
        <label for="replyContent" class="form-label">댓글 내용</label>
        <textarea name="replyContent" id="replyContent" class="form-control" rows="4"></textarea>
      </div>
      <button type="submit" class="btn btn-primary" id="replyWriteButton">댓글쓰기</button>
    </form>
  </div>
</div>




<!-- Bootstrap JS (옵션: 필요한 경우) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>