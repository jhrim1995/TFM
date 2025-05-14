<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat" %>
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

     .nav-tabs .nav-link {
         color: #7a5e45;
     }

     .nav-tabs .nav-link.active {
         background-color: #fef1dc;
         border-color: #e7cda2 #e7cda2 #fff;
         color: #5a3f2b;
         font-weight: bold;
     }

     .table thead {
         background-color: #fbeedb;
         color: #4e3b28;
     }

     .table-hover tbody tr:hover {
         background-color: #fcf5ea;
     }

     .btn-outline-success {
         border-color: #d2b48c;
         color: #7b5d41;
     }

     .btn-outline-success:hover {
         background-color: #f5e6ca;
         color: #5a3f2b;
     }

     .btn-primary {
         background-color: #a38c6b;
         border-color: #a38c6b;
     }

     .btn-primary:hover {
         background-color: #8c765a;
         border-color: #8c765a;
     }

     .page-link {
         color: #7b5d41;
     }

     .page-item.active .page-link {
         background-color: #e6cfa7;
         border-color: #e6cfa7;
         color: #4b3621;
     }

     .text-muted {
         color: #8e7760 !important;
     }
   </style>
</head>

<div class="container my-5">
  <h2 class="text-center mb-3 fw-bold py-3 rounded shadow-sm custom-title">Q & A</h2>

    <ul class="nav nav-tabs mb-2 fs-5 bg-light p-6 rounded shadow-sm justify-content-center">
      <li class="nav-item w-50">
          <a class="nav-link active text-center" href="faqlist.mvc">자주 묻는 질문</a>
      </li>
      <li class="nav-item w-50">
          <a class="nav-link text-center" href="inquirylist.mvc">문의사항</a>
      </li>
 		</ul>
<!-- 
  <div class="container mt-4 w-100">
  </div>
 -->
  <div class="row my-3">
    <div class="col">
      <div class="card shadow-sm border-0 rounded-4">
        <div class="card-body p-0">
          <table class="table table-hover align-middle text-center mb-0">
            <thead>
              <tr>
                <th class="col-1">번호</th>
                <th class="col-6">제목</th>
                <th class="col-3">작성자</th>
                <th class="col-2">조회수</th>
          	  </tr>
            </thead>
	          <tbody class="text-secondary">
		          <c:if test="${not empty fList }">
		            <c:forEach var="faq" items="${fList}">
	                <tr>
	                  <td>${faq.no}</td>
	                  <td><a href="faqDetail.mvc?no=${faq.no}&pageNum=${currentPage}&type=${type}&keyword=${keyword}"
	                       	class="text-decoration-none text-dark fw-semibold">${faq.title}</a></td>
	                  <td>${faq.writer}</td>
	                  <td>${faq.readCount}</td>
	                </tr>
		            </c:forEach>
		          </c:if>

	            <c:if test="${searchOption and empty fList }">
	              <tr>
	                <td colspan="5" class="text-center text-muted fst-italic">
	                    "${keyword}"가 포함된 게시글이 존재하지 않음
	                </td>
	              </tr>
	            </c:if>

	            <c:if test="${not searchOption and empty fList }">
	              <tr>
	                <td colspan="5" class="text-center text-muted fst-italic">
	                    게시글이 존재하지 않음
	                </td>
	              </tr>
	            </c:if>
	          </tbody>
	        </table>
	      </div>
	    </div>
	  </div>
	</div>
		
	<c:if test="${searchOption and not empty fList}">
    <div class="row">
      <div class="col">
        <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
            <c:if test="${startPage > pageGroup }">
              <li class="page-item">
                <a class="page-link" href="faqlist.mvc?pageNum=${startPage - pageGroup}&type=${type}&keyword=${keyword}">이전</a>
              </li>
	          </c:if>
	          <c:forEach var="i" begin="${startPage}" end="${endPage}">
              <c:if test="${i == currentPage }">
                <li class="page-item">
                  <span class="page-link active">${i}</span>
                </li>
	            </c:if>
	            <c:if test="${i != currentPage }">
                <li class="page-item">
                  <a class="page-link" href="faqlist.mvc?pageNum=${i}&type=${type}&keyword=${keyword}">${i}</a>
                </li>
              </c:if>
	          </c:forEach>
	          <c:if test="${endPage < pageCount}">
              <li class="page-item">
                <a class="page-link" href="faqlist.mvc?pageNum=${startPage - pageGroup}&type=${type}&keyword=${keyword}">다음</a>
              </li>
            </c:if>
          </ul>
        </nav>
      </div>
    </div>
  </c:if>
		
	<c:if test="${not searchOption and not empty fList}">
    <div class="row">
      <div class="col">
        <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
            <c:if test="${startPage > pageGroup }">
              <li class="page-item">
                  <a class="page-link" href="faqlist.mvc?pageNum=${startPage - pageGroup}">이전</a>
              </li>
            </c:if>
            <c:forEach var="i" begin="${startPage}" end="${endPage}">
              <c:if test="${i == currentPage }">
                  <li class="page-item">
                      <span class="page-link active">${i}</span>
                  </li>
              </c:if>
              <c:if test="${i != currentPage }">
                  <li class="page-item">
                      <a class="page-link" href="faqlist.mvc?pageNum=${i}">${i}</a>
                  </li>
              </c:if>
            </c:forEach>
            <c:if test="${endPage < pageCount}">
              <li class="page-item">
                  <a class="page-link" href="faqlist.mvc?pageNum=${startPage - pageGroup}">다음</a>
              </li>
            </c:if>
          </ul>
        </nav>
      </div>
    </div>
 	</c:if>
		
	<form name="searchForm" id="faqsearchForm" action="#" 
				class="row my-3 text-center justify-content-center align-items-center">
    <div class="col text-start">
    	<c:if test="${searchOption }">
	      <a href="faqlist.mvc" class="btn btn-outline-success">리스트</a>
    	</c:if>
    </div>
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
    <div class="col text-end">
      <a href="faqwriteForm.mvc" class="btn btn-outline-success">글쓰기</a>
    </div>
  </form>
		
	<c:if test="${searchOption }">
    <div class="row">
      <div class="col text-center text-muted fst-italic">
          "${keyword}" 검색 결과
      </div>
    </div>
 	</c:if>
</div>
