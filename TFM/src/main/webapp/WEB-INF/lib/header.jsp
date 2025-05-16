<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
 <style>
    * {
      box-sizing: border-box;
    }

    body {
      margin: 0;
      padding: 0;
      font-family: 'Arial', sans-serif;
      background-color: #f9f9f9;
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
      font-size: 24px;
      font-weight: 900;
      text-decoration: none;
      color: #333;
    }
	.header{
	 font-size: 30px;
     font-weight: bold;
     text-align: center;
     margin-top: 120px;
	}
    .menu-center {
      display: flex;
      gap: 20px;
    }

    .menu-box {
      padding: 10px 16px;
      border: 2px solid #333;
      border-radius: 8px;
      background-color: #fff;
      font-size: 16px;
      font-weight: bold;
      text-align: center;
      text-decoration: none;
      color: #000;
      transition: all 0.2s ease;
    }

    .menu-box:hover {
      background-color: #333;
      color: white;
    }

    .auth-buttons a {
      text-decoration: none;
      padding: 10px 18px;
      background-color: #333;
      color: #fff;
      border-radius: 5px;
      margin-left: 10px;
      transition: background-color 0.2s ease;
    }

    .auth-buttons a:hover {
      background-color: #555;
    }

    .main-content {
      margin-top: 60px;
      max-width: 800px;
      margin-left: auto;
      margin-right: auto;
      padding: 20px;
    }

    .post-box {
      background-color: #fff;
      border: 2px solid #ddd;
      padding: 20px;
      margin-bottom: 20px;
      border-radius: 10px;
      transition: box-shadow 0.2s ease;
    }

    .post-box:hover {
      box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    }

    .post-title {
      font-size: 20px;
      font-weight: bold;
      margin-bottom: 8px;
    }

    .post-info {
      font-size: 14px;
      color: #777;
      margin-bottom: 10px;
    }

    .post-summary {
      font-size: 16px;
      color: #444;
    }

    .pagination {
      text-align: center;
      margin-top: 70px;
    }

    .pagination a {
      margin: 0 6px;
      padding: 8px 12px;
      text-decoration: none;
      color: #333;
      border: 1px solid #aaa;
      border-radius: 5px;
      transition: background-color 0.2s ease;
    }

    .pagination a:hover {
      background-color: #333;
      color: #fff;
    }

    .pagination .active {
      background-color: #333;
      color: #fff;
      font-weight: bold;
    }
  </style>
<body>
	<!-- 상단바 -->
  <div class="top-bar">
    <div class="logo">
      <a href="index.html">TFM LOGO</a>
    </div>

    <div class="menu-center">
      <a href="#" class="menu-box">TFM</a>
      <a href="#" class="menu-box">TFM</a>
      <a href="#" class="menu-box">TFM</a>
      <a href="freeList.mvc" class="menu-box">자유게시판</a>
    </div>

    <div class="auth-buttons">
      <a href="login.html">로그인</a> 
      <a href="signup.html">회원가입</a>
    </div>
  </div>
</body>
</html>