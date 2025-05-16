<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<<<<<<< HEAD
=======
<<<<<<< HEAD
<script src="bootstrap/bootstrap.bundle.min.js"></script>
=======
>>>>>>> Seok
>>>>>>> main
<div class="top-bar">
	<div class = "col-3">
    <div class="logo">
      <a href="main.mvc"><img src ="images/logo.jpg" height="40" width="100" /></a>
    </div>
	</div>
	<div class="col-6 text-center">
    <div class="menu-center">
      <a href="atBoardList.mvc" class="menu-box">기사</a>
      <a href="#" class="menu-box">리뷰</a>
      <a href="#" class="menu-box">자유</a>
      <a href="faqlist.mvc" class="menu-box">Q&A</a>
    </div>
   </div>
	 <div class="col-3">
	 		<div class="row text-center">
	 			<span class="fs-5 text-bold text-center">
	 				<c:if test="${sessionScope.isLogin}"> <sapn class="fs-4 text-bold"> ${sessionScope.nickname}</sapn> 님 반갑습니다.</c:if>
	 				<c:if test="${!sessionScope.isLogin}">로그인 해주세요.</c:if>
	 			</span>
	 		</div>
	 		<div class="row my-2 text-center">
		    <div class="auth-buttons">
			      <a href='${ sessionScope.isLogin ? "logout.mvc" : "loginForm.mvc"}'>${ sessionScope.isLogin ? "로그아웃" : "로그인"}</a>
			      <a href='${ sessionScope.isLogin ? "myProfileForm.mvc" : "joinMemberForm.mvc"}'>${ sessionScope.isLogin ? "내정보" : "회원가입"}</a>
		    </div>
	    </div>
	 </div>
</div>