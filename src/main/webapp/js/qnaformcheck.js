//faq 동작
$(function() {

		// 게시글 쓰기 폼 유효성 검사
	$("#faqwriteForm").on("submit", function() {
		if($("#writer").val().length <= 0) {
			alert("작성자가 입력되지 않았습니다.\n작성자를 입력해주세요");
			$("#writer").focus();			
			return false;
		}
		if($("#title").val().length <= 0) {
			alert("제목이 입력되지 않았습니다.\n제목을 입력해주세요");
			$("#title").focus();
			return false;
		}
		if($("#content").val().length <= 0) {
			alert("내용이 입력되지 않았습니다.\n내용을 입력해주세요");
			$("#content").focus();
			return false;
		}
	});

		// 글수정 폼 요청 처리
	$("#faqdetailUpdate").on("click", function () {
		$("#checkForm").attr("action", "faqupdateForm.mvc");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();	
	});
	
	// 게시글 수정 폼 유효성 검사
	$("#faqupdateForm").on("submit", function() {
		if($("#writer").val().length <= 0) {
			alert("작성자가 입력되지 않았습니다.\n작성자를 입력해주세요");
			$("#writer").focus();			
			return false;
		}
		if($("#title").val().length <= 0) {
			alert("제목이 입력되지 않았습니다.\n제목을 입력해주세요");
			$("#title").focus();
			return false;
		}
		if($("#content").val().length <= 0) {
			alert("내용이 입력되지 않았습니다.\n내용을 입력해주세요");
			$("#content").focus();
			return false;
		}
	});
	
	// 글삭제 요청 처리
	$("#faqdetailDelete").on("click", function () {
		$("#checkForm").attr("action", "faqdeleteProcess.mvc");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();
	});
	
	// 게시글 리스트, 검색 결과 페이지에서 검색 요청 처리
	$("#searchForm").on("submit", function() {
		var keyword = $("#keyword").val();
		if(keyword.length <= 0) {
			alert("검색어가 입력되지 않았습니다.\n검색어를 입력해주세요");
			return false;
		}		
		$(this).attr("method", "post");
		$(this).attr("action", "faqlist.mvc");		
	});	
});


//inquiry 동작
$(function() {
	
	// 게시글 쓰기 폼 유효성 검사
	$("#inquirywriteForm").on("submit", function() {
		if($("#writer").val().length <= 0) {
			alert("작성자가 입력되지 않았습니다.\n작성자를 입력해주세요");
			$("#writer").focus();			
			return false;
		}
		if($("#title").val().length <= 0) {
			alert("제목이 입력되지 않았습니다.\n제목을 입력해주세요");
			$("#title").focus();
			return false;
		}
		if($("#content").val().length <= 0) {
			alert("내용이 입력되지 않았습니다.\n내용을 입력해주세요");
			$("#content").focus();
			return false;
		}
	});
	
	//글수정 폼 요청 처리
	$("#inquirydetailUpdate").on("click", function () {
		$("#checkForm").attr("action", "inquiryupdateForm.mvc");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();	
	});
	
	// 게시글 수정 폼 유효성 검사
	$("#inquiryupdateForm").on("submit", function() {
		if($("#writer").val().length <= 0) {
			alert("작성자가 입력되지 않았습니다.\n작성자를 입력해주세요");
			$("#writer").focus();			
			return false;
		}
		if($("#title").val().length <= 0) {
			alert("제목이 입력되지 않았습니다.\n제목을 입력해주세요");
			$("#title").focus();
			return false;
		}
		if($("#content").val().length <= 0) {
			alert("내용이 입력되지 않았습니다.\n내용을 입력해주세요");
			$("#content").focus();
			return false;
		}
	});
	
	// 글삭제 요청 처리
	$("#inquirydetailDelete").on("click", function () {
		$("#checkForm").attr("action", "inquirydeleteProcess.mvc");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();
	});
	
	// 게시글 리스트, 검색 결과 페이지에서 검색 요청 처리
	$("#searchForm").on("submit", function() {
		var keyword = $("#keyword").val();
		if(keyword.length <= 0) {
			alert("검색어가 입력되지 않았습니다.\n검색어를 입력해주세요");
			return false;
		}		
		$(this).attr("method", "post");
		$(this).attr("action", "inquirylist.mvc");		
	});	
});