<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<%@include file="../pages/header.jsp" %>		
		<!-- content -->			
		<div class="row my-5" id="global-content">
			<div class="col">		
				<div class="row text-center">
					<div class="col">
						<h2 class="fs-3 fw-bold">게시 글 리스트</h2>				
					</div>			
				</div>		
				<form name="searchForm" id="searchForm" action="#"
					class="row my-3 text-center justify-content-center">
					<div class="col-auto">
						<select name="type" class="form-select">				 
							<option value="title">제목</option>
							<option value="writer">작성자</option>
							<option value="content">내용</option>
						</select>
					</div>
					<div class="col-4">
						<input type="text" name="keyword" class="form-control" id="keyword">
					</div>
					<div class="col-auto">
						<input type="submit" class="btn btn-primary" value="검색">
					</div>
				</form>
				<div class="row">
					<div class="col text-end">
						<a href="writeForm" class="btn btn-outline-success">글쓰기</a>
					</div>
				</div>
				<div class="row my-3">
					<div class="col">
						<table class="table table-hover">
							<thead class="table-dark">
								<tr>
									<th>no</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일</th>
									<th>조회수111</th>
								</tr>
							</thead>
							<tbody class="text-secondary">
							<!-- 게시글이 있는 경우 -->
							<c:if test="${not empty bList}">
							<c:forEach var="board" items="${ bList }">
								<tr>
									<td>${board.no}</td> 
									<td class="text-secondary">
										<a href="boardDetail?no=${board.no}&pageNum=${currentPage}" 
										class="text-secondary text-decoration-none">${board.title}</a>
										</td>
									<td class="text-secondary">${board.writer}</td>
									<td>${board.regDate}</td>
									<td>${board.readCount}</td>
								</tr>
							</c:forEach>
							</c:if>
							<!-- 게시글이 없는 경우 -->
							<c:if test="${empty bList}">
								<tr>
									<td colspan="5" class="text-center">게시 글이 존재하지 않음</td>
								</tr>
							</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center">
						    <c:if test="${startPage > pageGroup}">
						    <li class="page-item">
						    	<a class="page-link" 
						    		href="boardList?pageNum=${startPage - pageGroup}">Pre</a>
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
						    		<a class="page-link" href="boardList?pageNum=${i}">${i}</a>
						    	</li>
						    	</c:if>
						    </c:forEach>
						    <c:if test="${endPage < pageCount}">						    
						    <li class="page-item">
						    	<a class="page-link" 
						    		href="boardList?pageNum=${startPage + pageGroup}">Next</a>
						    </li>
						    </c:if>
						  </ul>
						</nav>
					</div>
				</div>
		</div>
	</div>	
	<%@include file="../pages/footer.jsp" %>		
	</div>	
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>


