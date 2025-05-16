<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>맛집 기사 게시판</title>

</head>
<body>
	<div class="container">
	
		<!-- content -->
		<div class="row my-5 text-center" >
			<div class="col">
				<h2 class="fs=3 fw-bold" >맛집 기사 게시판</h2>
			</div>
		</div>
		<form name="searchForm" id="searchForm" action="#" class="row justify-content-center my-3" >
			<div class="col-auto" >
				<select name="type" class="form-select">
					<option value="title" >제목</option>
					<option value="m_id" >작성자</option>
					<option value="content" >내용</option>
				</select>
			</div>
			<div class="col-4" >
				<input type="text" name="keyword" class="form-control" id="keyword" />
			</div>
			<div class="col-auto" >
				<input type="submit" value="검 색" class = "btn btn-light" />
			</div>
		</form>
			<c:if test="${ searchOption }">
				<div class="row my-3">
					<div class="col text-center">
						"${ keyword }" 검색 결과
					</div>
				</div>
				<div class="row my-3">
					<div class="col-6">
						<a href="atBoardList.mvc" class="btn btn-outline-info">리스트</a>
					</div>
					<div class="col-6 text-end">
						<a href="atWriteForm.mvc" class="btn btn-outline-info">글쓰기</a>
					</div>
				</div>
			</c:if>
			<c:if test="${ not searchOption }">
				<div class="row my-3">
					<div class="col text-end">
						<c:if test="${ sessionScope.id == 'atadmin' }">
						<a href="atWriteForm.mvc" class="btn btn-outline-info">글쓰기</a>
						</c:if>
					</div>
				</div>
			</c:if>
			<div class="row my-3" >
				<div class="col" >
					<table class="table table-hover" >
						<thead>
						<tr class="table-info" >
							<th>No</th>
							<th>작성자</th>
							<th>제목</th>
							<th>작성일</th>
							<th>조회수</th>
							<th>추천수</th>
						</tr>
						</thead>
						<tbody class="text-light" >
						<!-- 검색 O, 검색리스트 O -->
						<c:if test="${ searchOption and not empty bList }">
							<c:forEach var="b" items="${ bList }" >
							<tr>
								<td>${ b.at_no }</td>
								<td class="text-secondary" >${ b.m_id }</td>
								<td class="text-secondary"><a href="boardDetail.mvc?no=${b.at_no}&pageNum=${currentPage}&type=${type}&keyword=${keyword}" 
										class="text-secondary text-decoration-none">${b.title}</a></td>
								<td>${ b.w_date}</td>
								<td>${ b.views }</td>
								<td>${ b.recm }</td>
							</tr>
							</c:forEach>
						</c:if>
						<!-- 검색 X, 검색리스트 O -->
						<c:if test="${ not searchOption and not empty bList }">
							<c:forEach var="b" items="${ bList }" >
							<tr>
								<td>${ b.at_no }</td>
								<td>${ b.m_id }</td>
								<td class="text-secondary"><a href="boardDetail.mvc?no=${b.at_no}&pageNum=${currentPage}" 
										class="text-secondary text-decoration-none">${b.title}</a></td>
								<td>${ b.w_date}</td>
								<td>${ b.views }</td>
								<td>${ b.recm }</td>
							</tr>
							</c:forEach>
						</c:if>
						<!-- 검색 O, 리스트 X -->
						<c:if test="${ searchOption and empty bList }">
							<tr>
								<td colspan="5" class="text-center" >"${ keyword }"가 포함된 게시글이 없습니다.</td>
							</tr>
						</c:if>
						<!-- 검색 X, 리스트 X -->
						<c:if test="${ not searchOption and empty bList }">
							<tr>
								<td colspan="5" class="text-center" >게시글이 없습니다.</td>
							</tr>
						</c:if>
						</tbody>
					</table>
				</div>
			</div>
		<!-- 검색 O, 리스트O -->
		<c:if test="${ searchOption and not empty bList }">
			<div class="row" >
				<div class="col" >
					<nav aria-label="PAGE navigation" >
						<ul class="pagination justify-content-center" >
							<c:if test="${ startPage > pageGroup }" >
								<li class="page-item" >
									<a class="page-link" href="atBoardList.mvc?pageNum=${ startPage - pageGroup }&type=${ type }&keyword=${ keyword }" >이전</a>
								</li>
							</c:if>
							<c:forEach var="i" begin="${ startPage }" end="${ endPage }" >
								<c:if test="${ i == currentPage }">
									<li class="page-item active" aria-current="page" >
										<span class="page-link" ></span>
									</li>
								</c:if>
								<c:if test="${ i != currentPage }">
									<li class="page-item" ><a class="page-link" href="atBoardList.mvc?pageNum=${i}&type=${ type }&keyword=${ keyword }" >${i}</a>
									</li>
								</c:if>
							</c:forEach>
							<c:if test="${ endPage < pageCount }">
								<li class="page-item" >
									<a class="page-link" href="atBoardList.mvc?pageNum=${ startPage + pageGroup }&type=${ type }&keyword=${ keyword }" >다음</a>
								</li>
							</c:if>
						</ul>
					</nav>
				</div>
			</div>
		</c:if>
		<!-- 검색 X, 리스트O -->
		<c:if test="${ not searchOption and not empty bList }">
			<div class="row" >
				<div class="col" >
					<nav aria-label="PAGE navigation" >
						<ul class="pagination justify-content-center" >
							<c:if test="${ startPage > pageGroup }" >
								<li class="page-item" >
									<a class="page-link" href="atBoardList.mvc?pageNum=${ startPage - pageGroup }" >이전</a>
								</li>
							</c:if>
							<c:forEach var="i" begin="${ startPage }" end="${ endPage }" >
								<c:if test="${ i == currentPage }">
									<li class="page-item active" aria-current="page" >
										<span class="page-link" ></span>
									</li>
								</c:if>
								<c:if test="${ i != currentPage }">
									<li class="page-item" ><a class="page-link" href="atBoardList.mvc?pageNum=${i}" >${i}</a>
									</li>
								</c:if>
							</c:forEach>
							<c:if test="${ endPage < pageCount }">
								<li class="page-item" >
									<a class="page-link" href="atBoardList.mvc?pageNum=${ startPage + pageGroup }" >다음</a>
								</li>
							</c:if>
						</ul>
					</nav>
				</div>
			</div>
		</c:if>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
