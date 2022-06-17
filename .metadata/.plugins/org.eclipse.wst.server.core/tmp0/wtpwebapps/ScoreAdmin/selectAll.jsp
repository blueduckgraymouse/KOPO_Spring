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
</head>

<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<%
		ScoreItemService scoreItemService = new ScoreItemServiceImpl();
		List<ScoreItem> scoreItems = scoreItemService.scoreItemSelectAll(request.getParameter("cPage")).getScoreItems();
		Pagination pagination = scoreItemService.scoreItemSelectAll(request.getParameter("cPage")).getPagination();
		int test = 11;
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
			</tr>
		<%
			for (ScoreItem scoreItem : scoreItems) {
		%>
			<tr>
				<td><a href="/ScoreAdmin/selectOne.jsp?name=<%= scoreItem.getName() %>"><%= scoreItem.getName() %></a></td>
				<td><%= scoreItem.getStudentId() %></td>
				<td><%= scoreItem.getKor() %></td>
				<td><%= scoreItem.getEng() %></td>
				<td><%= scoreItem.getMat() %></td>
			<tr>
		<%	
			}
		%>
		</table>
		<%=pagination.getTotalRecordCount() %>개, <%= pagination.getcPage() %> / <%= pagination.getTotalPage() %>
		<br>
		<%
			if (pagination.getPpPage() != 0 && pagination.getpPage() != 0) {
				out.println("<a href='/ScoreAdmin/selectAll.jsp?cPage=" + pagination.getPpPage() + "'>&lt;&lt;</a>");
				out.println("<a href='/ScoreAdmin/selectAll.jsp?cPage=" + pagination.getpPage() + "'>&lt;</a>");
			}
			
			for (int i = pagination.getStartPage(); i <= pagination.getLastPage() && i != 0; i++) {
				out.println("<a href='/ScoreAdmin/selectAll.jsp?cPage=" + i + "'>"+ i + "</a>");
			}
		
			if (pagination.getNnPage() != 0 && pagination.getnPage() != 0) {
				out.println("<a href='/ScoreAdmin/selectAll.jsp?cPage=" + pagination.getnPage() + "'>&gt;</a>");
				out.println("<a href='/ScoreAdmin/selectAll.jsp?cPage=" + pagination.getNnPage() + "'>&gt;&gt;</a>");
			}
		%>
		<br>
	</div>
</body>

</html>