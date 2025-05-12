<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="js/loginCheck.js"></script>
	<div class="container">
		
		<div class="row my-5 text-start">
			<div class="col">
					<a class="text-decoration-none fs-3" href = "loginForm.mvc"><img src = "images/logo.jpg" height="100" width="400"/></a>
			</div>
		</div>
	
		<form class="row justify-content-center my-3" name="joinMemberForm" action="joinMember.mvc" method="post" id="joinMemberForm" >
				<table class="table">
					<tr><td>
						<input class="form-control" type="text"  name="id" id="id" placeholder="아이디">
						<input class="form-control" type="password" name="pass" id="pass" placeholder="비밀번호">
						<input class="form-control" type="email" name="email" id="email" placeholder="[선택] 이메일 주소 (비밀번호 찾기 등 확인용)">
					</td></tr>
					<tr><td>
						<input class="form-control" type="text"  name="name" id="name" placeholder="이름">
						<input class="form-control" type="text"  name="nickname" id="nickname" placeholder="닉네임">
						<input class="form-control" type="text"  name="birthday" id="birthday"  placeholder="생년월일(8글자)">
					</td></tr>
					<tr><td>
						<input class="btn-check" type="radio"  name="gender" id="gender1" value="남성" checked><label class="btn" for="gender1">남자</label>
						<input class="btn-check" type="radio"  name="gender" id="gender2" value="여성"><label class="btn" for="gender2">여자</label>
						<input class="btn-check" type="radio"  name="foreign" id="foreign1" value="K" checked><label class="btn" for="foreign1">내국인</label>
						<input class="btn-check" type="radio"  name="foreign" id="foreign2" value="F"><label class="btn" for="foreign2">외국인</label>
					</td></tr>
					<tr><td>
						<select class="form-select form-select mb-3" name = "telecom">
						  <option value="SKT">SKT</option>
						  <option value="KT">KT</option>
						  <option value="LG">LGU+</option>
						  <option value="SKTap">SKT알뜰폰</option>
						  <option value="KTap">KT알뜰폰</option>
						  <option value="LGap">LGU+알뜰폰</option>
						</select>
						<input class="form-control" type="text"  name="phone"  id="phone" placeholder="핸드폰 번호">
					</td></tr>
				</table>
				<input class="btn btn-lg btn-dark" type="submit" value="가입 신청" >
		</form>	
	
	
	</div>