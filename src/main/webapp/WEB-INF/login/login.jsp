<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/loginCheck.js"></script>

<div class="container">
	<div class="col-7 bg-body mx-auto rounded-5">	
	
		<div class="row text-center">
			<div class="col my-5">
					<a href="main.mvc"><img src = "images/logo.jpg" height="100" width="400"/></a>
			</div>
		</div>
		
			<div class = "row">
					<div class="col">
						<form class="row" name="loginForm"  id="loginForm" action="loginCheck.mvc" method="post">
							<div class = "col">
								<div class="row">
									<div class="col-5 mx-auto my-2">
										<input class="form-control" type="text"  name="id" id = "userId" placeholder="아이디를 입력해주세요.">
									</div>
								</div>
								<div class="row">
									<div class="col-5 mx-auto">
										<input class="form-control" type="password" name="pass" id="pass" placeholder="비밀번호를 입력해주세요.">
									</div>
								</div>
								<div class="row my-2">
									<div class="d-grid col-5 mx-auto">
										<input class="btn btn-lg btn-dark" type="submit" value="로그인" >
									</div>
								</div>
							</div>
						</form>
					</div>
			</div>
	
		
		<div class="row text-center">
			<div class="col my-5">
				<a class="text-decoration-none mx-3"  href = "searchIdForm.mvc">아이디찾기 </a> | <a class="text-decoration-none mx-3" href = "searchPassForm.mvc">비밀번호찾기 </a> | <a class="text-decoration-none mx-3" href = "joinMemberForm.mvc"> 회원가입 </a>
			</div>
		</div>
		
	</div>		
</div>