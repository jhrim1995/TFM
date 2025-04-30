<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>요리 게시판</title>
  <style>
    body {
      font-family: 'Arial', sans-serif;
      background: #fff8f0;
      margin: 0;
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

    .login-form {
      display: none;
      padding: 1rem;
      background: #fff3e0;
      border-radius: 8px;
      margin: 1rem;
    }

    .login-form input {
      margin-right: 10px;
      padding: 5px;
    }

    h1 {
      text-align: center;
      margin: 1.5rem 0;
    }

    table {
      width: 90%;
      margin: auto;
      border-collapse: collapse;
      background: #fff;
    }

    th, td {
      padding: 1rem;
      border-bottom: 1px solid #ccc;
      text-align: left;
    }

    th {
      background-color: #ff7043;
      color: white;
    }

    .post-title {
      cursor: pointer;
      color: #e64a19;
    }

    .post-detail {
      display: none;
      background: #fff3e0;
      padding: 1rem;
      border-radius: 10px;
    }

    .comments {
      margin-top: 1rem;
    }

    .comments input {
      width: 80%;
      padding: 0.5rem;
    }

    .comments button {
      padding: 0.5rem;
      background: #ff7043;
      border: none;
      color: white;
      border-radius: 4px;
    }

    .like-btn, .edit-btn, .delete-btn {
      background: none;
      border: none;
      color: #ff7043;
      font-weight: bold;
      cursor: pointer;
      margin-right: 10px;
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
    </div>
    <div class="nav-right">
      <button onclick="toggleLoginForm()">로그인</button>
    </div>
  </nav>

  <!-- 로그인 폼 -->
  <div class="login-form" id="loginForm">
    <input type="text" placeholder="아이디">
    <input type="password" placeholder="비밀번호">
    <button onclick="alert('로그인 시도됨')">확인</button>
  </div>

  <h1>요리 게시판</h1>

  <!-- 게시글 테이블 -->
  <table>
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>날짜</th>
      </tr>
    </thead>
    <tbody>
      <tr onclick="togglePost(1)">
        <td>1</td>
        <td class="post-title">김치찌개 레시피</td>
        <td>요리왕</td>
        <td id="view-count-1">0</td>
        <td>2025-04-29</td>
      </tr>
      <tr>
        <td colspan="5">
          <div id="post-1" class="post-detail">
            <p><strong>재료:</strong> 김치, 돼지고기, 두부, 대파, 고춧가루 등</p>
            <p><strong>방법:</strong> 김치를 볶은 뒤 돼지고기와 함께 끓이면 완성!</p>

            <div>
              <span>❤️ <span id="like-count-1">0</span></span>
              <button class="like-btn" onclick="likePost(event, 1)">좋아요</button>
              <button class="edit-btn" onclick="editPost(1)">수정</button>
              <button class="delete-btn" onclick="deletePost(1)">삭제</button>
            </div>

            <div class="comments">
              <input type="text" id="comment-input-1" placeholder="댓글을 입력하세요" />
              <button onclick="addComment(1)">댓글 작성</button>
              <ul id="comment-list-1"></ul>
            </div>
          </div>
        </td>
      </tr>
    </tbody>
  </table>

  <script>
    function toggleLoginForm() {
      const form = document.getElementById('loginForm');
      form.style.display = form.style.display === 'block' ? 'none' : 'block';
    }

    function togglePost(id) {
      const detail = document.getElementById(`post-${id}`);
      const viewCount = document.getElementById(`view-count-${id}`);
      if (detail.style.display !== 'block') {
        detail.style.display = 'block';
        viewCount.textContent = parseInt(viewCount.textContent) + 1;
      } else {
        detail.style.display = 'none';
      }
    }

    function likePost(event, id) {
      event.stopPropagation();
      const countEl = document.getElementById(`like-count-${id}`);
      countEl.textContent = parseInt(countEl.textContent) + 1;
    }

    function addComment(id) {
      const input = document.getElementById(`comment-input-${id}`);
      const list = document.getElementById(`comment-list-${id}`);
      if (input.value.trim() === '') return;
      const li = document.createElement('li');
      li.textContent = input.value;
      list.appendChild(li);
      input.value = '';
    }

    function editPost(id) {
      alert(`게시글 ${id} 수정 기능은 서버와 연동해야 합니다.`);
    }

    function deletePost(id) {
      const confirmed = confirm('정말 삭제하시겠습니까?');
      if (confirmed) {
        const row = document.getElementById(`post-${id}`).closest('tr').previousElementSibling;
        row.style.display = 'none';
        document.getElementById(`post-${id}`).closest('tr').style.display = 'none';
      }
    }
  </script>

</body>
</html>