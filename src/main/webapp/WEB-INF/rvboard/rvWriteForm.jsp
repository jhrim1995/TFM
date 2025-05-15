<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.7.1.min.js"></script>
 <script src="js/rvformcheck.js"></script> 
</head>
<body>
<div class="container">
<!-- header -->

<!-- content  -->
<div class="row my-5" id="global-content">
  <h2 class="fs-3 fw-bold text-center">리뷰 쓰기~</h2>
  
  <form name="rvWriteForm" action="rvWriteProcess.mvc" id="rvWriteForm"
   			class="row g-3 border-primary" method="post" enctype="multipart/form-data">
   <div class="col-8 offset-md-2">
      <label for="m_id" class="form-label">ID</label>
      <input type="text" class="form-control" name="m_id" id="m_id">
    </div>
    <div class="col-8 offset-md-2">
      <label for="title" class="form-label">제 목</label>
      <input type="text" class="form-control" name="title" id="title">
    </div>
    <div>
      <label for="area" class="form-label">지 역</label>
      <input type="text" class="form-control" name="area" id="area">
    </div>
    <div>
      <label for="content" class="form-label">내 용</label>
        <textarea class="form-control" name="content" id="content" rows="10"></textarea>
    </div>
    <div class="col-8 offset-md-2">
    	<label for="file1" class="form-label">파 일</label>
    	<input type="file" class="form-control" name="file1" id="file1">
    </div>
    <div class="col-8 offset-md-2 text-center mt-5">
      <input type="submit" value="등록하기" class="btn btn-primary">
      <input type="button" value="목록보기" onclick="location.href='rvBoardList.mvc'" class="btn btn-primary">
    </div>
      </form>

</div>
<!-- footer -->

</div>
  <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>