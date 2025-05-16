<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="ko" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>기사 쓰기</title>
<script src="bootstrap/bootstrap.bundle.min.js"></script>
<script src="js/jquery-3.7.1.min.js"></script>
<script src="js/atFormCheck.js"></script>
</head>
<body>
	<div class="container" >
		
	<!-- content -->
	<div class="row my-5" id="global-content" >
		<div	class="col"	>
			<div class="row text-center" >
				<div class="col" >
					<h2 class="fs-3 fw-bold " >기사 쓰기</h2>
				</div>
			</div>
			<form name="writeForm" action="atWriteProcess.mvc" id="atWriteForm" class="row g-3 border-success" method="post" >
				<div class="col-4 offset-md-2" >
					<label for="m_id" class="form-label" >글쓴이</label>
					<input type="text" class="form-control" name="m_id" id="m_id"  placeholder="작성자를 입력해주세요" >
				</div>
				<div class="col-4" >
					<label for="pass" class="form-label" >비밀번호</label>
					<input type="password" class="form-control" name="pass" id="pass" >
				</div>
				<div class="col-8 offset-md-2" >
					<label for="title" class="form-label">제 목</label>
					<input type="text" class="form-control" name="title" id="title" >
				</div>
				<div class="col-8 offset-md-2" >
					<label for="content" class="form-label" >내 용</label>
						<textarea class="form-control" name="content" id="content" rows="10" ></textarea>
				</div>
				<div class="col-8 offset-md-2 text-center mt-5" >
					<input type="submit" value="등록하기" class="btn btn-success" />&nbsp;&nbsp;
					<input type="button" value="목록보기" onclick="location.href='atBoardList.mvc'" class="btn btn-success" />
				</div>
			</form>
		</div>
	</div>
	</div>
</body>
</html>
