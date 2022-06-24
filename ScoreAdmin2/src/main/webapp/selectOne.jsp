<%@page import="kr.ac.ctc.kopo35.Domain.ScoreItem"%>
<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemServiceImpl"%>
<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="./error.jsp" %>					<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>상세 조회</title>
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
		.button {
			background-color: light grey;	
			height : 25px;
			width : 90px;
			cursor : pointer;
		}
		.center {
			text-align: -webkit-center;
		}
	</style>
</head>

<body>
	<%
		ScoreItemService scoreItemService = new ScoreItemServiceImpl();
		List<ScoreItem> scoreItems = scoreItemService.scoreItemSelectName(request.getParameter("name"));
		
		ServletContext context = getServletContext();
		context.setAttribute("scoreItems", scoreItems);
	%>
	
	<%
		
	%>
	
	<div align="center">
		<h1>성적 조회</h1>
		
		<br>
		
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
			<th>				
				수정/삭제
			</th>
		</tr>
			<c:forEach var="scoreItem" items="${scoreItems}">
			<tr>
				<td><p class="center">${scoreItem.name}</p></td>
				<td><p class="center">${scoreItem.studentId}</p></td>
				<td><p class="center">${scoreItem.kor}</p></td>
				<td><p class="center">${scoreItem.eng}</p></td>
				<td><p class="center">${scoreItem.mat}</p></td>
				<td>
					<p class="center">
						<button class="button" onClick="location.href='/ScoreAdmin/updateAndDeleteForm.jsp?studentId=${scoreItem.studentId}'">수정/삭제</button>
					</p>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>

</html>