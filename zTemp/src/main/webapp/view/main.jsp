<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>요리의 모든 것</title>
  <style>
    body {
      margin: 0;
      font-family: 'Segoe UI', sans-serif;
      background-color: #fff8f0;
      color: #333;
    }
    /* 로그인 네비게이션 */
    .login-nav {
      background-color: #ff7043;
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 0.5rem 1rem;
    }
    .nav-left a {
      color: white;
      margin-right: 15px;
      text-decoration: none;
      font-weight: bold;
    }
    .nav-right button {
      background-color: white;
      color: #ff7043;
      border: none;
      padding: 5px 10px;
      font-weight: bold;
      cursor: pointer;
      border-radius: 5px;
    }

    header {
      background: url('https://source.unsplash.com/1600x600/?food,cooking') center/cover no-repeat;
      height: 60vh;
      display: flex;
      align-items: center;
      justify-content: center;
      color: white;
      text-shadow: 2px 2px 4px #000;
    }

    h1 {
      font-size: 3rem;
      text-align: center;
    }
    section {
      padding: 2rem;
      text-align: center;
    }
  </style>
</head>
<body>

  <!-- 로그인 네비게이션 -->
  <nav class="login-nav">
    <div class="nav-left">
      <a href="#">홈</a>
      <a href="#">레시피</a>
      <a href="#">리뷰</a>
      <a href="#">연락처</a>
    </div>
    <div class="nav-right">
      <button>로그인</button>
    </div>
  </nav>

  <header>
    <h1>맛있는 요리의 세계</h1>
  </header>

  <section>
    <h2>오늘의 추천 레시피</h2>
    <p>신선한 재료로 만드는 간편하고 맛있는 요리!</p>
  </section>

</body>
</html>