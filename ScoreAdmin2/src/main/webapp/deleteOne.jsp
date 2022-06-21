<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemServiceImpl"%>
<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="./error.jsp" %>					<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>수정 실행</title>
	<%
		request.setCharacterEncoding("UTF-8");
	
		ScoreItemService scoreItemService = new ScoreItemServiceImpl();
		boolean result = scoreItemService.scoreItemDeleteOne(Integer.parseInt(request.getParameter("studentId")));
		
		ServletContext context = getServletContext();
		context.setAttribute("result", result);
	%>
	<script>
		if (${result} == true) {
			alert("삭제 성공");
		} else {
			alert("삭제 실패");
		}
		location.href="/ScoreAdmin/selectAll.jsp";
	</script>
</head>

<body>
	
</body>

</html>