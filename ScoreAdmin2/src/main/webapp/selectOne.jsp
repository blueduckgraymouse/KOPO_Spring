<%@page import="kr.ac.ctc.kopo35.Domain.ScoreItem"%>
<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemServiceImpl"%>
<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<%
			for(ScoreItem scoreItem : scoreItems) {
		%>
			<tr>
				<td><p class="center"><%= scoreItem.getName() %></p></td>
				<td><p class="center"><%= scoreItem.getStudentId() %></p></td>
				<td><p class="center"><%= scoreItem.getKor() %></p></td>
				<td><p class="center"><%= scoreItem.getEng() %></p></td>
				<td><p class="center"><%= scoreItem.getMat() %></p></td>
				<td>
					<p class="center">
						<button class="button" onClick="location.href='/ScoreAdmin/updateAndDeleteForm.jsp?studentId=<%= scoreItem.getStudentId() %>'">수정/삭제</button>
					</p>
				</td>
			</tr>
		<%
			}
		%>
		</table>
	</div>
</body>

</html>