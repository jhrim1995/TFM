<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="js/loginCheck.js"></script>

<div class="row my-5 text-center">
	<span class="fs-1 fw-bold">
		<c:if test="${findThing.equals('id')}">아이디 찾기</c:if>
		<c:if test="${findThing.equals('pass')}">비밀번호 찾기</c:if>	
	</span>
</div>


<form name="searchIdPassForm" id="searchIdPassForm" action="searchIdPass.mvc" method="post">

	<input type="hidden" name="findThing" id="findThing" value="${findThing}">
	
	<c:if test="${findThing.equals('pass')}">
		<div class="row my-2">
						<input class="form-control" type="text"  name="id" id="id" placeholder="아이디">
		</div>
	</c:if>
	
	<div class="row my-2">
			<input class="form-control" type="text"  name="name" id="name" placeholder="이름">
	</div>
	
	<div class="row my-2">
		<select class="form-select form-select mb-3" name = "telecom">
				<option value="SKT">SKT</option>
				<option value="KT">KT</option>
				<option value="LG">LGU+</option>
				<option value="SKTap">SKT알뜰폰</option>
				<option value="KTap">KT알뜰폰</option>
				<option value="LGap">LGU+알뜰폰</option>
		</select>
	</div>
	
	<div class="row my-2">
					<input class="form-control" type="text"  name="phone"  id="phone" placeholder="핸드폰 번호 ( - 포함 해주세요.)">
	</div>
	<div class="text-danger text-center" id="info"></div>
	<div class="row my-2">
					<input class="btn btn-lg btn-dark" type="submit" value="찾기" >
	</div>
</form>
