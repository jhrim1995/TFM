<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="bootstrap/bootstrap.bundle.min.js"></script>
<div class="top-bar">
    <div class="logo">
      <a href="main.mvc"><img src ="images/logo.jpg" height="40" width="100" /></a>
    </div>

    <div class="menu-center">
      <a href="#" class="menu-box">기사</a>
      <a href="#" class="menu-box">리뷰</a>
      <a href="#" class="menu-box">자유</a>
      <a href="#" class="menu-box">Q&A</a>
    </div>

    <div class="auth-buttons">
      <a href='${ sessionScope.isLogin ? "logout.mvc" : "loginForm.mvc"}'>${ sessionScope.isLogin ? "로그아웃" : "로그인"}</a>
      <a href='${ sessionScope.isLogin ? "myProfileForm.mvc" : "joinMemberForm.mvc"}'>${ sessionScope.isLogin ? "내정보" : "회원가입"}</a>
    </div>
  </div>