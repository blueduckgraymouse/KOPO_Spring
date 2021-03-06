<%@page import="kr.ac.ctc.kopo35.Domain.ScoreItem"%>
<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemServiceImpl"%>
<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>수정</title>
</head>

<body>
	<jsp:include page="menu.jsp"></jsp:include>
	
	<%
		ScoreItemService scoreItemService = new ScoreItemServiceImpl();
		ScoreItem scoreItem = scoreItemService.scoreItemSelectId(Integer.parseInt(request.getParameter("studentId")));
	%>
	<div align="center">
		<form method="post" action="/ScoreAdmin/updateOne.jsp">
			<table cellspacing=1 border=1>		
				<tr height=40px>	
					<td width=100px>
						이름
					</td>
					<td width=250px>
						<input type=text name="name" value="<%= scoreItem.getName() %>">
					</td>
				</tr>
				<tr>
					<td>
						학번
					</td>
					<td>
						<input type=number name="studentId" value="<%= scoreItem.getStudentId() %>" readonly>
					</td>			
				</tr>		
				<tr>
					<td>
						국어
					</td>
					<td>
						<input type=number name="kor" value="<%= scoreItem.getKor() %>">
					</td>			
				</tr>		
				<tr>
					<td>
						영어
					</td>
					<td>
						<input type=number name="eng" value="<%= scoreItem.getEng() %>">
					</td>			
				</tr>		
				<tr>
					<td>
						수학
					</td>
					<td>
						<input type=number name="mat" value="<%= scoreItem.getMat() %>">
					</td>			
				</tr>		
			</table>
			<input type="submit" value="저장">
			<input type="button" onclick="history.go(-1)" value="취소">
		</form>
	</div>
</body>

</html>