<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>TFM</title>	
	<link href="bootstrap/bootstrap.min.css" rel="stylesheet" >
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<link rel="stylesheet" type="text/css" href="css/global.css" />
	<script src="js/jquery-3.7.1.min.js"></script>
	<style>
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
   .container{
   	margin-top : 150px;
   }
	</style>
</head>
<body>
	<%@ include file="pages/header.jsp" %>
	<div class="container">
				<jsp:include page="${ param.body }" />
	</div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>	
</body>
</html>-->