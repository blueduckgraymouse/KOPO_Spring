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
</head>

<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<%
		ScoreItemService scoreItemService = new ScoreItemServiceImpl();
		List<ScoreItem> scoreItems = scoreItemService.scoreItemSelectName(request.getParameter("name"));
	%>
	<div align="center">
		<table cellspacing=1 border=1>		
		<tr height=40px  bgcolor=gray>		
			<th width=100px>				
				이름
			</th>
			<th width=100px>				
				학번
			</th>
			<th width=100px>			
				국어 점수
			</th>
			<th width=100px>			
				영어 점수
			</th>
			<th width=100px>				
				수학 점수
			</th>
			</th>
			<th width=100px>				
				수정
			</th>
			</th>
			<th width=100px>				
				삭제
			</th>
		</tr>
		<%
			for(ScoreItem scoreItem : scoreItems) {
		%>
			<tr height=50px>
				<td width=50><p align='center'><%= scoreItem.getName() %></p></td>
				<td width=50><p align='center'><%= scoreItem.getStudentId() %></p></td>
				<td width=50><p align='center'><%= scoreItem.getKor() %></p></td>
				<td width=50><p align='center'><%= scoreItem.getEng() %></p></td>
				<td width=50><p align='center'><%= scoreItem.getMat() %></p></td>
				<%-- <td width=50>
					<p align='center'>
						<button onClick="location.href='/ScoreAdmin/updateForm.jsp?studentId=<%= scoreItem.getStudentId() %>'">수정</button>
					</p>
				</td>
				<td width=50>
					<p align='center'>
						<button onClick="location.href='/ScoreAdmin/deleteOne.jsp?studentId=<%= scoreItem.getStudentId() %>'">삭제</button>
					</p>
				</td> --%>
			</tr>
		<%
			}
		%>
		</table>
	</div>
</body>

</html>