<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>리뷰 리스트</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.7.1.min.js"></script>
<script src="js/rvformcheck.js"></script>
</head>
<body class="bs-warning-bg-subtle">
<div class="container">
<!-- header -->
<!-- content -->
<div class="row my-5 " id="global-content" >
<div class="col">
<div class="row text-center">
<div class="col">
<h2 class="fs-3 fw-bold text-center">리뷰우~</h2>
</div>
</div>
 <form name="reviewForm" id="reviewForm" action="#" class="row justify-content-center my-3">
 <div class="col-auto">
  <select name="type" class="form-select">
   <option value="title">제목</option>
   <option value="m_id">작성자</option>
   <option value="content">내용</option>
   <option value="area">지역</option>
  </select>
</div>  
   <div class="col-4">
    <input type="text" name="keyword" class="form-control" id="keyword">
   </div>
   <div class="col-auto">
    <input type="submit" value="검 색" class="btn btn-outline-danger">
   </div>
 </form>
 <!-- 검색 요청일 경우 -->
 <c:if test="${searchOption }">
 	<div class="row my-3">
 		<div class="col text-center">
 			"${keyword }" 검색 결과~
 		</div>
 	</div>
 	<!-- 검색 요청일 경우 리뷰 게시글 리스트로 -->
 	<div class="row my-3">
 		<div class="col-6">
 			<a href="rvBoardList.mvc" class="btn-outline-success">리스트</a>
 		</div>
 		<div class="col-6 text-end">
 			<a href="rvWriteForm.mvc" class="btn btn-outline-success">글쓰기</a>
 		</div>
 	</div>
 </c:if>
 <!-- 검색 요청이 아닐 경우 -->
 <c:if test="${not searchOption }">
 	<div class="row my-3">
 		<div class="col text-end">
 			<a href="rvWriteForm.mvc" class="btn btn-outline-success">글쓰기</a>
 		</div>
 	</div>
 </c:if>
<div class="row my-3">
<div class="col">
  <table class="table table-hober">
  <thead>
    <tr class="table-danger">
      <th>번호</th>
      <th>작성자</th>
      <th>제목</th>
      <th>작성일</th>
      <th>조회수</th>
      <th>지역</th>
    </tr>
  </thead>
  <tbody class="text-secondary">
  <!-- 검색 요청 이면서 검색된 리스트가 존재할 경우 -->
  <c:if test="${searchOption and not empty boardList }">
   <c:forEach var="b" items="${boardList }" varStatus="status">
     <tr>
       <td>${b.r_no }</td>
       <td>${b.m_id }</td>
       <td><a href="rvBoardDetail.mvc?r_no=${b.r_no }&pageNum=${currentPage}&type=${type}&keyword=${keyword}" 
       	class="text-decoration-none link-secondary" >${b.title }</a></td>
       <td><fmt:formatDate value="${b.p_date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
       <td>${b.views }</td>
       <td>${b.area }</td>
     </tr>
   </c:forEach>
   </c:if>
   <!-- 일반 게시글 리스트 요청 이면서 게시글 리스트가 존재할 경우 -->
    <c:if test="${not searchOption and not empty boardList }">
   <c:forEach var="b" items="${boardList }" varStatus="status">
     <tr>
       <td>${b.r_no }</td>
       <td>${b.m_id }</td>
       <td><a href="rvBoardDetail.mvc?r_no=${b.r_no }&pageNum=${currentPage}" 
       	class="text-decoration-none link-secondary" >${b.title }</a></td>
       <td><fmt:formatDate value="${b.p_date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
       <td>${b.views }</td>
       <td>${b.area }</td>
     </tr>
   </c:forEach>
   </c:if>
   <!-- 검색 요청이면서 검색된 리스트가 존재하지 않을 경우 -->
   <c:if test="${searchOption and empty boardList }">
     <tr>
       <td colspan="6" class="text-center">"${keyword }"가 포함된 게시글 ㄴ</td>
     </tr>
   </c:if>
   <!-- 일반 게시글 리스트 요청이면서 게시글 리스트가 존재하지 않을 경우 -->
   <c:if test="${not searchOption and empty boardList }">
     <tr>
       <td colspan="6" class="text-center">게시글 ㄴ</td>
     </tr>
   </c:if>
  </tbody>
</table>
</div>
</div>
<!-- 검색 요청이면서 검색된 리스트가 존재할 경우 -->
<c:if test="${searchOption and not empty boardList }">
<div class="row">
  <div class="col">
    <nav aria-label="Page navigation">
      <ul class="pagination justify-content-center">
        <c:if test="${startPage > pageGroup }">
          <li class="page-item">
            <a class="page-link" href="rvBoardList.mvc?pageNum=${startPage - pageGroup }&type=${type}&keyword=${keyword}">이전</a>
          </li>
        </c:if>
        <c:forEach var="i" begin="${startPage }" end="${endPage }">
          <c:if test="${i == currentPage }">
            <li class="page-item" aria-current="page">
              <span class="page-link active">${i }</span>
            </li>
          </c:if>
          <c:if test="${i != currentPage }">
            <li class="page-item">
              <a class="page-link" href="rvBoardList.mvc?pageNum=${i }&type=${type}&keyword=${keyword}">${i }</a>
            </li>
          </c:if>
        </c:forEach>
        <c:if test="${endPage < pageCount }">
          <li class="page-item">
            <a class="page-link" href="rvBoardList.mvc?pageNum=${startPage + pageGroup }&type=${type}&keyword=${keyword}">Next</a>
          </li>
        </c:if>
      </ul>
    </nav>
  </div>
</div>
</c:if>
<!-- 일반 게시글 요청이면서 검색된 리스트가 존재할 경우 -->
<c:if test="${not searchOption and not empty boardList }">
	<div class="row">
		<div class="col">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<c:if test="${startPage > pageGroup }">
						<li class="page-item">
							<a class="page-link" href="rvBoardList.mvc?pageNum=${startPage - pageGroup }">
							이전
							</a>
						</li>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:if test="${i == currentPage }">
						<li class="page-item active" aria-current="page">
							<span class="page-link">${i }</span>
						</li>
						</c:if>
						<c:if test="${i != currentPage }">
						<li class="page-item">
							<a class="page-link" href="rvBoardList.mvc?pageNum=${i }">${i }</a>
						</li>
						</c:if>
					</c:forEach>
					<c:if test="${endPage < pageCount }">
						<li class="page-item">
							<a class="page-link" href="rvBoardList.mvc?pageNum=${startPage + pageGroup }">
							Next
							</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
	</c:if>
</div>
</div>

<!-- footer -->
</div>
<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>