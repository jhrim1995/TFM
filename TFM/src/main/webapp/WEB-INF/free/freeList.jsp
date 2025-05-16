<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">  
  <title>TFM LIST</title>
  	<script src="js/jquery-3.7.1.min.js"></script>
	<script src="js/freelistCheck.js"></script>
  <!-- Bootstrap CSS CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- 상단 네비게이션 -->
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top border-bottom">
  <div class="container-fluid px-4">
    <a class="navbar-brand fw-bold" href="index.html">TFM LOGO</a>

    <div class="collapse navbar-collapse justify-content-center">
      <ul class="navbar-nav gap-3">
        <li class="nav-item"><a class="nav-link fw-bold" href="#">TFM</a></li>
        <li class="nav-item"><a class="nav-link fw-bold" href="#">TFM</a></li>
        <li class="nav-item"><a class="nav-link fw-bold" href="#">TFM</a></li>
        <li class="nav-item"><a class="nav-link fw-bold" href="freeList.mvc">자유게시판</a></li>
      </ul>
    </div>

    <div class="d-flex">
      <a href="login.html" class="btn btn-dark me-2">로그인</a>
      <a href="signup.html" class="btn btn-outline-dark">회원가입</a>
    </div>
  </div>
</nav>

	<div class="row text-center">
					<div class="col">
						<h2 class="fs-3 fw-bold">게시 글 리스트</h2>				
					</div>			
				</div>		
				
				<form name="searchForm" id="searchForm" action="#"
					class="row my-3 text-center justify-content-center">
					<div class="col-auto">
						<select name="type" class="form-select">				 
							<option value="ftitle">제목</option>
							<option value="fid">작성자</option>
							<option value="fcontent">내용</option>
						</select>
					</div>
					<div class="col-4">
						<input type="text" name="keyword" class="form-control" id="keyword">
					</div>
					<div class="col-auto">
						<input type="submit" class="btn btn-primary" value="검색">
					</div>
				</form>
				
				<%-- 검색 요청인 경우  --%>
				<c:if test="${searchOption}">
				<div class="row">
					<div class="col text-center">
						"${ keyword }" 검색 결과
					</div>
				</div>
				<div class="row">
					<div class="col">
						<a href="freeList.mvc" class="btn btn-outline-success">리스트</a>
					</div>
					<div class="col text-end">
						<a href="freewriteForm.mvc" class="btn btn-outline-success">글쓰기</a>
					</div>
				</div>				
				</c:if>
				
				<%-- 검색 요청이 아닌 경우  --%>
				<c:if test="${not searchOption}">
				<div class="row">
					<div class="col text-end">
						<a href="freewriteForm.mvc" class="btn btn-outline-success">글쓰기</a>
					</div>
				</div>
				</c:if>
				
				<div class="row my-3">
					<div class="col">
						<table class="table table-hover">
							<thead class="table-dark">
								<tr>
									<th>no</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody class="text-secondary">
							<%-- 검색 요청이면서 게시글이 있는 경우 --%>
							<c:if test="${searchOption and not empty fList}">
							<c:forEach var="f" items="${ fList }">
								<tr>
									<td>${f.no}</td> 
									<td class="text-secondary">
										<a href="freeDetail.mvc?no=${f.no}&pageNum=${currentPage}&type=${type}&keyword=${keyword}" 
										class="text-secondary text-decoration-none">${f.title}</a>
										</td>
									<td class="text-secondary">${f.id}</td>
									<td>${f.date}</td>
									<td>${f.view}</td>
								</tr>
							</c:forEach>
							</c:if>
							<%-- 일반 게시 글 리스트 요청이면서 게시 글이 있는 경우 --%>
							<c:if test="${not searchOption and not empty fList}">
							<c:forEach var="f" items="${ fList }">
								<tr>
									<td>${f.no}</td> 
									<td class="text-secondary">
										<a href="freeDetail.mvc?no=${f.no}&pageNum=${currentPage}" 
										class="text-secondary text-decoration-none">${f.title}</a>
										</td>
									<td class="text-secondary">${f.id}</td>
									<td>${f.date}</td>
									<td>${f.view}</td>
								</tr>
							</c:forEach>
							</c:if>
							<!-- 검색 게시 글 리스트 이면서 게시글이 없는 경우 -->
							<c:if test="${searchOption and empty fList}">
								<tr>
									<td colspan="5" class="text-center">
										"${keyword}"가 포함된 게시 글이 존재하지 않음
									</td>
								</tr>
							</c:if>
							
							<!-- 일반 게시 글 리스트 이면서 게시글이 없는 경우 -->
							<c:if test="${not searchOption and empty fList}">
								<tr>
									<td colspan="5" class="text-center">게시 글이 존재하지 않음</td>
								</tr>
							</c:if>
							</tbody>
						</table>
					</div>
				</div>


<!-- 검색 요청이면서 검색된 리스트가 존재할 경우 페이지네이션 -->
				<c:if test="${ searchOption and not empty fList }">
				<div class="row">
					<div class="col">
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center">
						    <c:if test="${startPage > pageGroup}">
						    <li class="page-item">
						    	<a class="page-link" 
						    		href="freeList.mvc?pageNum=${startPage - pageGroup}&type=${type}&keyword=${keyword}">Pre</a>
						    </li>
						    </c:if>
						    <c:forEach var="i" begin="${startPage}" end="${endPage}">
						    	<%-- 현재 페이지 - 링크x active --%>
						    	<c:if test="${i == currentPage}">
						    	<li class="page-item">
						    		<span class="page-link active">${i}</span>
						    	</li>
						    	</c:if>
						    	<c:if test="${i != currentPage}">
						    	<li class="page-item">
						    		<a class="page-link" href="freeList.mvc?pageNum=${i}&type=${type}&keyword=${keyword}">${i}</a>
						    	</li>
						    	</c:if>
						    </c:forEach>
						    <c:if test="${endPage < pageCount}">						    
						    <li class="page-item">
						    	<a class="page-link" 
						    		href="freeList.mvc?pageNum=${startPage + pageGroup}&type=${type}&keyword=${keyword}">Next</a>
						    </li>
						    </c:if>
						  </ul>
						</nav>
					</div>
				</div>
				</c:if>
				
				<!-- 일반 요청이면서 검색된 리스트가 존재할 경우 페이지네이션 -->
				<c:if test="${ not searchOption and not empty fList }">
				<div class="row">
					<div class="col">
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center">
						    <c:if test="${startPage > pageGroup}">
						    <li class="page-item">
						    	<a class="page-link" 
						    		href="free.mvc?pageNum=${startPage - pageGroup}">Pre</a>
						    </li>
						    </c:if>
						    
						    <c:forEach var="i" begin="${startPage}" end="${endPage}">
						    	<%-- 현재 페이지 - 링크x active --%>
						    	<c:if test="${i == currentPage}">
						    	<li class="page-item">
						    		<span class="page-link active">${i}</span>
						    	</li>
						    	</c:if>
						    	<c:if test="${i != currentPage}">
						    	<li class="page-item">
						    		<a class="page-link" href="freeList.mvc?pageNum=${i}">${i}</a>
						    	</li>
						    	</c:if>
						    </c:forEach>
						    <c:if test="${endPage < pageCount}">						    
						    <li class="page-item">
						    	<a class="page-link" 
						    		href="freeList.mvc?pageNum=${startPage + pageGroup}">Next</a>
						    </li>
						    </c:if>
						  </ul>
						</nav>
					</div>
				</div>
				</c:if>

<!-- Bootstrap JS (선택사항) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>