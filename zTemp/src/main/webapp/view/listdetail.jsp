<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>게시글 상세 보기</title>
  <style>
    body {
      font-family: 'Arial', sans-serif;
      background: #fff8f0;
      margin: 0;
      padding: 0;
    }

    /* Nav */
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

    /* 상세 콘텐츠 */
    .container {
      max-width: 800px;
      margin: 2rem auto;
      background: #fff;
      padding: 2rem;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }

    h1 {
      margin-bottom: 0.5rem;
    }

    .meta {
      color: #666;
      font-size: 0.9rem;
      margin-bottom: 1.5rem;
    }

    .content {
      line-height: 1.6;
      margin-bottom: 1.5rem;
    }

    .actions {
      margin-bottom: 1.5rem;
    }

    .actions button {
      background: #ff7043;
      border: none;
      color: white;
      padding: 0.5rem 1rem;
      margin-right: 10px;
      cursor: pointer;
      border-radius: 5px;
    }

    .like {
      color: #ff7043;
      margin-bottom: 1rem;
    }

    .comments input {
      width: 80%;
      padding: 0.5rem;
    }

    .comments button {
      background: #ff7043;
      border: none;
      color: white;
      padding: 0.5rem;
      border-radius: 5px;
    }

    .comments ul {
      margin-top: 1rem;
      list-style: none;
      padding: 0;
    }

    .comments ul li {
      background: #fff3e0;
      margin-bottom: 5px;
      padding: 0.5rem;
      border-radius: 5px;
    }
  </style>
</head>
<body>

  <!-- 상단 네비게이션 바 -->
  <nav class="login-nav">
    <div class="nav-left">
      <a href="index.html">홈</a>
      <a href="board.html">레시피</a>
      <a href="#">리뷰</a>
    </div>
    <div class="nav-right">
      <button onclick="alert('로그인 창 열기')">로그인</button>
    </div>
  </nav>

  <!-- 게시글 상세 콘텐츠 -->
  <div class="container">
    <h1>김치찌개 레시피</h1>
    <div class="meta">작성자: 요리왕 | 작성일: 2025-04-29 | 조회수: <span id="views">1</span></div>

    <div class="content">
      <p><strong>재료:</strong> 김치, 돼지고기, 두부, 대파, 고춧가루 등</p>
      <p><strong>만드는 법:</strong> 김치를 볶은 뒤 돼지고기와 함께 끓이면 완성입니다.</p>
    </div>

    <div class="actions">
      <button onclick="editPost()">수정</button>
      <button onclick="deletePost()">삭제</button>
    </div>

    <div class="like">
      ❤️ 좋아요: <span id="likeCount">0</span>
      <button onclick="likePost()">좋아요</button>
    </div>

    <div class="comments">
      <input type="text" id="commentInput" placeholder="댓글을 입력하세요">
      <button onclick="addComment()">댓글 작성</button>
      <ul id="commentList"></ul>
    </div>
  </div>

  <script>
    function likePost() {
      const count = document.getElementById('likeCount');
      count.textContent = parseInt(count.textContent) + 1;
    }

    function addComment() {
      const input = document.getElementById('commentInput');
      const list = document.getElementById('commentList');
      if (input.value.trim() === '') return;
      const li = document.createElement('li');
      li.textContent = input.value;
      list.appendChild(li);
      input.value = '';
    }

    function editPost() {
      alert('수정 기능은 서버 연동 시 구현 가능합니다.');
    }

    function deletePost() {
      if (confirm('정말 삭제하시겠습니까?')) {
        alert('삭제되었습니다. (실제 삭제는 서버 연동 필요)');
        window.location.href = 'board.html';
      }
    }
  </script>

</body>
</html>