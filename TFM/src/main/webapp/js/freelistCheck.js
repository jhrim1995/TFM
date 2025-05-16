/**
 * 
 */
$(function() { // DOM이 준비되면
	$("#searchForm").on("submit",function(){
		let keyword = $("#keyword").val();
		if(keyword == 0){
			alert("검색어를 1자 이상 입력하세요");
			return false;
		}
		
		$(this).attr("method","post");
		$(this).attr("action","freeList.mvc");
	});
	
	$("#commend").click(function(e) {
			
			let com = $(this).attr("id");
			console.log("com : " + com);
			
			$.ajax({
				url : "recommend.ajax",
				type : "post",
				data : {recommend : com , no: $("#no").val()},
				dataType:"json",
				success: function(resData){
				//console.log(resData);
				$("#commend").val("추천 ( " + resData.recommend + " )");	
				},
				error: function(xhr, status, error){
					console.log("error : ",xhr , "-", xhr.statusText, "-",status,"-",error);
				}
			});
			
		});
	
	
	
	
	// 게시 글 상세보기에서 삭제하기 버튼이 클릭되면	
	$("#listDelete").on("click", function() {
		
		$("#checkForm").attr("action", "deleteFreeList.mvc");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();
	});
	
	
	// 게시 글 상세보기에서 수정하기 버튼이 클릭되면	
	$("#listupdate").on("click", function() {
		
		$("#checkForm").attr("action", "updateFreeListForm.mvc");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();
	});
	
	// 게시 글쓰기 폼 유효성 검사
	// 문서에서 폼 선택하고 그 폼에 서브밋 이벤트 핸들러 등록
	$("#writeForm").on("submit", function(e) {
		//e.preventDefault();
		
		if($("#writer").val().length < 3) {
			alert("작성자는 3자 이상 입력해야 합니다.");
			$("#writer").focus();
			return false;
		}

		if($("#pass").val().length < 3) {
			alert("비밀번호는 3자 이상 입력해야 합니다.");
			$("#pass").focus();
			return false;
		}		
				
		if($("#title").val().length < 3) {
			alert("제목은 3자 이상 입력해야 합니다.");
			$("#title").focus();
			return false;
		}		

		if($("#content").val().length < 5) {
			alert("게시 글 내용은 5자 이상 입력해야 합니다.");
			$("#content").focus();
			return false;
		}
		
		
	});
	
	// 게시 글쓰기 폼 유효성 검사
	// 문서에서 폼 선택하고 그 폼에 서브밋 이벤트 핸들러 등록
	$("#updateForm").on("submit", function(e) {
		//e.preventDefault();
		
		if($("#writer").val().length < 3) {
			alert("작성자는 3자 이상 입력해야 합니다.");
			$("#writer").focus();
			return false;
		}

		if($("#pass").val().length < 3) {
			alert("비밀번호는 3자 이상 입력해야 합니다.");
			$("#pass").focus();
			return false;
		}		
				
		if($("#title").val().length < 3) {
			alert("제목은 3자 이상 입력해야 합니다.");
			$("#title").focus();
			return false;
		}		

		if($("#content").val().length < 5) {
			alert("게시 글 내용은 5자 이상 입력해야 합니다.");
			$("#content").focus();
			return false;
		}
		
		
	});
	
	
	/*
	const writeForm = document.querySelector("#writeForm");
	writeForm.addEventListener("submit", (e) => {
		const writer = document.querySelector("#writer").value;
		if(writer == "") {
			alert("작성자를 입력해 주세요");
			e.preventDefault();
			return;
		}
	});
	*/
	
});

