<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemServiceImpl"%>
<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>수정 실행</title>
	<%
		request.setCharacterEncoding("UTF-8");
	
		ScoreItemService scoreItemService = new ScoreItemServiceImpl();
		boolean result = scoreItemService.scoreItemInsertOne(
				request.getParameter("name"),
				request.getParameter("kor"),
				request.getParameter("eng"),
				request.getParameter("mat"));
	%>
	<script>
		if (<%= result %> == true) {
			alert("입력 성공");
		} else {
			alert("입력 실패");
		}
		location.href="/ScoreAdmin/selectOne.jsp?name=<%= request.getParameter("name") %>";
	</script>
</head>

<body>
	
</body>

</html>