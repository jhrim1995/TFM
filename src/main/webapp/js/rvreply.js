/**
 * auth : jini
 */
$(function(){ // DOM이 준비되면
	
	//댓글 삭제 버튼이 클릭될 때 
	$(document).on("click", ".rvDeleteReply", function(e){
		console.log("rvDeleteReply Clicked.");
		let c_No = $(this).attr("data-no")
		let r_No = $("#rvReplyForm input[name=r_no]").val();
								
			let params = "replyC_no=" + c_No + "&r_No=" + r_No;
			console.log(params);
			
			let writer = $(this).parent().prev().find("span").text();
			
			// 현재 폼을 백업 - 댓글 리스트 부분은 삭제되기 때문에 
			let $rvReplyForm = $("#rvReplyForm").slideUp(300);
			
			let result = confirm(writer + " 님이 작성한 " + c_No + "번 댓글을 삭제할까?");
			
			if(result) {
				
			$.ajax({
				url: "rvReplyDelete.ajax",
				data: params,
				dataType: "json",
				type: "post",
				"success": function(resData){
					// resData = jQuery 응답 데이터를 처리해서 넣어주는 데이터
					// resData => 댓글 리스트
					console.log(resData);
					$("#rvReplyList").empty();
					$.each(resData, function(i, v){
						//console.log(i, v)
						// 작성일 데이터 처리- May 8, 2025, 3:19:18 PM => 2025-05-08 15:19:18
						let date = new Date(v.c_Date);
						let strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10)
							? "0" + (date.getMonth() + 1) : date.getMonth() + 1) + "-" 
							+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
							+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + " : "
							+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + " : "
							+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
						
						//하나의 댓글 요소를 출력
						let result = 
						'<div class="replyRow row border border-top-0">'
											+		'<div class="col">'
											+	  	'<div class="row bg-light p-2">'
														+	'<div class="col-4">'
														+`		<span>${v.m_Id }</span>`
														+	'</div>'
														+	'<div class="col-8 text-end p-1">'
															+	'<span class="me-3">'
												+		`         ${strDate}`
															+	'</span>'
														+		'<button class="rvModifyReply btn btn-outline-success btn-sm" '
																				+	'data-no="'+v.c_no +'"><i class="bi bi-file-text"> 수정</i></button>'
														+		'<button class="rvDeleteReply btn btn-outline-warning btn-sm" '
																				+	`data-no="${v.c_no }"><i class="bi bi-trash-fill"> 삭제</i></button>`
														+		'<button class="btn btn-outline-danger btn-sm" '
																				+		`onclick="rvReportReply(' ${ v.c_no} ')">`
											+									'<i class="bi bi-telephone-outbound-fill"> 신고</i></button>'
													+		'</div>'
													+	'</div>'
												+		'<div class="row">'
														+	'<div class="col p-3">'
															+  '<pre>' + v.c_Con + '</pre>'
														+	'</div>'
													+	'</div>'
											+		'</div>'
										+		'</div>'
						
										$("#rvReplyList").append(result) ;
										$("#rvReplyList").removeClass("text-center");
										$("#rvReplyList").removeClass("p-5");
										
						
					});    //end $.each(resData, function(i, v){});
					
					$("#rvReplyContent").val("");
					$("#global-content > div.col").append($rvReplyForm)
					
				},  //
				error: function(xhr){
					console.log(xhr)
				}
			});
		}
		// 아니요 선택 - 아무동작도 안함
					
	});
	
	
	
	
	
	//댓글 수정 폼이 전송될 때 - 이벤트 위임 방식으로 이벤트 리스너 적용
	$(document).on("submit", "#rvReplyUpdateForm", function(){
			// 폼이 서브밋 될 때 - 댓글이 입력되었는지 검사 - 유효성 검사
			if($("#rvReplyContent").val().length < 2){
				alert("댓글은 2자 이상 입력...");
				return false;
				//e.proventDefault();
				//e.stopPropagation();
			}
			// 데이터를 준비해서 서버로 보내야 함
			
			let params = $(this).serialize() + "&replyC_No=" + $(this).attr("data-no");
			console.log(params);
			
			// 현재 폼을 백업 - 댓글 리스트 부분은 삭제되기 때문에 
			let $rvReplyForm = $("#rvReplyForm").slideUp(300);
			
			/*setTimeout(function(){*/
			$.ajax({
				url: "rvReplyUpdate.ajax",
				data: params,
				dataType: "json",
				type: "post",
				"success": function(resData){
					// resData = jQuery 응답 데이터를 처리해서 넣어주는 데이터
					// resData => 댓글 리스트
					console.log(resData);
					$("#rvReplyList").empty();
					$.each(resData, function(i, v){
						//console.log(i, v)
						// 작성일 데이터 처리- May 8, 2025, 3:19:18 PM => 2025-05-08 15:19:18
						let date = new Date(v.c_Date);
						let strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10)
							? "0" + (date.getMonth() + 1) : date.getMonth() + 1) + "-" 
							+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
							+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + " : "
							+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + " : "
							+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
						
						//하나의 댓글 요소를 출력
						let result = 
						'<div class="replyRow row border border-top-0">'
											+		'<div class="col">'
											+	  	'<div class="row bg-light p-2">'
														+	'<div class="col-4">'
														+`		<span>${v.replyM_id }</span>`
														+	'</div>'
														+	'<div class="col-8 text-end">'
															+	'<span class="me-3">'
												+		`         ${strDate}`
															+	'</span>'
														+		'<button class="rvModifyReply btn btn-outline-success btn-sm" '
																				+	'data-no="'+v.c_No +'"><i class="bi bi-file-text"> 수정</i></button>'
														+		'<button class="rvDeleteReply btn btn-outline-warning btn-sm" '
																				+	`data-no="${v.c_No }"><i class="bi bi-trash-fill"> 삭제</i></button>`
														+		'<button class="btn btn-outline-danger btn-sm" '
																				+		`onclick="rvReportReply(' ${ v.c_No} ')">`
											+									'<i class="bi bi-telephone-outbound-fill"> 신고</i></button>'
													+		'</div>'
													+	'</div>'
												+		'<div class="row">'
														+	'<div class="col p-3">'
															+  '<pre>' + v.c_Con + '</pre>'
														+	'</div>'
													+	'</div>'
											+		'</div>'
										+		'</div>'
						
										$("#rvReplyList").append(result) ;
										$("#rvReplyList").removeClass("text-center");
										$("#rvReplyList").removeClass("p-5");
										
						
					});    //end $.each(resData, function(i, v){});
					
					$("#rvReplyContent").val("");
					$("#global-content > div.col").append($rvReplyForm)
					
				},  //
				error: function(xhr){
					console.log(xhr)
				}
			});
			/*}, 300);*/
			return false;
					
		});   //end	$("#replyUpdateForm").on("submit", function(){};
	
	
	
			
	// 댓글 쓰기 폼이 전송될 때
	$(document).on("submit", "#rvReplyWriteForm", function(){
		// 폼이 서브밋 될 때 - 댓글이 입력되었는지 검사 - 유효성 검사
		if($("#rvReplyContent").val().length < 2){
			alert("댓글은 2자 이상 입력...");
			return false;
			//e.proventDefault();
			//e.stopPropagation();
		}
		// 데이터를 준비해서 서버로 보내야 함
		let params = $(this).serialize();
		console.log(params);
		
		$.ajax({
			url: "rvReplyWrite.ajax",
			data: params,
			dataType: "json",
			type: "post",
			"success": function(resData){
				// resData = jQuery 응답 데이터를 처리해서 넣어주는 데이터
				// resData => 댓글 리스트
				console.log(resData);
				$("#rvReplyList").empty();
				$.each(resData, function(i, v){
					//console.log(i, v)
					// 작성일 데이터 처리- May 8, 2025, 3:19:18 PM => 2025-05-08 15:19:18
					let date = new Date(v.c_Date);
					let strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10)
						? "0" + (date.getMonth() + 1) : date.getMonth() + 1) + "-" 
						+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
						+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + " : "
						+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + " : "
						+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
					
					//하나의 댓글 요소를 출력
					let result = 
					'<div class="replyRow row border border-top-0">'
										+		'<div class="col">'
										+	  	'<div class="row bg-light p-2">'
													+	'<div class="col-4">'
													+`		<span>${v.replyM_id }</span>`
													+	'</div>'
													+	'<div class="col-8 text-end">'
														+	'<span class="me-3">'
											+		`         ${strDate}`
														+	'</span>'
													+		'<button class="rvModifyReply btn btn-outline-success btn-sm" '
																			+	'data-no="'+v.c_No +'"><i class="bi bi-file-text"> 수정</i></button>'
													+		'<button class="rvDeleteReply btn btn-outline-warning btn-sm" '
																			+	`data-no="${v.c_No }"><i class="bi bi-trash-fill"> 삭제</i></button>`
													+		'<button class="btn btn-outline-danger btn-sm" '
																			+		`onclick="rvReportReply(' ${ v.c_No} ')">`
										+									'<i class="bi bi-telephone-outbound-fill"> 신고</i></button>'
												+		'</div>'
												+	'</div>'
											+		'<div class="row">'
													+	'<div class="col p-3">'
														+  '<pre>' + v.replyC_con + '</pre>'
													+	'</div>'
												+	'</div>'
										+		'</div>'
									+		'</div>'
					
									$("#rvReplyList").append(result) ;
									/*$("#replyList").removeClass("text-center");
									$("#replyList").removeClass("p-5");*/
									
					
				});    //end $.each(resData, function(i, v){});
				
				$("#rvReplyForm").slideUp(300).add("#rvReplyContent").val("");
				
			},
			error: function(xhr){
				console.log(xhr)
			}
		});
		
		return false;
				
	});   //end	$("#replyWriteForm").on("submit", function(){};
	
	
	// 수정 버튼 클릭 이벤트 처리
	$("#rvReplyList").on("click", ".rvModifyReply" , function(e){
	/*let*/	$replyRow = $(this).parents(".replyRow");
		
		// 폼이 보이는지 체크 - display
	  // console.log("display : " + $("#replyForm").css("display") != 'none');
	  if($("#rvReplyForm").is(":visible")){ // 보이는 상태
			
			let $next = $replyRow.next();
			if($next.is("#rvReplyForm")){
				return;
			}
			$("#rvReplyForm").slideUp(300);
			
			setTimeout(function(){
				$("#rvReplyForm").insertAfter($replyRow)	.slideDown(300);
			},300);
		
			
		} else {
			// 현재 폼이 안보일때
					$("#rvReplyForm").insertAfter($replyRow).
								css("display", "none").removeClass("d-none").slideDown(300);
		}
		
		$("#rvReplyForm").find("form").attr({"id": "rvReplyUpdateForm","data-no": $(this).attr("data-no")});
		$("#rvReplyWriteButton").val("댓글수정");
		
		let reply = $(this).parent().parent().next().find("pre").text();
		$("#rvReplyContent").val(reply);
		
	});
	
	
	// 댓글 쓰기 클릭 이벤트
	$("#rvReplyWrite").on("click", function(){
		// 댓글 쓰기 폼(#replyForm)을 선택하고 댓글 리스트 타이틀 앞쪽으로 이동한 후(앞쪽에 추가) 화면에 보이게한다.
		// document.querySelector("#replyForm")

		// 폼이 보이는지 체크 - display
		if($("#rvReplyForm").is(":visible")){ // 보이는 상태
			
			let $prev = $("#rvReplyTitle").prev();
			
			if($prev.is("#rvReplyForm")){
				return;
			}
			
			$("#rvReplyForm").slideUp(300);
			
			setTimeout(function(){
				$("#rvReplyForm").insertBefore("#rvReplyTitle").slideDown(300);
			}, 300);
			
		} else {
			// 현재 폼이 안보일때
			$("#rvReplyForm").insertBefore("#rvReplyTitle").
				css("display", "none").removeClass("d-none").slideDown(300);
		}
		
		$("#rvReplyForm").find("form").attr("id", "rvReplyWriteForm").removeAttr("data-no"); 
		$("#rvReplyWriteButton").val("댓글쓰기");
		$("#rvReplyContent").val("");

		
	});
	
	// 추천 Ajax
	$(".btnCommend").click(function(){
		
	//	let com = $(this).attr("m_id");
		//console.log("com : " + com);
		
		$.ajax({
			url : "recommend.ajax",
			type : "post",
			// recommend=thank&no=20
		//	data : {recommend: com, r_no: $("#r_no").val()},
			data : {r_no: $("#r_no").val()},
			dataType: "json",
			success: function(resData){
				//console.log(resData);
			//	let msg = com == 'commend' ? "추천이" : " 땡큐가" ;
				$("#commend > .recommend").text("(" + resData.recommend + ")");
				
				let msg = "추천이";
				alert(msg + " 반영되었습니다.");						
			},
			error: function(xhr, status, error){
				console.log("error : " , xhr, " - ", xhr.statusText, " - ", status, " - ", error);
			}
		});
		
	});
	
	
});

function reportReply(replyNo){
	alert(replyNo + "번 글을 신고 ㄱ?");
}