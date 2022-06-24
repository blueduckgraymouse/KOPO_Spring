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
		boolean result = scoreItemService.insertOne(
				request.getParameter("name"),
				request.getParameter("kor"),
				request.getParameter("eng"),
				request.getParameter("mat"));
		
		ServletContext context = getServletContext();
		context.setAttribute("result", result);
		context.setAttribute("name", request.getParameter("name"));
	%>
	
	<script>
		if (${result}) {
			alert("입력 성공");
		} else {
			alert("입력 실패");
		}
		location.href="/ScoreAdmin/selectOne.jsp?name=${name}";
	</script>
</head>

<body>
	
</body>

</html>