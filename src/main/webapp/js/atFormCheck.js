$(function() {
	
	$("#searchForm").on("submit", function() {
		var keyword = $("#keyword").val();
		if(keyword.length <= 0) {
			alert("검색어를 입력해 주세요.")
			return false;
		}
		$(this).attr("method", "post")
		$(this).attr("action", "atBoardList.mvc")
	});
	
	$("#atDetailDelete").on("click", function() {
		var pass = $("#pass").val();
		if(pass.length <4){
			alert("게시글을 삭제하려면 비밀번호를 입력해주세요.")
			return false;
		}
		$("#rPass").val(pass);
		$("#checkForm").attr("action", "atDeleteProcess.mvc");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();
	});
	
	$("#atUpdateForm").on("submit", function() {
		if($("#m_id").val().length < 3 ) {
			alert("글쓴이는 3자 이상 입력해주세요.")
			$("#m_id").focus();
			return false;
		}
		if($("#title").val().length <5 ) {
			alert("제목은 5자 이상 입력해 주세요.")
			$("#title").focus();
			return false;
		}
		if($("#pass").val().length < 4 ) {
			alert("비밀번호는 4자 이상 입력해주세요.")
			$("#pass").focus();
			return false;
		}
		if($("#content").val().length < 10 ) {
			alert("내용은 10자 이상 입력해 주세요.")
			$("#content").focus();
			return false;
		}
	})
	
	$("#atDetailUpdate").on("click", function() {
		var pass = $("#pass").val();
		if(pass.length <4){
			alert("게시글을 수정하려면 비밀번호를 입력해주세요.")
			return false;
		}
		$("#rPass").val(pass);
		$("#checkForm").attr("action", "atUpdateForm.mvc");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();
	});
	
	$("#atWriteForm").on("submit", function() {
		if($("#m_id").val().length < 3 ) {
			alert("글쓴이는 3자 이상 입력해주세요.")
			$("#m_id").focus();
			return false;
		}
		if($("#title").val().length <5 ) {
			alert("제목은 5자 이상 입력해 주세요.")
			$("#title").focus();
			return false;
		}
		if($("#pass").val().length < 4 ) {
			alert("비밀번호는 4자 이상 입력해주세요.")
			$("#pass").focus();
			return false;
		}
		if($("#content").val().length < 10 ) {
			alert("내용은 10자 이상 입력해 주세요.")
			$("#content").focus();
			return false;
		}
	});
	
});
