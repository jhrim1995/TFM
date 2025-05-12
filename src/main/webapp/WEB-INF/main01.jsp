<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>TFM</title>
  <style>
    * {
      box-sizing: border-box;
    }

    body {
      margin: 0;
      padding: 0;
      font-family: 'Arial', sans-serif;
      background-color: #f4f4f4;
      min-height: 100vh;
    }

    .top-bar {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 20px 40px;
      background-color: #fff;
      border-bottom: 2px solid #ccc;
      position: fixed;
      width: 100%;
      top: 0;
      z-index: 100;
    }

    .logo a {
      font-size: 28px;
      font-weight: 900;
      text-decoration: none;
      color: #333;
    }

    .auth-buttons a {
      text-decoration: none;
      padding: 10px 18px;
      background-color: #333;
      color: #fff;
      border-radius: 5px;
      margin-left: 15px;
      transition: background-color 0.2s ease;
    }

    .auth-buttons a:hover {
      background-color: #555;
    }

    .main-header {
      margin-top: 160px;
      text-align: center;
      font-size: 48px;
      font-weight: 900;
      letter-spacing: 4px;
    }

    .menu-row {
      display: flex;
      justify-content: center;
      align-items: center;
      margin-top: 150px;
      gap: 40px;
      flex-wrap: wrap;
      padding-bottom: 60px;
    }

    .menu-box {
      width: 200px;
      height: 300px;
      border: 2px solid #333;
      background-color: #fff;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 22px;
      font-weight: bold;
      text-align: center;
      cursor: pointer;
      transition: transform 0.2s ease, box-shadow 0.2s ease;
      text-decoration: none;
      color: #000;
    }

    .menu-box:hover {
      transform: translateY(-5px);
      box-shadow: 0 6px 14px rgba(0,0,0,0.15);
    }

    a {
      text-decoration: none;
    }
  </style>
</head>
<body>

  <!-- 상단바 -->
  <div class="top-bar">
    <div class="logo">
      <a href="main.mvc"><img src ="images/logo.jpg" height="40" width="100" /></a>
    </div>
    <div class="auth-buttons">
      <a href='${ sessionScope.isLogin ? "logout.mvc" : "loginForm.mvc"}'>${ sessionScope.isLogin ? "로그아웃" : "로그인"}</a>
      <a href='${ sessionScope.isLogin ? "myProfileForm.mvc" : "joinMemberForm.mvc"}'>${ sessionScope.isLogin ? "내정보" : "회원가입"}</a>
    </div>
  </div>

  <!-- 메인 헤더 -->
  <div class="main-header">WELCOME TO TFM</div>

  <!-- 가로형 메뉴 박스 -->
  <div class="menu-row">
    <a href="#" class="menu-box">기사</a>
    <a href="#" class="menu-box">리뷰</a>
    <a href="#" class="menu-box">자유</a>
    <a href="#" class="menu-box">Q&A</a>
  </div>

</body>
</html>