<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">  
  <title>TFM LIST</title>
  	<script src="js/jquery-3.7.1.min.js"></script>
	<script src="js/freelistCheck.js"></script>
  <!-- Bootstrap CSS CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- 상단 네비게이션 -->
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top border-bottom">
  <div class="container-fluid px-4">
    <a class="navbar-brand fw-bold" href="freeList.mvc">TFM LOGO</a>

    <div class="collapse navbar-collapse justify-content-center">
      <ul class="navbar-nav gap-3">
        <li class="nav-item"><a class="nav-link fw-bold" href="#">TFM</a></li>
        <li class="nav-item"><a class="nav-link fw-bold" href="#">TFM</a></li>
        <li class="nav-item"><a class="nav-link fw-bold" href="#">TFM</a></li>
        <li class="nav-item"><a class="nav-link fw-bold" href="freeList.mvc">자유게시판</a></li>
      </ul>
    </div>

    <div class="d-flex">
      <a href="login.html" class="btn btn-dark me-2">로그인</a>
      <a href="signup.html" class="btn btn-outline-dark">회원가입</a>
    </div>
  </div>
</nav>
		<!-- content -->			
		<div class="row my-5" id="global-content">
			<div class="col">					
				<div class="row my-5 text-center">
					<div class="col">				
						<h2 class="fs-3 fw-bold">게시 글쓰기</h2>
					</div>
				</div>
				<!-- 폼의 데이터를 전송할 때 application/x-www-urlencoded -->
				<form action="freewriteProcess.mvc" name="writeForm" id="writeForm"
					class="row border-primary g-3" method="post" 
					enctype="multipart/form-data">			
					<div class="col-4 offset-2">
				    <label for="writer" class="form-label">작성자</label>
				    <input type="text" class="form-control" id="fid" name="fid" > 
				  </div>
					
				 	<div class="col-8 offset-2">
				    <label for="title" class="form-label">제 목</label>
				    <input type="text" class="form-control" id="ftitle" name="ftitle">
				  </div>				  
				  <div class="col-8 offset-2">
				    <label for="content" class="form-label">내 용</label>
				    <textarea class="form-control" id="fcontent" name="fcontent" rows="10"></textarea>
				  </div>
				 	<div class="col-8 offset-2">				   
				    <input type="file" class="form-control" id="ffile" name="ffile">
				  </div>				  
					<div class="col-8 offset-2 text-center mt-5">
						<input type="submit" class="btn btn-primary" value="등록하기">
						&nbsp;&nbsp;
						<input type="button" class="btn btn-primary" value="목록보기"
							onclick="location.href='freeList.mvc'">
					</div>
				</form>		
			</div>
		</div>