<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	
		<div class="row my-5 text-center">
			<div class="col">
					<a href="main.mvc"><img src = "images/logo.jpg"/></a>
			</div>
		</div>
		

		<form class="row justify-content-center my-3" name="loginForm" action="lowginCheck.mvc">
			<div class = "col">
				<div class="row my-2">
					<input class="form-control " type="text"  name="id" placeholder="아이디를 입력해주세요.">
				</div>
				<div class="row my-2">
					<input class="form-control" type="password" name="pass" placeholder="비밀번호를 입력해주세요.">
				</div>
				<div class="row my-2">
					<input class="btn btn-lg btn-dark" type="submit" value="로그인" >
				</div>
			</div>
		</form>
		
		<div class="row my-5 text-center">
			<div class="col">
				<a class="text-decoration-none mx-3"  href = "#">아이디찾기 </a> | <a class="text-decoration-none mx-3" href = "#">비밀번호찾기 </a> | <a class="text-decoration-none mx-3" href = "joinMemberForm.mvc"> 회원가입 </a>
			</div>
		</div>
		
</div>
