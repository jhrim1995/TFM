/**
 * 
 */
$(function(){
	
	// 내정보 다시쓰기 클릭 시
	$("#updateProfileForm").on("reset",function(){
		
			$("#info1").text("");
			$("#info2").text("");
			$("#info3").text("");

			$("#oldPass").css("background-color", "white");
			$("#oldPass").css("color", "black");
			$("#oldPass").css("text-decoration", "none");
			
			$("#pass").css("background-color", "white");
			$("#pass").css("color", "black");
			$("#pass").css("text-decoration", "none");
			
			$("#email").css("background-color", "white");
			$("#email").css("color", "black");
			$("#email").css("text-decoration", "none");

			$("#name").css("background-color", "white");
			$("#name").css("color", "black");
			$("#name").css("text-decoration", "none");		
			
			$("#nickname").css("background-color", "white");
			$("#nickname").css("color", "black");
			$("#nickname").css("text-decoration", "none");						
			
			$("#phone").css("background-color", "white");
			$("#phone").css("color", "black");
			$("#phone").css("text-decoration", "none");				
			
			reset();
			
	});
	
	
	// 회원가입 다시쓰기 클릭 시
	$("#joinMemberForm").on("reset",function(){
		
			$("#info1").text("");
			$("#info2").text("");
			$("#info3").text("");
			
			$("#id").css("background-color", "white");
			$("#id").css("color", "black");
			$("#id").css("text-decoration", "none");

			$("#pass").css("background-color", "white");
			$("#pass").css("color", "black");
			$("#pass").css("text-decoration", "none");
			
			$("#email").css("background-color", "white");
			$("#email").css("color", "black");
			$("#email").css("text-decoration", "none");

			$("#name").css("background-color", "white");
			$("#name").css("color", "black");
			$("#name").css("text-decoration", "none");		
			
			$("#nickname").css("background-color", "white");
			$("#nickname").css("color", "black");
			$("#nickname").css("text-decoration", "none");		
											
			$("#birthday").css("background-color", "white");
			$("#birthday").css("color", "black");
			$("#birthday").css("text-decoration", "none");					
			
			$("#phone").css("background-color", "white");
			$("#phone").css("color", "black");
			$("#phone").css("text-decoration", "none");				
			
			reset();
			
	});
	
	
	// 회원가입 ID 입력 시
	$("#joinMemberForm").on("focusout","#id",function(){
		let idRegExp = /^[A-Za-z0-9]*$/;
		
		if($("#id").val().length <=  0){
			$("#info1").text("아이디는 필수 입력입니다. 아이디를 입력해주세요.");
			$("#id").css("background-color", "#F76159");
			$("#id").css("text-decoration", "none");
			return false;
		}else	if(!idRegExp.test($("#id").val())){
			$("#info1").text("아이디에 한글이나 특수문자를 입력할 수 없습니다.");
			$("#id").css("background-color", "#F76159");
			$("#id").css("color", "white");
			$("#id").css("text-decoration", "underline");
			return false;
		}else{
			$("#info1").text("");
			$("#id").css("background-color", "white");
			$("#id").css("color", "black");
			$("#id").css("text-decoration", "none");
		}

		
		$.ajax({
			url : "overlapIdCheck.ajax",
			data : { id : $("#id").val() },
			datatype : "json",
			success : function(resData){ 
								console.log(resData); 			
								if(resData.result == "true"){
									$("#info1").text("이미 사용중인 아이디 입니다.");
									$("#id").css("background-color", "#F76159");
									$("#id").css("color", "white");
									$("#id").css("text-decoration", "underline");
								}else{
									$("#info1").text("");
									$("#id").css("background-color", "white");
									$("#id").css("color", "black");
									$("#id").css("text-decoration", "none");
								}
							},
			error : function(xhr, status, error){ console.log("error : " , xhr, " - ", xhr.statusText, " - ",status , " - " , error); }
		});
		
	}); // 회원가입 ID 입력 시
	
	
	// 회원가입시 비밀번호 포커스 아웃
	$("#joinMemberForm").on("focusout","#pass",function(){
		if($("#pass").val().length <=  0){
						$("#info1").text("비밀번호를 입력해주세요.");
						$("#pass").css("background-color", "#F76159");
						$("#pass").css("text-decoration", "none");
						return false;
			}else{
				$("#info1").text("");
				$("#pass").css("background-color", "white");
				$("#pass").css("color", "black");
				$("#pass").css("text-decoration", "none");
			}
	}); // 회원가입시 비밀번호 포커스 아웃
	
	
	// 회원가입시 이름 포커스 아웃
		$("#joinMemberForm").on("focusout","#name",function(){
			let nameRegExp = /^[A-Za-z가-힣]*$/;
			if($("#name").val().length <=  0){
					$("#info2").text("이름을 입력해주세요.");
					$("#name").css("background-color", "#F76159");
					$("#name").css("text-decoration", "none");
					return false;
			}else	if(!nameRegExp.test($("#name").val())){
					$("#info2").text("이름을 한글이나 영문으로 입력해주세요.");
					$("#name").css("background-color", "#F76159");
					$("#name").css("color", "white");
					$("#name").css("text-decoration", "underline");
					return false;
			}else{
					$("#info2").text("");
					$("#name").css("background-color", "white");
					$("#name").css("color", "black");
					$("#name").css("text-decoration", "none");
				}
		}); // 회원가입시 이름 포커스 아웃
		
		
		// 회원가입 시 닉네임 포커스 아웃
		$("#joinMemberForm").on("focusout","#nickname",function(){
			if($("#nickname").val().length <=  0){
					$("#info2").text("닉네임을 입력해주세요.");
					$("#nickname").css("background-color", "#F76159");
					$("#nickname").css("text-decoration", "none");
					return false;
			}else{
					$("#info2").text("");
					$("#nickname").css("background-color", "white");
					$("#nickname").css("color", "black");
					$("#nickname").css("text-decoration", "none");
			}
		});// 회원가입 시 닉네임 포커스 아웃
		
		// 회원가입 시 생일 포커스 아웃
		$("#joinMemberForm").on("focusout","#birthday",function(){
			let birthdayRegExp = /^[0-9]*$/;
			if($("#birthday").val().length <=  0){
				$("#info2").text("생년월일을 입력해주세요.");
				$("#birthday").css("background-color", "#F76159");
				$("#birthday").css("text-decoration", "none");
				return false;
			}else if($("#birthday").val().length !=  8){
				$("#info2").text("생년월일을 양식에 맞게 입력해주세요. (ex.20250101)");
				$("#birthday").css("background-color", "#F76159");
				$("#birthday").css("color", "white");
				$("#birthday").css("text-decoration", "underline");
				return false;
			}else if(!birthdayRegExp.test($("#birthday").val())){
				$("#info2").text("생년월일을 숫자로만 입력해주세요.");
				$("#birthday").css("background-color", "#F76159");
				$("#birthday").css("color", "white");
				$("#birthday").css("text-decoration", "underline");
				return false;
			}else{
				$("#info2").text("");
				$("#birthday").css("background-color", "white");
				$("#birthday").css("color", "black");
				$("#birthday").css("text-decoration", "none");
			}
		});// 회원가입 시 생일 포커스 아웃
		
		
		// 회원 시 휴대전화 포커스 아웃
		$("#joinMemberForm").on("focusout","#phone",function(){
				let phoneRegExp = /^01([0|1|6|7|8|9]?)-?([0-9]{4})-?([0-9]{4})$/;
				
				if($("#phone").val().length <=  0){
								$("#info3").text("휴대전화 번호를 입력해주세요.");
								$("#phone").css("background-color", "#F76159");
								$("#phone").css("text-decoration", "none");
								return false;
				}else if($("#phone").val().length != 13){
								$("#info3").text("13자 이상입력하세요.");
								$("#phone").css("background-color", "#F76159");
								$("#phone").css("color", "white");
								$("#phone").css("text-decoration", "underline");
								return false;
				}else if(!phoneRegExp.test($("#phone").val())){
								$("#info3").text("핸드폰 번호를 양식에 맞게 입력해주세요.(ex.010-0000-0000)");
								$("#phone").css("background-color", "#F76159");
								$("#phone").css("color", "white");
								$("#phone").css("text-decoration", "underline");
								return false;
				}else{
								$("#info3").text("");
								$("#phone").css("background-color", "white");
								$("#phone").css("color", "black");
								$("#phone").css("text-decoration", "none");
				}
		});// 회원 시 휴대전화 포커스 아웃
	
	
	// 아이디, 비밀번호 찾을 때
	$("#searchIdPassForm").on("submit", function(){
		
			let idRegExp = /^[A-Za-z0-9]*$/;	
		
			if($("#findThing").val() == "pass"){
				if($("#id").val().length <=  0){
					$("#info").text("아이디는 필수 입력입니다. 아이디를 입력해주세요.");
					$("#id").css("background-color", "#F76159");
					$("#id").css("text-decoration", "none");
					return false;
				}else if(!idRegExp.test($("#id").val())){
					$("#info").text("아이디에 한글이나 특수문자를 입력할 수 없습니다.");
					$("#id").css("background-color", "#F76159");
					$("#id").css("color", "white");
					$("#id").css("text-decoration", "underline");
					return false;
				}else{
					$("#info").text("");
					$("#id").css("background-color", "white");
					$("#id").css("color", "black");
					$("#id").css("text-decoration", "none");
				}
			}
			
			let nameRegExp = /^[A-Za-z가-힣]*$/;
			
			if($("#name").val().length <=  0){
				$("#info").text("이름을 입력해주세요.");
				$("#name").css("background-color", "#F76159");
				$("#name").css("text-decoration", "none");
				return false;
			}else if(!nameRegExp.test($("#name").val())){
				$("#info").text("이름을 한글이나 영문으로 입력해주세요.");
				$("#name").css("background-color", "#F76159");
				$("#name").css("color", "white");
				$("#name").css("text-decoration", "underline");
				return false;
			}else{
				$("#info").text("");
				$("#name").css("background-color", "white");
				$("#name").css("color", "black");
				$("#name").css("text-decoration", "none");
			}
			
		let phoneRegExp = /^01([0|1|6|7|8|9]?)-?([0-9]{4})-?([0-9]{4})$/;
		
		if($("#phone").val().length <= 0 ){
			$("#info").text("핸드폰 번호를 선택해주세요");
			$("#phone").css("background-color", "#F76159");
			$("#phone").css("text-decoration", "none");
			return false;
		}else if($("#phone").val().length != 13 ){
			$("#info").text("핸드폰 번호를 양식에 맞게 입력해주세요.(ex.010-0000-0000)");
			$("#phone").css("background-color", "#F76159");
			$("#phone").css("text-decoration", "none");
			return false;
		}else if(!phoneRegExp.test($("#phone").val())){
			$("#info").text("핸드폰 번호를 양식에 맞게 입력해주세요.(ex.010-0000-0000)");
			$("#phone").css("background-color", "#F76159");
			$("#phone").css("color", "white");
			$("#phone").css("text-decoration", "underline");
			return false;
		}else{
			$("#info").text("");
			$("#phone").css("background-color", "white");
			$("#phone").css("color", "black");
			$("#phone").css("text-decoration", "none");
		}

				
		}); // 아이디, 비밀번호 찾을 때
	
		
	// 회원 수정에서 이전 비밀번호를 입력 벗어날때
	$("#updateProfileForm").on("focusout", "#oldPass" ,function(){
		if($("#oldPass").val().length <=  0){
						$("#info1").text("이전 비밀번호를 입력해 주세요");
						$("#oldPass").css("background-color", "#F76159");
						$("#oldPass").css("text-decoration", "none");
						return false;
		}else{
						$("#info1").text("");
						$("#oldPass").css("background-color", "white");
						$("#oldPass").css("color", "black");
						$("#oldPass").css("text-decoration", "none");
		}
	});// 회원 수정에서 이전 비밀번호를 입력 벗어날때
	
	// 회원 수정에서 새로운 비밀번호를 입력 벗어날때
	$("#updateProfileForm").on("focusout", "#pass" ,function(){
		if($("#pass").val().length <=  0){
						$("#info1").text("새로운 비밀번호를 입력해 주세요.");
						$("#pass").css("background-color", "#F76159");
						$("#pass").css("text-decoration", "none");
							return false;
		}else{
						$("#info1").text("");
						$("#pass").css("background-color", "white");
						$("#pass").css("color", "black");
						$("#pass").css("text-decoration", "none");
		}
	});// 회원 수정에서 새로운 비밀번호를 입력 벗어날때
	
	// 회원 수정에서 이름 포커스 아웃
	$("#updateProfileForm").on("focusout","#name",function(){
		let nameRegExp = /^[A-Za-z가-힣]*$/;
		
		if($("#name").val().length <=  0){
				$("#info2").text("이름을 입력해주세요.");
				$("#name").css("background-color", "#F76159");
				$("#name").css("text-decoration", "none");
				return false;
		}else if(!nameRegExp.test($("#name").val())){
				$("#info2").text("이름을 한글이나 영문으로 입력해주세요.");
				$("#name").css("background-color", "#F76159");
				$("#name").css("color", "white");
				$("#name").css("text-decoration", "underline");
				return false;
		}else{
				$("#info2").text("");
				$("#name").css("background-color", "white");
				$("#name").css("color", "black");
				$("#name").css("text-decoration", "none");
		}
	});// 회원 수정에서 이름 포커스 아웃
	
	// 회원 수정에서 닉네임 포커스 아웃
	$("#updateProfileForm").on("focusout","#nickname",function(){
		if($("#nickname").val().length <=  0){
				$("#info2").text("닉네임을 입력해주세요.");
				$("#nickname").css("background-color", "#F76159");
				$("#nickname").css("text-decoration", "none");
				return false;
		}else{
				$("#info2").text("");
				$("#nickname").css("background-color", "white");
				$("#nickname").css("color", "black");
				$("#nickname").css("text-decoration", "none");
		}
	});// 회원 수정에서 닉네임 포커스 아웃
	
	// 회원수정 시 휴대전화 포커스 아웃
	$("#updateProfileForm").on("focusout","#phone",function(){
		let phoneRegExp = /^01([0|1|6|7|8|9]?)-?([0-9]{4})-?([0-9]{4})$/;
		if($("#phone").val().length <=  0){
				$("#info3").text("휴대전화 번호를 입력해주세요.");
				$("#phone").css("background-color", "#F76159");
				$("#phone").css("text-decoration", "none");
				return false;
		}else if($("#phone").val().length != 13){
				$("#info3").text("핸드폰 번호를 양식에 맞게 입력해주세요.(ex.010-0000-0000)");
				$("#phone").css("background-color", "#F76159");
				$("#phone").css("color", "white");
				$("#phone").css("text-decoration", "underline");
				return false;
		}else if(!phoneRegExp.test($("#phone").val())){
				$("#info3").text("핸드폰 번호를 양식에 맞게 입력해주세요.(ex.010-0000-0000)");
				$("#phone").css("background-color", "#F76159");
				$("#phone").css("color", "white");
				$("#phone").css("text-decoration", "underline");
				return false;
		}else{
				$("#info3").text("");
				$("#phone").css("background-color", "white");
				$("#phone").css("color", "black");
				$("#phone").css("text-decoration", "none");
		}
	});// 회원수정 시 휴대전화 포커스 아웃
	
	// 회원 탈퇴 버튼을 누를 때
	$("#cancleMembershipForm").on("submit", function(){
			
			if($("#oldPass").val().length <=  0){
				$("#info1").text("이전 비밀번호를 입력해 주세요");
				$("#oldPass").css("background-color", "#F76159");
				$("#oldPass").css("text-decoration", "none");
				return false;
			}else{
				$("#info1").text("");
				$("#oldPass").css("background-color", "white");
				$("#oldPass").css("color", "black");
				$("#oldPass").css("text-decoration", "none");
			}
			
			let result = confirm("정말로 회원을 탈퇴 하시겠습니까?");
			
			if(! result){
				
				return false;	
			
			}
			
			$("#canclePass").val($("#oldPass").val());
			
	}); // 회원 탈퇴 버튼을 누를 때
	
	// 내 정보 수정 할 때 
	$("#updateProfileForm").on("submit", function(){
		
			if($("#oldPass").val().length <=  0){
					$("#info1").text("이전 비밀번호를 입력해 주세요.");
					$("#oldPass").css("background-color", "#F76159");
					$("#oldPass").css("text-decoration", "none");
					return false;
			}else{
					$("#info1").text("");
					$("#oldPass").css("background-color", "white");
					$("#oldPass").css("color", "black");
					$("#oldPass").css("text-decoration", "none");
			}
			
			if($("#pass").val().length <=  0){
					$("#info1").text("새로운 비밀번호를 입력해주세요.");
					$("#pass").css("background-color", "#F76159");
					$("#pass").css("text-decoration", "none");
					return false;
			}else{
					$("#info1").text("");
					$("#pass").css("background-color", "white");
					$("#pass").css("color", "black");
					$("#pass").css("text-decoration", "none");
			}
		
			let nameRegExp = /^[A-Za-z가-힣]*$/;
			
			if($("#name").val().length <=  0){
					$("#info2").text("이름을 입력해주세요.");
					$("#name").css("background-color", "#F76159");
					$("#name").css("text-decoration", "none");
					return false;
			}else if(!nameRegExp.test($("#name").val())){
					$("#info2").text("이름을 한글이나 영문으로 입력해주세요.");
					$("#name").css("background-color", "#F76159");
					$("#name").css("color", "white");
					$("#name").css("text-decoration", "underline");
					return false;
			}else{
					$("#info2").text("");
					$("#name").css("background-color", "white");
					$("#name").css("color", "black");
					$("#name").css("text-decoration", "none");
			}
				
			if($("#nickname").val().length <=  0){
					$("#info2").text("닉네임을 입력해주세요.");
					$("#nickname").css("background-color", "#F76159");
					$("#nickname").css("text-decoration", "none");
								return false;
			}else{
					$("#info2").text("");
					$("#nickname").css("background-color", "white");
					$("#nickname").css("color", "black");
					$("#nickname").css("text-decoration", "none");
			}
			
			let phoneRegExp = /^01([0|1|6|7|8|9]?)-?([0-9]{4})-?([0-9]{4})$/;
			
			if($("#phone").val().length <= 0 ){
					$("#info3").text("핸드폰 번호를 선택해주세요");
					$("#phone").css("background-color", "#F76159");
					$("#phone").css("text-decoration", "none");
					return false;
			}else if($("#phone").val().length != 13 ){
					$("#info3").text("핸드폰 번호를 양식에 맞게 입력해주세요.(ex.010-0000-0000)");
					$("#phone").css("background-color", "#F76159");
					$("#phone").css("text-decoration", "none");
					return false;
			}else if(!phoneRegExp.test($("#phone").val())){
					$("#info3").text("핸드폰 번호를 양식에 맞게 입력해주세요.(ex.010-0000-0000)");
					$("#phone").css("background-color", "#F76159");
					$("#phone").css("color", "white");
					$("#phone").css("text-decoration", "underline");
					return false;
			}else{
					$("#info3").text("");
					$("#phone").css("background-color", "white");
					$("#phone").css("color", "black");
					$("#phone").css("text-decoration", "none");
			}
	}); //내 정보 수정 할 때 
	
	// 로그인을 할 때
	$("#loginForm").on("submit", function(){
		
		if($("#userId").val().length <=  0){
					alert("아이디를 입력해주세요.");
					return false;
		}
		
		if($("#pass").val().length <=  0){
							alert("비밀번호를 입력해주세요.");
							return false;
		}
	
	}); // 로그인을 할 때
	
	
	// 회원가입에 가입 신청 버튼을 누를 때
	$("#joinMemberForm").on("submit", function(){
		
		let idRegExp = /^[A-Za-z0-9]*$/;
		
		if($("#id").val().length <=  0){
			$("#info1").text("아이디는 필수 입력입니다. 아이디를 입력해주세요.");
			$("#id").css("background-color", "#F76159");
			$("#id").css("text-decoration", "none");
			return false;
		}else if(!idRegExp.test($("#id").val())){
			$("#info1").text("아이디에 한글이나 특수문자를 입력할 수 없습니다.");
			$("#id").css("background-color", "#F76159");
			$("#id").css("color", "white");
			$("#id").css("text-decoration", "underline");
			return false;
		}else{
			$("#info1").text("");
			$("#id").css("background-color", "white");
			$("#id").css("color", "black");
			$("#id").css("text-decoration", "none");
		}
		
		if($("#pass").val().length <=  0){
					$("#info1").text("비밀번호를 입력해주세요.");
					$("#pass").css("background-color", "#F76159");
					$("#pass").css("text-decoration", "none");
					return false;
		}else{
			$("#info1").text("");
			$("#pass").css("background-color", "white");
			$("#pass").css("color", "black");
			$("#pass").css("text-decoration", "none");
		}
		
		let nameRegExp = /^[A-Za-z가-힣]*$/;
		
		if($("#name").val().length <=  0){
					$("#info2").text("이름을 입력해주세요.");
					$("#name").css("background-color", "#F76159");
					$("#name").css("text-decoration", "none");
					return false;
		}else if(!nameRegExp.test($("#name").val())){
					$("#info2").text("이름을 한글이나 영문으로 입력해주세요.");
					$("#name").css("background-color", "#F76159");
					$("#name").css("color", "white");
					$("#name").css("text-decoration", "underline");
					return false;
		}else{
					$("#info2").text("");
					$("#name").css("background-color", "white");
					$("#name").css("color", "black");
					$("#name").css("text-decoration", "none");
		}
		
		if($("#nickname").val().length <=  0){
					$("#info2").text("닉네임을 입력해주세요.");
					$("#nickname").css("background-color", "#F76159");
					$("#nickname").css("text-decoration", "none");
					return false;
		}else{
					$("#info2").text("");
					$("#nickname").css("background-color", "white");
					$("#nickname").css("color", "black");
					$("#nickname").css("text-decoration", "none");
		}
		
		if($("#birthday").val().length <=  0){
					$("#info2").text("닉네임을 입력해주세요.");
					$("#birthday").css("background-color", "#F76159");
					$("#birthday").css("text-decoration", "none");
						return false;
		}else{
					$("#info2").text("");
					$("#birthday").css("background-color", "white");
					$("#birthday").css("color", "black");
					$("#birthday").css("text-decoration", "none");
		}
		
		let birthdayRegExp = /^[0-9]*$/;
		
		if($("#birthday").val().length !=  8){
					$("#info2").text("생일을 양식에 맞게 입력해주세요. (ex.20250101)");
					$("#birthday").css("background-color", "#F76159");
					$("#birthday").css("text-decoration", "none");
					return false;
		}else if(!birthdayRegExp.test($("#birthday").val())){
					$("#info2").text("생년월일을 숫자로만 입력해주세요.");
					$("#birthday").css("background-color", "#F76159");
					$("#birthday").css("color", "white");
					$("#birthday").css("text-decoration", "underline");
					return false;
		}else{
					$("#info2").text("");
					$("#birthday").css("background-color", "white");
					$("#birthday").css("color", "black");
					$("#birthday").css("text-decoration", "none");
		}
		
		let phoneRegExp = /^01([0|1|6|7|8|9]?)-?([0-9]{4})-?([0-9]{4})$/;
		
		if($("#phone").val().length <= 0 ){
				$("#info3").text("핸드폰 번호를 선택해주세요");
				$("#phone").css("background-color", "#F76159");
				$("#phone").css("text-decoration", "none");
				return false;
		}else if($("#phone").val().length != 13 ){
				$("#info3").text("핸드폰 번호를 양식에 맞게 입력해주세요.(ex.010-0000-0000)");
				$("#phone").css("background-color", "#F76159");
				$("#phone").css("text-decoration", "none");
				return false;
		}else if(!phoneRegExp.test($("#phone").val())){
				$("#info3").text("핸드폰 번호를 양식에 맞게 입력해주세요.(ex.010-0000-0000)");
				$("#phone").css("background-color", "#F76159");
				$("#phone").css("color", "white");
				$("#phone").css("text-decoration", "underline");
				return false;
		}else{
				$("#info3").text("");
				$("#phone").css("background-color", "white");
				$("#phone").css("color", "black");
				$("#phone").css("text-decoration", "none");
		}
		
	});// 회원가입 버튼 누를 때
	
});