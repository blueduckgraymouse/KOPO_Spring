<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemServiceImpl"%>
<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>reset</title>
	<%
		request.setCharacterEncoding("UTF-8");
	
		ScoreItemService scoreItemService = new ScoreItemServiceImpl();
		boolean result = scoreItemService.scoreItemsReset();

	%>
	<script>
		if (<%= result %>) {
			alert("초기화 성공");
		} else {
			alert("초기화 실패");
		}
		location.href="/ScoreAdmin/selectAll.jsp";
	</script>

<body>
	
</body>

</html>