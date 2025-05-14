<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head lang="ko" >
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>맛집 기사 상세보기</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet" >
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.12.1/font/bootstrap-icons.min.css">	
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/member.css" />
<script src="bootstrap/bootstrap.bundle.min.js"></script>
<script src="js/jquery-3.7.1.min.js"></script>
<script src="js/atFormCheck.js"></script>
</head>
<body>
	<div class="container" >
	<!-- header -->
	<%@ include file="../pages/header.jsp" %>
	<!-- content -->
	<div class="row my-5" id="global-content" >
		<div class="col">
			<form name="checkForm" id="checkForm" >
				<input type="hidden" name="at_no" id="at_no" value="${b.at_no }" />
				<input type="hidden" name="pass" id="rPass"/>
				<input type="hidden" name="pageNum" value="${ pageNum }" />
				<c:if test="${ searchOption }">
				<input type="hidden" name="type" value="${ type }" />
				<input type="hidden" name="keyword" value="${ keyword }" />
				</c:if>
			</form>
			<div class="row text-center" >
				<div class="col">
					<h2 class="fs-3 fw-bold" >기사 상세보기</h2>
				</div>
			</div>
			<div class="row my-3" >
				<div class="col" >
					<table class="table table-bordered" >
						<tbody>
							<tr>
								<th class="table-secondary" >제목</th>
								<td colspan="3" >${ b.title }</td>
							</tr>
							<tr>
								<th>글쓴이</th>
								<td>${ board.m_id }</td>
								<th>작성일</th>
								<td><fmt:formatDate value="${ b.p_date }"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td>
									<div class="col-sm-8">
										<input class="form-control" type="password" name="pass" id="pass" />
									</div>
								</td>
								<th>조회수</th>
								<td>${ b.views }</td>
							</tr>
							<tr>
								<td colspan="4" >
									<pre>${ b.content }</pre>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row my-3" >
			<div class="col text-center" >
				<input class="bth btn-warning" type="button" id="atDetailUpdate" value="수정하기"/>&nbsp;&nbsp;
				<input class="bth btn-danger" type="button" id="atDetailDelete" value="삭제하기"/>&nbsp;&nbsp;
				<!-- 검색 X -->
				<c:if test="${not searchOption}">
				<input class="bth btn-success" type="button" value="목록보기" onclick="location.href='atBoardList.mvc?pageNum=${ pageNum }'" />
				</c:if>
				<!-- 검색 O -->
				<c:if test="${searchOption}">
				<input class="bth btn-success" type="button" value="목록보기" onclick="location.href='atBoardList.mvc?pageNum=${ pageNum }&type=${type}&keyword=${keyword}" />
				</c:if>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
