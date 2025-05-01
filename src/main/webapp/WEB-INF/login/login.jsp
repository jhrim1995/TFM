<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../js/jquery-3.7.1.min.js"></script>
<title>로그인</title>
<link href="../bootstrap/bootstrap.min.css" rel="stylesheet" >  
</head>
<body>
	<div class="container">
	
		<div class="row my-5 text-center">
			<div class="col">
					<h1>TFM 로고</h1>
			</div>
		</div>
		

		<form class="row justify-content-center my-3" name="loginForm" action="../loginCheck.mvc">
			<input class="form-control" type="text"  name="id" placeholder="아이디를 입력해주세요.">
			<input class="form-control" type="password" name="pass" placeholder="비밀번호를 입력해주세요.">
			<input class="btn btn-lg btn-dark" type="submit" value="로그인" >
		</form>
		
		<div class="row my-5 text-center">
			<div class="col">
				<a class="text-decoration-none mx-3"  href = "#">아이디찾기 </a> | <a class="text-decoration-none mx-3" href = "#">비밀번호찾기 </a> | <a class="text-decoration-none mx-3" href = "joinMember.jsp"> 회원가입 </a>
			</div>
		</div>
		
	</div>
<script src="../bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>