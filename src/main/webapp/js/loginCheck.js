/**
 * 
 */
$(function(){
	
	// 회원가입 ID 입력 시
	$("#joinMemberForm").on("focusout","#id",function(){
		
		if($("#id").val().length <=  0){
			$("#info1").text("아이디는 필수 입력입니다. 아이디를 입력해주세요.");
			$("#id").css("background-color", "#F76159");
			$("#id").css("text-decoration", "none");
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
		
	});
	
	
	// 비밀번호 포커스 아웃
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
	});
	
	
	// 이름 포커스 아웃
		$("#joinMemberForm").on("focusout","#name",function(){
			if($("#name").val().length <=  0){
					$("#info2").text("이름을 입력해주세요.");
					$("#name").css("background-color", "#F76159");
					$("#name").css("text-decoration", "none");
					return false;
			}else{
					$("#info2").text("");
					$("#name").css("background-color", "white");
					$("#name").css("color", "black");
					$("#name").css("text-decoration", "none");
				}
		});
		
		
		// 닉네임 포커스 아웃
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
		});
		
		// 생일 포커스 아웃
		$("#joinMemberForm").on("focusout","#birthday",function(){
			if($("#birthday").val().length <=  0){
				$("#info2").text("생일을 입력해주세요.");
				$("#birthday").css("background-color", "#F76159");
				$("#birthday").css("text-decoration", "none");
				return false;
			}else if($("#birthday").val().length !=  8){
				$("#info2").text("생일을 양식에 맞게 입력해주세요. (ex.20250101)");
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
		});
		
		
		// 휴대전화 포커스 아웃
		$("#joinMemberForm").on("focusout","#phone",function(){
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
		}else{
						$("#info3").text("");
						$("#phone").css("background-color", "white");
						$("#phone").css("color", "black");
						$("#phone").css("text-decoration", "none");
		}
		});
	
	
	// 아이디, 비밀번호 찾을 때
	$("#searchIdPassForm").on("submit", function(){
			
			if($("#findThing").val() == "pass"){
				if($("#id").val().length <= 0){
							alert("아이디를 입력해주세요.");
							return false;
				}
			}
			
			
			if($("#name").val().length <= 0){
					alert("이름을 입력해주세요.");
					return false;
			}
			
			if($("#phone").val().length <= 0){
					alert("핸드폰 번호를 입력해주세요.");
					return false;
			}
			
			if($("#phone").val().length != 13 ){
							alert("번호를 잘못 입력 하였습니다.");
							return false;
			}
				
		}); // 아이디, 비밀번호 찾을 때
	
	
	// 회원 탈퇴 버튼을 누를 때
	$("#cancleMembershipForm").on("submit", function(){
			
			if($("#oldPass").val().length <=  0){
				
				alert("이전 비밀번호를 입력해 주세요.");
				
				return false;
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
					alert("이전 비밀번호를 입력해주세요.");
					return false;
			}
			
			if($("#pass").val().length <=  0){
					alert("새로운 비밀번호를 입력해주세요.");
					return false;
			}
			
			if($("#name").val().length <=  0){
							alert("이름을 입력해주세요.");
							return false;
				}
				
			if($("#nickname").val().length <=  0){
									alert("닉네임을 입력해주세요.");
									return false;
			}
			
			if($("#phone").val().length <= 0 ){
						alert("핸드폰 번호를 선택해주세요.");
						return false;
			}
					
			if($("#phone").val().length != 13 ){
						alert("번호를 잘못 입력 하였습니다.");
						return false;
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
		
		if($("#id").val().length <=  0){
			$("#info1").text("아이디는 필수 입력입니다. 아이디를 입력해주세요.");
			$("#id").css("background-color", "#F76159");
			$("#id").css("text-decoration", "none");
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
		
		if($("#name").val().length <=  0){
					$("#info2").text("이름을 입력해주세요.");
					$("#name").css("background-color", "#F76159");
					$("#name").css("text-decoration", "none");
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
		
		if($("#birthday").val().length !=  8){
					$("#info2").text("생일을 양식에 맞게 입력해주세요. (ex.20250101)");
					$("#birthday").css("background-color", "#F76159");
					$("#birthday").css("text-decoration", "none");
					return false;
		}else{
					$("#info2").text("");
					$("#birthday").css("background-color", "white");
					$("#birthday").css("color", "black");
					$("#birthday").css("text-decoration", "none");
		}
		
		if($("#phone").val().length <= 0 ){
				$("#info3").text("핸드폰 번호를 선택해주세요");
				$("#phone").css("background-color", "#F76159");
				$("#phone").css("text-decoration", "none");
				return false;
		}else{
				$("#info3").text("");
				$("#phone").css("background-color", "white");
				$("#phone").css("color", "black");
				$("#phone").css("text-decoration", "none");
		}
		
		if($("#phone").val().length != 13 ){
				$("#info3").text("핸드폰 번호를 양식에 맞게 입력해주세요.(ex.010-0000-0000)");
				$("#phone").css("background-color", "#F76159");
				$("#phone").css("text-decoration", "none");
				return false;
		}else{
				$("#info3").text("");
				$("#phone").css("background-color", "white");
				$("#phone").css("color", "black");
				$("#phone").css("text-decoration", "none");
		}
		
	});// 회원가입 버튼 누를 때
	
});