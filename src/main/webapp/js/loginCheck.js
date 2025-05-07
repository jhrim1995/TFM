/**
 * 
 */
$(function(){
	
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
		
		alert($("#gender1").val() + " / " + $("#gender2").val());
		
	});
	
});