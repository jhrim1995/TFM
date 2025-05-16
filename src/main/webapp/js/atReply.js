/**
 * 
 */
$(document).ready(function() {
	
	$(document).on("click", ".atDeleteReply", function() {
		let no = $(this).attr("data-no");
		let m_id = $(this).parent().prev().find("span").text();
		let at_no = $("#atReplyForm input[name=at_no]").val();
		let params = "c_no="+no+"&at_no="+at_no;
		console.log(params);
		
		$atReplyForm = $("#atReplyForm").slideUp(300);
		let result = confirm(m_id+"님이 작성한"+no+"번 댓글을 삭제하시겠습니까?");
		
		if(result) {
			$.ajax({
				url: "atReplyDelete.ajax",
				data: params,
				type: "post",
				dataType: "json",
				"success": function(resData, status, xhr) {
					console.log(resData);
					
					$("#rList").empty();
					$.each(resData, function(i, v){
						var date = new Date(v.regDate);
						var strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10)
											? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-"
											+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
											+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":"
											+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":"
											+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
						var result =
						'<div class="replyRow row border border-top-0 " >'+
										'<div class="col" >'+
											'<div class="row bg-light p-2" >'+
												'<div class="col-4" >'+
													`<span>${ v.m_id }</span>`+
												'</div>'+
												'<div class="col-8 text-end p-1" >'+
													'<span class="me-3" >'+
														`${ strDate }`+
													'</span>'+
													`<button class="atModifyReply btn btn-outline-primary btn-sm" data-no="${v.c_no }" >`+
													'<i class="bi bi-pencil">수정</i></button>'+
													`<button class="atDeleteReply btn btn-outline-danger btn-sm" data-no="${v.c_no }" >`+
													'<i class="bi bi-trash3">삭제</i></button>'+
													`<button class="atDeleteReply btn btn-outline-warning btn-sm" onclick="atReportReply('${v.c_no }')" >`+
													'<i class="bi bi-exclamation-triangle">신고</i></button>'+
												'</div>'+
											'</div>'+
											'<div class="row" >'+
												'<div class="col p-2" >'+
													`<pre>${ v.c_con }</pre>`+
												'</div>'+
											'</div>'+
										'</div>'+
									'</div>'
						$("#atReplyList").append(result);
						
					}); // end each
					
					$("#atReplyForm").val("");
					$atReplyForm.css("display", "none");
					$("#global-content > div.col").append($atReplyForm);
				},
				error: function(xhr, status, error) {
					alert("ajax 실패 : "+status+"-"+xhr.status)
				}
			});
		}
		return false;
	});
	
	$(document).on("click", ".atModifyReply", function() {
		var $replyRow = $(this).parents(".replyRow");

		if($("#atReplyForm").is(":visible")) {
			var $next = $replyRow.next();
			if(! $next.is("#atReplyForm")) {
			$("#atReplyForm").slideUp(300);
			}
			setTimeout(function(){
			$("#atReplyForm").insertAfter($replyRow).slideDown(300);
			}, 300);
			} else {
			$("#atReplyForm").removeClass("d-none")
			.css("display", "none").insertAfter($replyRow).slideDown(300);
			}
			$("#atReplyForm").find("form")
			.attr({"id":"atReplyUpdateForm", "data-no":$(this).attr("data-no")});
			$("#atReplyWriteButton").val("댓글수정");
			var r = $(this).parent().parent().next().find("pre").text();
			$("#c_con").val($.trim(r));
			
	});
	
	$(document).on("submit", "#atReplyUpdateForm", function() {
		if($("#c_con").val().length < 5) {
			alert("댓글은 5자 이상 입력해주세요.");
			return false;
		}
		$atReplyForm = $("#atReplyForm").slideUp(300);
		
		var params = $(this).serialize()+"&c_no=" +$(this).attr("data-no"); 
		console.log(params);
		
		$.ajax({
			url: "atReplyUpdate.ajax",
			data: params,
			type: "post",
			dataType: "json",
			"success": function(resData) {
				console.log(resData);
				
				$("#rList").empty();
				$.each(resData, function(i, v){
					var date = new Date(v.regDate);
					var strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10)
										? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-"
										+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
										+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":"
										+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":"
										+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
					var result =
					'<div class="replyRow row border border-top-0 " >'+
															'<div class="col" >'+
																'<div class="row bg-light p-2" >'+
																	'<div class="col-4" >'+
																		`<span>${ v.m_id }</span>`+
																	'</div>'+
																	'<div class="col-8 text-end p-1" >'+
																		'<span class="me-3" >'+
																			`${ strDate }`+
																		'</span>'+
																		`<button class="atModifyReply btn btn-outline-primary btn-sm" data-no="${v.c_no }" >`+
																		'<i class="bi bi-pencil">수정</i></button>'+
																		`<button class="atDeleteReply btn btn-outline-danger btn-sm" data-no="${v.c_no }" >`+
																		'<i class="bi bi-trash3">삭제</i></button>'+
																		`<button class="atDeleteReply btn btn-outline-warning btn-sm" onclick="atReportReply('${v.c_no }')" >`+
																		'<i class="bi bi-exclamation-triangle">신고</i></button>'+
																	'</div>'+
																'</div>'+
																'<div class="row" >'+
																	'<div class="col p-2" >'+
																		`<pre>${ v.c_con }</pre>`+
																	'</div>'+
																'</div>'+
															'</div>'+
														'</div>'
					
					$("#atReplyList").append(result);
					
				}); // end each
				
				$("#atReplyForm").val("");
				$atReplyForm.css("display", "none");
				$("#global-content > div.col").append($atReplyForm);
			},
			error: function(xhr, status, error) {
				alert("ajax 실패 : "+status+"-"+xhr.status)
			}
		});
		return false;
	});
	
	$(document).on("submit", "#atReplyWriteForm", function(e) {
		if($("#c_con").val().length < 5) {
			alert("댓글은 5자 이상 입력해주세요.");
			return false;
		}
		var params = $(this).serialize();
		console.log(params);
		
		$.ajax({
			url: "atReplyWrite.ajax",
			data: params,
			type: "post",
			dataType: "json",
			"success": function(resData) {
				console.log(resData);
				
				$("#rList").empty();
				$.each(resData, function(i, v){
					var date = new Date(v.regDate);
					var strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10)
										? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-"
										+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " "
										+ (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":"
										+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":"
										+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
					var result =
					'<div class="replyRow row border border-top-0 " >'+
															'<div class="col" >'+
																'<div class="row bg-light p-2" >'+
																	'<div class="col-4" >'+
																		`<span>${ v.m_id }</span>`+
																	'</div>'+
																	'<div class="col-8 text-end p-1" >'+
																		'<span class="me-3" >'+
																			`${ strDate }`+
																		'</span>'+
																		`<button class="atModifyReply btn btn-outline-primary btn-sm" data-no="${v.c_no }" >`+
																		'<i class="bi bi-pencil">수정</i></button>'+
																		`<button class="atDeleteReply btn btn-outline-danger btn-sm" data-no="${v.c_no }" >`+
																		'<i class="bi bi-trash3">삭제</i></button>'+
																		`<button class="atDeleteReply btn btn-outline-warning btn-sm" onclick="atReportReply('${v.c_no }')" >`+
																		'<i class="bi bi-exclamation-triangle">신고</i></button>'+
																	'</div>'+
																'</div>'+
																'<div class="row" >'+
																	'<div class="col p-2" >'+
																		`<pre>${ v.c_con }</pre>`+
																	'</div>'+
																'</div>'+
															'</div>'+
														'</div>'
					
					$("#atReplyList").append(result);
					$("#atReplyList").removeClass("text-center");
					$("#atReplyList").removeClass("p-5");
					
				}); // end each
				$("#atReplyForm").slideUp(300).add("#c_con").val("");
			},
			"error": function(xhr, status) {
				console.log("error : " + status);
			}
		});
		return false;
	});
	
	$("#atReplyWrite").on("click", function() {
		console.log($("#atReplyForm").css("display"));
		console.log($("#atReplyForm").is("visible"));
		if($("#atReplyForm").is(":visible")) {
			var $prev = $("#atReplyTitle").prev();
			if(! $prev.is("#atReplyForm")) {
				$("#atReplyForm").slideUp(300);
			}
			setTimeout(function() {
				$("#atReplyForm").insertBefore("#atReplyTitle").slideDown(300);
			}, 300);
		} else {
			$("#atReplyForm").removeClass("d-none").css("display", "none").insertBefore("#atReplyTitle").slideDown(300);
		}
		$("#atReplyForm").find("form").attr("id", "atReplyWriteForm").removeAttr("data-no");
		$("#c_con").val("");
		$("#atReplyWriteButton").val("댓글쓰기");
	});
	
	$(".btnCommend").click(function () {
		var com = $(this).attr("id");
		console.log("com : " + com);
		$.ajax({
			url: "atRecm.ajax",
			type: "post",
			data: {recm:com, at_no : $("#at_no").val()},
			dataType: "json",
			success: function(data) {
				alert("추천이 반영되었습니다.");
				$("#cm > .recm").text("("+data.recm+")");
			},
			error: function(xhr, status, error) {
				alert("error : "+xhr.statusText + ","+status+","+error);
			}
		})
	});
});
function atReportReply(elemId) {
	var result = confirm("이 댓글을 신고하시겠습니까?");
	if(result == true) {
		alert("report - " + result);
	}
}