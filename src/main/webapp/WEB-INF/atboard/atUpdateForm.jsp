<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>기사 수정하기</title>

</head>
<body>
	<div class="container" >
		
	<!-- content -->
	<div class="row my-5" id="global-content" >
		<div	class="col"	>
			<div class="row text-center" >
				<div class="col" >
					<h2 class="fs-3 fw-bold " >기사 수정하기</h2>
				</div>
			</div>
			<form name="atUpdateForm" action="atUpdateProcess.mvc" id="atUpdateForm" class="row g-3 border-success" method="post" >
				<input type="hidden" name="at_no" value="${ b.at_no }" />
				<input type="hidden" name="pageNum" value="${pageNum}" />
				<c:if test="${ searchOption }">
				<input type="hidden" name="type" value="${ type }" />
				<input type="hidden" name="keyword" value="${ keyword }" />
				</c:if>
				<div class="col-4 offset-md-2" >
					<label for="m_id" class="form-label" >글쓴이</label>
					<input type="text" class="form-control" name="m_id" id="m_id"  placeholder="작성자를 입력해주세요" value="${ b.m_id }">
				</div>
				<div class="col-4" >
					<label for="pass" class="form-label" >비밀번호</label>
					<input type="password" class="form-control" name="pass" id="pass" >
				</div>
				<div class="col-8 offset-md-2" >
					<label for="title" class="form-label">제 목</label>
					<input type="text" class="form-control" name="title" id="title" value="${ b.title }" >
				</div>
				<div class="col-8 offset-md-2" >
					<label for="content" class="form-label" >내 용</label>
						<textarea class="form-control" name="content" id="content" rows="10" >${ b.content }</textarea>
				</div>
				<div class="col-8 offset-md-2 text-center mt-5" >
					<input type="submit" value="수정하기" class="btn btn-success" />&nbsp;&nbsp;
					<!-- 검색 X -->
					<c:if test="${not searchOption}">
					<input class="bth btn-success" type="button" value="목록보기" onclick="location.href='atBoardList.mvc?pageNum=${ pageNum }'" />
					</c:if>
					<!-- 검색 O -->
					<c:if test="${searchOption}">
					<input class="bth btn-success" type="button" value="목록보기" onclick="location.href='atBoardList.mvc?pageNum=${ pageNum }&type=${type}&keyword=${keyword}" />
					</c:if>
				</div>
			</form>
		</div>
	</div>
	</div>
</body>
</html>
