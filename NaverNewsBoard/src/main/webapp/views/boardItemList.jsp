<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>네이버 뉴스</title>
<!-- <link rel="stylesheet" type="text/css" href="/views/bootstrap/bootstrap.css"> -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<style>
 .container {
 	font-size: 15px;
 }
table td, th {
	text-align : center;
}
a {
	text-decoration : none;
}
a:hover {
	color : navy;
}
</style>
</head>


<body>

	<jsp:include page="boardBanner.jsp" />
	
	<div class="container">
	
		<br><h1 class="success">네이버 뉴스</h1><br>
		
		<table class="table">
			<tr class="table-primary">
				<th width="10%"><b>번호</b></th>
				<th width="50%"><b>제목</b></th>
				<th width="10%"><b>조회수</b></th>
				<th width="30%"><b>등록일</b></th>
			</tr>
			<c:forEach var="boardItem" items="${PageDto.boardItems.content}">
				<tr class="table-active">
					<td>${boardItem.id}</td>
					<td class='align-left'><a
						href='/boardItem/view?id=${boardItem.id}'> ${boardItem.title}
					</a></td>
					<td>${boardItem.viewcnt}</td>
					<td>${boardItem.date}</td>
				</tr>
			</c:forEach>
		</table>
		
		
		<div style="text-align:right">
			<button type="button" class="btn btn-primary" onclick="window.location='/boardItem/insert'">신규</button>
		</div>
		
		
		
		<div class="row">
		    <div class="col">
		    </div>
		    
		    <div class="col">
		    	<c:choose>
			    	<c:when test="${keyword eq null || keyword eq ''}">
						<ul class="pagination">
							<c:set var="pagination" value="${PageDto.pagination}" />
							<c:if test="${pagination.ppPage != 0 && pagination.pPage != 0}">
								<li class="page-item">
									<a class="page-link" href='/boardItem/list?cPage=${pagination.ppPage}'>&lt;&lt;</a>
								</li>
								<li class="page-item">
									<a class="page-link" href='/boardItem/list?cPage=${pagination.pPage}'>&lt;</a>
								</li>
							</c:if>
					
							<c:forEach var="noPage" begin="${pagination.startPage}"	end="${pagination.lastPage}">
								<c:if test="${noPage != 0}">
									<c:choose>
										<c:when test="${noPage == pagination.cPage}">
											<li class="page-item active">
												<a class="page-link" href='/boardItem/list?cPage=${noPage}'>${noPage}</a>
											</li>
										</c:when>
										<c:when test="${noPage != pagination.cPage}">
											<li class="page-item">
												<a  class="page-link"href='/boardItem/list?cPage=${noPage}'>${noPage}</a>
											</li>
										</c:when>
									</c:choose>
								</c:if>
							</c:forEach>
					
							<c:if test="${pagination.nnPage != 0 && pagination.nPage != 0}">
								<li class="page-item">
									<a class="page-link" href='/boardItem/list?cPage=${pagination.nPage}'>&gt;</a>
								</li>
								<li class="page-item">
									<a class="page-link" href='/boardItem/list?cPage=${pagination.nnPage}'>&gt;&gt;</a>
								</li>
							</c:if>
						</ul>
					</c:when>
					
					<c:otherwise>
						<ul class="pagination">
							<c:set var="pagination" value="${PageDto.pagination}" />
							<c:if test="${pagination.ppPage != 0 && pagination.pPage != 0}">
								<li class="page-item">
									<a class="page-link" href='/boardItem/list?cPage=${pagination.ppPage}&keyword=${keyword}'>&lt;&lt;</a>
								</li>
								<li class="page-item">
									<a class="page-link" href='/boardItem/list?cPage=${pagination.pPage}&keyword=${keyword}'>&lt;</a>
								</li>
							</c:if>
					
							<c:forEach var="noPage" begin="${pagination.startPage}"	end="${pagination.lastPage}">
								<c:if test="${noPage != 0}">
									<c:choose>
										<c:when test="${noPage == pagination.cPage}">
											<li class="page-item active">
												<a class="page-link" href='/boardItem/list?cPage=${noPage}&keyword=${keyword}'>${noPage}</a>
											</li>
										</c:when>
										<c:when test="${noPage != pagination.cPage}">
											<li class="page-item">
												<a  class="page-link"href='/boardItem/list?cPage=${noPage}&keyword=${keyword}'>${noPage}</a>
											</li>
										</c:when>
									</c:choose>
								</c:if>
							</c:forEach>
					
							<c:if test="${pagination.nnPage != 0 && pagination.nPage != 0}">
								<li class="page-item">
									<a class="page-link" href='/boardItem/list?cPage=${pagination.nPage}&keyword=${keyword}'>&gt;</a>
								</li>
								<li class="page-item">
									<a class="page-link" href='/boardItem/list?cPage=${pagination.nnPage}&keyword=${keyword}'>&gt;&gt;</a>
								</li>
							</c:if>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
			
		    <div class="col">
		    </div>
		</div>
	</div>
</body>

</html>