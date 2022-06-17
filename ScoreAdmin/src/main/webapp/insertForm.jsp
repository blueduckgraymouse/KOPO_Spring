<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>입력</title>
	<style type="text/css">
		td {
			text-align: center;
		}
	</style>
</head>

<body>
	<jsp:include page="menu.jsp"></jsp:include>
		
	<div align="center">
		<form method="post" action="/ScoreAdmin/insertOne.jsp">
			<table cellspacing=1 border=1>	
			<tr height=40px>	
					<td width=100px>
						이름
					</td>
					<td width=250px>
						<input type=text name="name">
					</td>
				</tr>
				<tr>
					<td>
						학번
					</td>
					<td>
						빈 번호로 순차 부여
					</td>			
				</tr>		
				<tr>
					<td>
						국어
					</td>
					<td>
						<input type=number name="kor">
					</td>			
				</tr>		
				<tr>
					<td>
						영어
					</td>
					<td>
						<input type=number name="eng">
					</td>			
				</tr>		
				<tr>
					<td>
						수학
					</td>
					<td>
						<input type=number name="mat">
					</td>			
				</tr>		
			</table>
			<input type="submit" value="저장">
			<input type="button" onclick="location.href='/ScoreAdmin/selectAll.jsp'" value="취소">
		</form>
	</div>
</body>

</html>