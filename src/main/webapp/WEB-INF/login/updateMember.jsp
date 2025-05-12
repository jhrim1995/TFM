<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="js/loginCheck.js"></script>
	<div class="container">
		
		<div class="row my-5 text-start">
			<div class="col">
					<a class="text-decoration-none fs-3" href = "main.mvc"><img src = "images/logo.jpg" height="100" width="400"/></a>
			</div>
		</div>
	
		<form class="row justify-content-center my-3" name="updateProfileForm" action="updateProfile.mvc" method="post" id="updateProfileForm" >
				<table class="table">
					<tr><td>
						<input class="form-control" type="text"  name="id" id="id" value="${Member.m_id}" disabled readonly>
						<input class="form-control" type="password" name="oldPass" id="oldPass" placeholder="이전 비밀번호">
						<input class="form-control" type="password" name="pass" id="pass" placeholder="새로운 비밀번호 (비밀번호를 그대로 유지하면 이전과 같게 입력하세요.)">
						<input class="form-control" type="email" name="email" id="email" placeholder="[선택] 이메일 주소 (비밀번호 찾기 등 확인용)" value = "${Member.email}">
					</td></tr>
					<tr><td>
						<input class="form-control" type="text"  name="name" id="name" placeholder="이름" value = "${Member.m_name}">
						<input class="form-control" type="text"  name="nickname" id="nickname" placeholder="닉네임" value = "${Member.nickname}">
						<input class="form-control" type="text"  name="birthday" id="birthday"  placeholder="생년월일(8글자)" value = "${Member.birthday}" disabled readonly>
					</td></tr>
					<tr><td>
						<input class="form-control" type="text"  name="gender" id="gender" value = "${Member.gender}" disabled readonly>
						<input class="form-control" type="text"  name="foreign" id="foreign" value = '${Member.foreignyn.equals("K") ? "내국인" : "외국인"}' disabled readonly>
					</td></tr>
					<tr><td>
						<select class="form-select form-select mb-3" name = "telecom">
						  <option value="SKT" ${Member.telecom.equals("SKT") ? "selected" : ""}>SKT</option>
						  <option value="KT" ${Member.telecom.equals("KT") ? "selected" : ""}>KT</option>
						  <option value="LG" ${Member.telecom.equals("LG") ? "selected" : ""}>LGU+</option>
						  <option value="SKTap" ${Member.telecom.equals("SKTap") ? "selected" : ""}>SKT알뜰폰</option>
						  <option value="KTap" ${Member.telecom.equals("KTap") ? "selected" : ""}>KT알뜰폰</option>
						  <option value="LGap" ${Member.telecom.equals("LGap") ? "selected" : ""}>LGU+알뜰폰</option>
						</select>
						<input class="form-control" type="text"  name="phone"  id="phone" placeholder="핸드폰 번호" value = "${Member.phone}">
					</td></tr>
				</table>
				<input class="btn btn-lg btn-dark" type="submit" value="수정하기" >
		</form>	
	
	
	</div>