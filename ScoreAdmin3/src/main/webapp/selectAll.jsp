<%@page import="kr.ac.ctc.kopo35.Dto.Pagination"%>
<%@page import="kr.ac.ctc.kopo35.Dto.Pagination"%>
<%@page import="kr.ac.ctc.kopo35.Domain.ScoreItem"%>
<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemServiceImpl"%>
<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>전제 조회</title>
	<style>
		table {
			border-collapse: collapse;
		}
		tr, td, th {
			border : 1px solid black;
			width : 100px;
			
		}
		th {
			background-color : grey;
			height : 40px;
		}
		a {							
			color : grey;			
			text-decoration : none;	
		}
		a:hover {					
			color : blue;			
			cursor-point: pointer;
 		}
		.info {
		 	width : 500px;
		 	text-align: -webkit-right;
		}
		.center {
			text-align: -webkit-center;
		}
		
	</style>
</head>

<body>
	<%
		ScoreItemService scoreItemService = new ScoreItemServiceImpl();
	
		List<ScoreItem> scoreItems = scoreItemService.selectAll(request.getParameter("cPage"));
		Pagination pagination = scoreItemService.getPagination(request.getParameter("cPage"));
		
		ServletContext context = getServletContext();
		context.setAttribute("scoreItems", scoreItems);
		context.setAttribute("pagination", pagination);
	%>
	
	<div align="center">
		<h1>성적 조회</h1>
		
		<br>
		<div class="info">
			<c:out value="${pagination.totalRecordCount}"/>개, <c:out value="${pagination.cPage}"/> / <c:out value="${pagination.totalPage}"/>
		</div>
		<table>			
			<tr>	
				<th>	
					이름
				</th>
				<th>			
					학번
				</th>
				<th>			
					국어 점수
				</th>
				<th>			
					영어 점수
				</th>
				<th>			
					수학 점수
				</th>			
			</tr>
			<c:forEach var="scoreItem" items="${scoreItems}">
				<tr>
					<td><p class="center"><a href="/ScoreAdmin/selectOne.jsp?name=${scoreItem.name}">${scoreItem.name}</a></td>
					<td><p class="center">${scoreItem.studentId}</td>
					<td><p class="center">${scoreItem.kor}</td>
					<td><p class="center">${scoreItem.eng}</td>
					<td><p class="center">${scoreItem.mat}</td>
				<tr>
			</c:forEach>
		</table>
		
		<br>
		
		<c:if test="${pagination.ppPage != 0 && pagination.pPage != 0}">
			<a href='/ScoreAdmin/selectAll.jsp?cPage=${pagination.ppPage}'>&lt;&lt;</a>
			<a href='/ScoreAdmin/selectAll.jsp?cPage=${pagination.pPage}'>&lt;</a>
		</c:if>
		
		<c:forEach var="noPage" begin="${pagination.startPage}" end="${pagination.lastPage}">
			<c:if test="${noPage != 0}">
				<c:choose>
					<c:when test="${noPage == pagination.cPage}">
						<b><a style='text-decoration: underline;' href='/ScoreAdmin/selectAll.jsp?cPage=${noPage}'>${noPage}</a></b>
					</c:when>
					<c:when test="${noPage != pagination.cPage}">
						<a href='/ScoreAdmin/selectAll.jsp?cPage=${noPage}'>${noPage}</a>
					</c:when>
				</c:choose>
			</c:if>
		</c:forEach>
		
		<c:if test="${pagination.nnPage != 0 && pagination.nPage != 0}">
			<a href='/ScoreAdmin/selectAll.jsp?cPage=${pagination.nPage}'>&gt;</a>
			<a href='/ScoreAdmin/selectAll.jsp?cPage=${pagination.nnPage}'>&gt;&gt;</a>
		</c:if>
	</div>
</body>

</html>