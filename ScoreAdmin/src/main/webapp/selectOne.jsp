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
		<table cellspacing=1 border=1>		<!-- 테이블의 셀간 여백 1, 선 두께 1로 지정 -->
		<tr height=40px  bgcolor=gray>		<!-- 현재 행의 높이를 40px, 배경색을 회색으로 지정 -->
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				이름
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				학번
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				국어 점수
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				영어 점수
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				수학 점수
			</th>
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				수정
			</th>
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				삭제
			</th>
		</tr>
		<%
			for(ScoreItem scoreItem : scoreItems) {			// ResultSet에 저장된 모든 레코드에 대하여 반복
		%>
			<tr height=50px>
				<td width=50><p align='center'><%= scoreItem.getName() %></p></td>
				<td width=50><p align='center'><%= scoreItem.getId() %></p></td>
				<td width=50><p align='center'><%= scoreItem.getKor() %></p></td>
				<td width=50><p align='center'><%= scoreItem.getEng() %></p></td>
				<td width=50><p align='center'><%= scoreItem.getMat() %></p></td>
				<td width=50>
					<p align='center'>
						<button onClick="location.href='/ScoreAdmin/updateForm.jsp?id=<%= scoreItem.getId() %>'">수정</button>
					</p>
				</td>
				<td width=50>
					<p align='center'>
						<button onClick="location.href='/ScoreAdmin/deleteOne.jsp?id=<%= scoreItem.getId() %>'">삭제</button>
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