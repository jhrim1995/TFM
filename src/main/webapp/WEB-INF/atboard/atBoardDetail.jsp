<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="js/atReply.js" ></script>
<!-- content -->
<div class="row my-5" id="global-content" >
	<div class="col">
		<form name="checkForm" id="checkForm" >
			<input type="hidden" name="at_no" id="at_no" value="${b.at_no}" />
			<input type="hidden" name="pass" id="rPass"/>
			<input type="hidden" name="pageNum" value="${ pageNum }" />
			<c:if test="${ searchOption }">
			<input type="hidden" name="type" value="${ type }" />
			<input type="hidden" name="keyword" value="${ keyword }" />
			</c:if>
		</form>
		<div class="row text-center" >
			<div class="col">
				<h2 class="fs-3 fw-bold" >기사 상세보기</h2>
			</div>
		</div>
		<div class="row my-3" >
			<div class="col" >
				<table class="table table-bordered" >
					<tbody>
						<tr>
							<th class="table-secondary" >제목</th>
							<td colspan="3" >${ b.title }</td>
						</tr>
						<tr>
							<th>글쓴이</th>
							<td>${ b.m_id }</td>
							<th>작성일</th>
							<td><fmt:formatDate value="${ b.w_date }"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td>
								<div class="col-sm-8">
									<input class="form-control" type="password" name="pass" id="pass" />
								</div>
							</td>
							<th>조회수</th>
							<td>${ b.views }</td>
						</tr>
						<tr>
							<td colspan="4" >
								<pre>${ b.content }</pre>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="row my-3" >
		<div class="col text-center" >
			<input class="btn btn-warning" type="button" id="atDetailUpdate" value="수정하기"/>&nbsp;&nbsp;
			<input class="btn btn-danger" type="button" id="atDetailDelete" value="삭제하기"/>&nbsp;&nbsp;
			<!-- 검색 X -->
			<c:if test="${not searchOption}">
			<input class="btn btn-success" type="button" value="목록보기" onclick="location.href='atBoardList.mvc?pageNum=${ pageNum }'" />
			</c:if>
			<!-- 검색 O -->
			<c:if test="${searchOption}">
			<input class="btn btn-success" type="button" value="목록보기" onclick="location.href='atBoardList.mvc?pageNum=${ pageNum }&type=${type}&keyword=${keyword}'" />
			</c:if>
		</div>
	</div>
	<!-- 추천 -->
	<div class="row my-5">
		<div class="col border p-3" >
			<div id="recm" class="text-end" >
				<span id="cm" class="btnCommend text-info" style="cursor: pointer;" >
				<img src="images/recommend.png" alt="추천하기"/>&nbsp;추천
				<span class="recm" >(${ b.recm })</span>
				</span> | 
				<span id="atReplyWrite" class="text-info" style="cursor: pointer;" >
					<i class="bi bi-chat-left-text" style="color:orange;" ></i> 댓글쓰기
				</span>
			</div>
		</div>
	</div>
	<!-- 댓글 헤더 -->
	<div class="row" id="atReplyTitle" >
		<div class="col p-2 text-center bg-dark text-white" >
			<h3 class="fs-4" >댓글 리스트</h3>
		</div>
	</div>
	<!-- 댓글 O -->
	<c:if test="${ not empty rList }">
	<div class="row mb-3" >
		<div class="col" id="rList" >
			<c:forEach var="reply" items="${rList }" >
			<div class="replyRow row border border-top-0" >
				<div class="col" >
					<div class="row bg-light p-2" >
						<div class="col-4" >
							<span>${ r.m_id }</span>
						</div>
						<div class="col-8 text-end" >
							<span class="me-3" >
								<fmt:formatDate value="${ r.c_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
							</span>
							<button class="atModifyReply btn btn-outline-primary btn-sm" data-no="${r.c_no }" >
							<i class="bi bi-pencil">수정</i></button>
							<button class="atDeleteReply btn btn-outline-danger btn-sm" data-no="${r.c_no }" >
							<i class="bi bi-trash3">삭제</i></button>
							<button class="atDeleteReply btn btn-outline-warning btn-sm" data-no="${r.c_no }" >
							<i class="bi bi-exclamation-triangle">신고</i></button>
						</div>
					</div>
					<div class="row" >
						<div class="col p-3" >
							<pre>${ r.c_con }</pre>
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
	</c:if>
	<!-- 댓글 X -->
	<c:if test="${ empty rList }">
	<div class="row mb-5" id="rList" >
		<div class="col text-center borar p-5" >
			<div>댓글이 없습니다.</div>
		</div>
	</div>
	</c:if>
	<!-- 댓글 폼 -->
	<div class="row my-3 d-none" id="atReplyForm" >
		<div class="col" >
			<form name="atReplyWriteForm" id="atReplyWriteForm" >
				<input type="hidden" name="at_no" value="${ b.at_no }" />
				<input type="hidden" name="m_id" value="${ sessionScope.id }" />
				<div class="row bg-light my-3 p-3 border" >
					<div class="col" >
						<div class="row" >
							<div class="col text-center" >
								<span>악의적인 댓글은 예고없이 삭제될 수 있으며 별도의 제제가 이루어질 수 있습니다.</span>
							</div>
						</div>
						<div class="row my-3" >
							<div class="col-md-10" >
								<textarea name="c_con" id="c_con" class="form-control" rows="4" ></textarea>
							</div>
							<div class="com-md">
								<input type="submit" value="댓글쓰기" class="btn btn-success h-100 w-100" id="atReplyWriteButton" />
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
