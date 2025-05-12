/**
 * 
 */
$(function(){
	
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
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		if($("#pass").val().length <=  0){
					alert("비밀번호를 입력해주세요.");
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
		
		if($("#birthday").val().length <=  0){
					alert("생일을 입력해주세요.");
					return false;
		}
		
		if($("#birthday").val().length !=  8){
					alert("생일을 양식에 맞게 입력해주세요.");
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
		
	});// 회원가입 버튼 누를 때
	
});