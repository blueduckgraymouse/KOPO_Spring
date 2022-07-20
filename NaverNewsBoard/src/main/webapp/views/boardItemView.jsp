<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>뉴스 보기</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
	crossorigin="anonymous"></script>
	
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>


<style>

.align-right {
	text-align: right;
}
p {
	text-indent: 0;
}
</style>
</head>


<body style="min-height: 1200px;">
	<jsp:include page="boardBanner.jsp" />

	<div class="container">

		<br><br><br>

		<div class="article">
			<h1>${boardItem.title}</h1>
			<br> <br>
			<h6>${boardItem.writer} 기자 | 날짜 ${boardItem.date} | 조회수 ${boardItem.viewcnt}</h6>

			<br> <br>
			<p style="white-space: pre-wrap;">		<!-- pre태그를 통해 줄바꿈(<br>)을 적용, 스타일 옵션을 통해서 너비 초과에 따른 줄바꿈 적용 -->
				${boardItem.content}
			</p>
		</div>

		<br>

		<div class="button" style="text-align: right">
			<button type="button" class="btn btn-primary" onclick="location.href='/boardItem/list'">목록</button>
			<button type="button" class="btn btn-primary" onclick="location.href='/boardItem/update?id=${boardItem.id}'">수정</button>
			<button type="button" class="btn btn-primary" onclick="location.href='/boardItem/delete?id=${boardItem.id}'">삭제</button>
		</div>

		<br><br><br><br>

		<div class="reply" id="reply">
			<div class="list-group">
			
				<h3 class="display">댓글</h3>	
				<div class="list-group-item flex-column align-items-start">
					<form method="post" action="/replyItem/root/insert"> <!-- 댓글 입력 -->
						<input class="form-control" type="text" name="writer" placeholder="작성자" style="width:30%"> <br>
						<textarea name="content" style="width:100%;resize: none;" rows="6" placeholder="내용"></textarea>
						<input type="hidden" name="boardId" value="${boardItem.id}">
						<div style="text-align: right"><input type="submit" class="btn btn-primary" value="등록"></div>
					</form>
				</div>
			
			
				<c:forEach var="replyItem" items="${boardItem.newsReplyItems}" varStatus="rootStatus">
					<c:if test="${replyItem.id eq replyItem.rootReplyItem.id}">
					
						<div class="list-group-item list-group-item-action flex-column align-items-start">
							<div class="d-flex w-100 justify-content-between">
								<span class="mb-1"> ${replyItem.writer} | ${replyItem.date} |
									<a data-toggle="collapse" href="#subReplyInsertFrom${rootStatus.index}">답글</a> |
									<a data-toggle="collapse" href="#replyUpdateForm${rootStatus.index}">수정</a> |
									<a href='/replyItem/root/delete?id=${replyItem.id}'>삭제</a>
								</span>
							</div>
							<pre class="mb-1">${replyItem.content}</pre>
							
							<!-- 댓글 수정 -->
							<div id="replyUpdateForm${rootStatus.index}" class="collapse">
								<form method="post" action="/replyItem/root/update"> 
									<textarea name="content" style="width:100%;resize: none;" rows="6" placeholder="댓글 내용">${replyItem.content}</textarea>
									<input type="hidden" name="id" value="${replyItem.id}">
									<div style="text-align: right"><input type="submit" class="btn btn-primary" value="등록"></div>
								</form>
							</div>
							
							<div id="subReplyInsertFrom${rootStatus.index}" class="collapse">
								<div class="row">
									<div class="col-1"></div>
									<div class="col-11">
									 	<!-- 답글 등록 -->
										<form method="post" action="/replyItem/sub/insert"> 
											<input class="form-control" type="text" name="writer" placeholder="작성자" style="width:30%"> <br>
											<textarea name="content" style="width:100%;resize: none;" rows="6" placeholder="답글 내용"></textarea>
											<input type="hidden" name="rootReplyId" value="${replyItem.id}">
											<div style="text-align: right"><input type="submit" class="btn btn-primary" value="등록"></div>
										</form>
									</div>
								</div>
							</div>
							

							<c:forEach var="subReplyItem" items="${replyItem.subReplyItems}" varStatus="subStatus">
								<div class="row">
									<div class="col-1"></div>
									<div class="col-11">
									
										<c:if test="${subReplyItem.id ne replyItem.id && subReplyItem.rootReplyItem.id eq replyItem.id}">
											<div class="d-flex w-100 justify-content-between">
												<span class="mb-1"> ${subReplyItem.writer} | ${subReplyItem.date} | 
													<a data-toggle="collapse" href="#subReplyUpdateForm${rootStatus.index}-${subStatus.index}">수정</a> | 
													<a href="/replyItem/sub/delete?id=${subReplyItem.id}">삭제</a>
												</span>
											</div>
											<pre class="mb-1">${subReplyItem.content}</pre>
											
											<!-- 답글 수정 -->
											<div id="subReplyUpdateForm${rootStatus.index}-${subStatus.index}" class="collapse">
												<form method="post" action="/replyItem/sub/update">
													<textarea name="content" style="width:100%;resize: none;" rows="6" placeholder="댓글 내용">${subReplyItem.content}</textarea>
													<input type="hidden" name="id" value="${subReplyItem.id}">
													<div style="text-align: right"><input type="submit" class="btn btn-primary" value="등록"></div>
												</form>
											</div>
										</c:if>
										
									</div>
								</div>
							</c:forEach>
							
						</div>
						
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</body>


</html>