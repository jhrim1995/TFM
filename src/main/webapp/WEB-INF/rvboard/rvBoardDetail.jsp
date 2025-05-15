<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<script src="js/rvreply.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>리뷰 상세보기</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.7.1.min.js"></script>
 <script src="js/rvformcheck.js"></script> 
</head>
<body>
<div class="container">
<!-- header -->

<!-- content -->
<div class="row my-5" id="global-content">
<div class="col">
<form name="rvCheckForm" id="rvCheckForm">
  <input type="hidden" name="r_no" id="r_no" value="${ board.r_no }">
  <input type="hidden" name="m_id" id="rM_id" >
  <input type="hidden" name="pageNum" value="${pageNum }">
  <c:if test="${searchOption }">
  	<input type="hidden" name="type" value="${type }">
  	<input type="hidden" name="keyword" value="${keyword }">
  </c:if>
</form>
  <h2 class="fs-3 fw-bold text-center">리뷰 상세보기~</h2>
</div>
</div>
<table class="table table-bordered">
  <tbody>
    <tr>
      <td class="table-secondary">제 목</td>
      <td colspan="3">${board.title }</td>
    </tr>
    <tr>
      <th>글쓴이</th>
      <td>
      	<div>
      		<input class="form-control" type="password" name="m_id" id="m_id">
      		<%-- <td>${board.m_id }</td> --%>
      	</div>
      </td>
      <th>작성일</th>
      <td><fmt:formatDate value="${board.p_date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
    </tr>
    <tr>
    <th>조회수</th>
      <td>${board.views }</td>            
    <th>지역</th>
      <td>${board.area }</td>
     </tr>
      <tr>
    	<th>파일</th>
    	<td colspan="3">
    		<c:if test="${empty board.file1 }">
    		첨부파일 없음
    		</c:if>
    		<c:if test="${not empty board.file1 }">
    			<a href="upload/${board.file1 }">파일 다운로드</a>
    		</c:if>
    	</td>
    </tr>
    <tr>
      <td colspan="4">
        <pre>${board.content }</pre>
      </td>
    </tr>
  </tbody>
</table>

<div class="row my-3">
  <div class="col text-center">
    <input class="btn btn-warning" type="button" id="rvDetailUpdate" value="수정하기">
    <input class="btn btn-danger" type="button" id="rvDetailDelete" value="삭제하기">
    <c:if test="${not searchOption }">
    <input class="btn btn-primary" type="button" value="목록" onclick="location.href='rvBoardList.mvc?pageNum=${pageNum}'">
  	</c:if>
  	<c:if test="${searchOption }">
    <input class="btn btn-primary" type="button" value="목록" onclick="location.href='rvBoardList.mvc?pageNum=${pageNum}&type=${type }&keyword=${keyword }'">
  	</c:if>
  </div>
</div>  

<!-- 추천/땡큐 영역 -->
<div class="row my-5">
  <div class="col border p-3">
    <div id="recommend" class="text-end">
      <span id="commend" class="btnCommend text-warning" style="cursor: pointer;">
        <img src="images/recommend.png" alt="추천" />추천
        <span class="recommend">${board.recommend })(</span> 
      </span> |
      
      <span id="ReplyWrite" class="text-warning" style="cursor: pointer;">
      	<i class="bi bi-file-earmark-text-fill" style="color: cornflowerblue;"></i>댓글
      </span>
    </div>
  </div>
</div>

<!-- 댓글 헤더 영역 -->
<div class="row" id="rvReplyTitle">
  <div class="col p-2 text-center bg-yellowgreen text-white">
    <h3 class="fs-4">댓 리스트</h3>
  </div>
</div>

<!-- 댓글 리스트 영역 -->
<!-- 댓글이 존재하는 경우 -->
<c:if test="${not empty replyList }">
  <div class="row mb-3">
    <div class="col" id="ReplyList">
      <c:forEach var="reply" items="${replyList }">
        <div class="replyRow row border border-top-0">
          <div class="col">
            <div class="row bg-light p-2">
              <div class="col-4">
                <span>${reply.m_id }</span>
              </div>
              <div class="col-8 text-end">
                <span class="me-3">
                  <fmt:formatDate value="${reply.c_date }" pattern="yyyy-MM-dd HH:mm:ss" />                  
                </span>
                <button class="modifyReply btn btn-outline-success btn-sm" data-no="${reply.c_no }">
                  <i class="bi bi-journal-text">수정</i>
                </button>
                <button class="deleReply btn btn-outline-warning btn-sm" data-no="${reply.c_no }">
                  <i class="bi bi-trash">삭제</i>
                </button>
                <button class="btn btn-outline-danger btn-sm" onclick="reportReply('${reply.c_no}')">
                  <i class="bi bi-telephone-outbound">신고</i>
                </button>
              </div>
            </div>
            <div class="row">
              <div class="col p-3">
                <pre>${reply.c_con }</pre>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</c:if>

<!-- 댓글이 존재하지 않는 경우 -->
<c:if test="${empty replyList }">
<div class="row mb-5" id="replyList">
  <div class="col text-center border p-5">
    <div>ㄴ 댓글</div>
  </div>
</div>
</c:if>

<!-- 댓글 쓰기 폼 -->
<div class="row my-3 d-none" id="replyForm">
  <div class="col">
    <form name="replyWriteForm" id="replyWriteForm">
      <input type="hidden" name="r_no" value="${board.r_no }" >
      <input type="hidden" name="m_id" value="${sessionScope.id }">
      <div class="row bg-light my-3 p-3 border">
        <div class="col">
          <div class="row">
            <div class="col text-center">
              <span>악의 적인 댓 삭제 될수 있음</span>
            </div>
          </div>
          <div class="row my-3">
            <div class="col-md-10">
              <textarea name="c_con" id="c_con" class="form-control" rows="4">
              </textarea>
            </div>          
            <div class="col-md">
              <input type="submit" value="댓글쓰기" class="btn btn-primary h-100 w-100" id="replyWriteButton">
            </div>
          </div>
        </div>
      </div>
    </form>
  </div>
</div>



<!-- footer -->
</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>