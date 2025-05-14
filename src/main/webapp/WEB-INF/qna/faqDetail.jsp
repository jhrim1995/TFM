<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
<link href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.bundle.min.js"></script>
  <style>
    body {
      background-color: #fdfaf6;
    }
    .custom-title {
      background-color: #fef6e4;
      color: #5e4637;
      border: 2px solid #f0e2c1;
    }
    .table thead {
      background-color: #fbeedb;
      color: #4e3b28;
    }
    .table-bordered th {
      background-color: #f9f0e3;
      color: #5a4231;
      width: 20%;
    }
    .btn-primary {
      background-color: #a38c6b;
      border-color: #a38c6b;
    }
    .btn-primary:hover {
      background-color: #8c765a;
    }
    .btn-warning {
      background-color: #f5e6ca;
      border-color: #f5e6ca;
      color: #4b3621;
    }
    .btn-warning:hover {
      background-color: #e6d3b3;
    }
    .btn-danger {
      background-color: #e8a19b;
      border-color: #e8a19b;
    }
    .btn-danger:hover {
      background-color: #d68f89;
    }
  </style>
</head>

<div class="container my-5">
	<h2 class="text-center mb-4 fw-bold py-3 rounded shadow-sm custom-title">자주 묻는 질문</h2>
		<form name="checkForm" id="checkForm">
			<input type="hidden" name="no" value="${faq.no }" id="faq_no">
			<input type="hidden" name="pass" id="faqPass">
			<input type="hidden" name="pageNum" value="${pageNum }">
			<c:if test="${searchOption }">
				<input type="hidden" name="type" value="${type }">
				<input type="hidden" name="keyword" value="${keyword }">
			</c:if>
		</form>
		
		<div class="card shadow-sm border-0 rounded-4">
			<div class="card-body">
				<table class="table table-bordered align-middle">
					<tbody>
						<tr>
							<th >제 목</th>
							<td colspan="3">${faq.title }</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>${faq.writer }</td>
							<th>작성일</th>
							<td><fmt:formatDate value="${faq.regDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input class="form-control" type="password" name="pass" id="pass"></td>
							<th>조회수</th>
							<td>${faq.readCount }</td>
						</tr>
						<tr>
							<td colspan="4">
								<pre>${faq.content }</pre>
							</td>
						</tr>
					</tbody>	
				</table>
			</div>
		</div>
		
		<div class="row my-3">
			<div class="col text-center">
				<input type="button" class="btn btn-primary" id="faqdetailUpdate" value="수정하기">
				<input type="button" class="btn btn-danger ms-2 me-2" id="faqdetailDelete" value="삭제하기">
				<c:if test="${searchOption }">
					<input type="button" class="btn btn-warning" value="목록보기" onclick="location.href='faqlist.mvc?pageNum=${pageNum }&type=${type }&keyword=${keyword }'">
				</c:if>
				<c:if test="${not searchOption }">
					<input type="button" class="btn btn-warning" value="목록보기" onclick="location.href='faqlist.mvc?pageNum=${pageNum }'">
				</c:if>
			</div>
		</div>
		
		
		
		
	</div>
</div>