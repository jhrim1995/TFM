$(function() {

//게시글 쓰기 폼 유효성 검사
$("#rvWriteForm").on("submit",function(){
	
	if($("#title").val().length <= 0){
		alert("제목을 입력하세연~");
		$("#title").focus();
		return false;
	}
	if($("#content").val().length <= 0){
		alert("내용을 입력하세연~");
		$("#content").focus();
		return false;
	}
	if($("#area").val().length <= 0){
		alert("지역을 입력하세연~");
		$("area").focus();
		return false;
	}
	
});






//게시글 상세 보기에서 게시글 수정 폼 요청 처리 
/*$("#rvDetailUpdate").on("click", function(){
	
	var m_id = $("#m_id").val();
	if(m_id.length <= 0){
		alert("게시글을 수정하려면 아이디를 입력 ㄱ");
		return false;
	}
	$("#rM_id").val(m_id);
	$("#rvCheckForm").attr("action","rvUpdateForm.mvc");
	$("#rvCheckForm").attr("method","post");
	$("#rvCheckForm").submit();
});*/
//게시글 상세 보기에서 게시글 수정 폼 요청 처리 
$("#rvDetailUpdate").on("click",function(){
	
	var loginId = $("#login_m_id").val();
	var writerId = $("#writer_m_id").val();
	
	if(!loginId){
		alert("로그인이 필요합니다~");
		return false;
	}
	if(loginId !== writerId){
		alert("본인만 수정 가능~");
		return false;
	}

	$("#rvCheckForm").attr("action","rvUpdateForm.mvc").submit();
	
});






//게시글 수정 폼 유효성 검사
$("#rvUpdateForm").on("submit", function(){
	if($("#m_id").val().length <= 0){
		alert("id 입력하세요~");
		$("#m_id").focus();
		return false;
	}
	if($("#title").val().length <= 0){
			alert("제목 입력하세요~");
			$("#title").focus();
			return false;
		}
	if($("#content").val().length <= 0){
		alert("내용 입력하세요~");
		$("#content").focus();
		return false;
			}
	if($("#area").val().length <= 0){
		alert("지역 입력하세요~");
		$("#area").focus();
		return false;
				}
});


// 게시글 상세보기에서 게시글 삭제 요청 처리
$("#rvDetailDelete").on("click", function(){
	
	var m_id = $("#m_id").val();
	if(m_id.length <= 0){
		alert("삭제하려면 ID 입력");
		return false;
	}
	$("#rM_id").val(m_id);
	$("#rvCheckForm").attr("action","rvDeleteProcess.mvc");
	$("#rvCheckForm").attr("method","post");
	$("#rvCheckForm").submit();
});



// 게시글 리스트, 검색 결과 페이지에서 검색 요청 처리
$("#searchForm").on("submit", function(){
	var keyword = $("#keyword").val();
	if(keyword.length <= 0){
		alert("검색어 입력 ㄱㄱ");
		return false;
	}
	$(this).attr("method", "post");
	$(this).attr("action", "rvBoardList.mvc");
});

















});