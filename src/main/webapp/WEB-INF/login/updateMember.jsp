<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="js/loginCheck.js"></script>
	<div class="container">
		<div class="col bg-body mx-auto">	
		
		<div class="row text-center">
			<div class="col my-4">
					<a class="text-decoration-none fs-3" href = "main.mvc"><img src = "images/logo.jpg" height="100" width="400"/></a>
			</div>
		</div>
		
		<div class="row">
			<div class="col text-end mx-3">
				<form name="cancleMembershipForm" id="cancleMembershipForm" action="cancleMembership.mvc" method="post">
					<input type="hidden" name="cancleId" id="cancleId" value="${Member.m_id}" readonly>
					<input type="hidden" name="canclePass" id="canclePass">
					<input type="submit" class="btn btn-dark"  value="회원 탈퇴" >
				</form>
			</div>
		</div>
	
		<form class="row-5 justify-content-center my-3" name="updateProfileForm" action="updateProfile.mvc" method="post" id="updateProfileForm" >
				<table class="table">
					<tr><td>
						<input class="form-control my-2 bg-secondary-subtle" type="text"  name="id" id="id" value="${Member.m_id}" readonly>
						<input class="form-control my-2" type="password" name="oldPass" id="oldPass" placeholder="이전 비밀번호">
						<input class="form-control my-2" type="password" name="pass" id="pass" placeholder="새로운 비밀번호 (회원 탈퇴시 입력하지 않아도 됩니다.)">
						<input class="form-control my-2" type="email" name="email" id="email" placeholder="[선택] 이메일 주소 (비밀번호 찾기 등 확인용)" value = "${Member.email}">
						<div class="text-danger text-center" id="info1"></div>
					</td></tr>
					<tr><td>
						<input class="form-control my-2" type="text"  name="name" id="name" placeholder="이름" value = "${Member.m_name}">
						<input class="form-control my-2" type="text"  name="nickname" id="nickname" placeholder="닉네임" value = "${Member.nickname}">
						<input class="form-control my-2 bg-secondary-subtle" type="text"  name="birthday" id="birthday"  placeholder="생년월일(8글자)" value = "${Member.birthday}" readonly>
						<div class="text-danger text-center" id="info2"></div>
					</td></tr>
					<tr><td>
						<input class="form-control my-2 bg-secondary-subtle" type="text"  name="gender" id="gender" value = "${Member.gender}" readonly>
						<input class="form-control my-2 bg-secondary-subtle" type="text"  name="foreign" id="foreign" value = '${Member.foreignyn.equals("K") ? "내국인" : "외국인"}' readonly>
					</td></tr>
					<tr><td>
						<select class="form-select my-2 form-select mb-3" name = "telecom">
						  <option value="SKT" ${Member.telecom.equals("SKT") ? "selected" : ""}>SKT</option>
						  <option value="KT" ${Member.telecom.equals("KT") ? "selected" : ""}>KT</option>
						  <option value="LG" ${Member.telecom.equals("LG") ? "selected" : ""}>LGU+</option>
						  <option value="SKTap" ${Member.telecom.equals("SKTap") ? "selected" : ""}>SKT알뜰폰</option>
						  <option value="KTap" ${Member.telecom.equals("KTap") ? "selected" : ""}>KT알뜰폰</option>
						  <option value="LGap" ${Member.telecom.equals("LGap") ? "selected" : ""}>LGU+알뜰폰</option>
						</select>
						<input class="form-control my-2" type="text"  name="phone"  id="phone" placeholder="핸드폰 번호 ( - 포함 해주세요.)" value = "${Member.phone}">
						<div class="text-danger text-center" id="info3"></div>
					</td></tr>
				</table>
				
				<div class="row">
					<div class="col text-center my-4">
						<input class="btn btn-lg btn-dark mx-5" type="submit" value="수정하기" >
						<input class="btn btn-lg btn-dark mx-5" type="reset" value="다시쓰기" >
					</div>
				</div>
		</form>	
	
		</div>
	</div>