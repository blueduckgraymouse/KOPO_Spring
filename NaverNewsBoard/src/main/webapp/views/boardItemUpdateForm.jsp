<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>뉴스 수정</title>
<style>
.container {
	max-width: 600px;
	margin: 0 auto;
}

table {
	text-align: center;
}

td {
	border: 1px solid black;
}

.table-update {
	width: 600px;
	border-collapse: collapse;
}

.table-update td {
	height: 30px;
	border: 1px solid grey;
}

td.title {
	border-right: 3px double grey;
	background-color: lightgrey;
}

.align-left {
	text-align: right;
}

a {
	color: black;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

textarea {
	width: 450px;
	height: 250px;
}

div.div-button {
	text-align: right;
}
</style>
</head>


<body>
	<div class="container">
		<jsp:include page="boardBanner.jsp" />

		<h1>뉴스 수정</h1>

		<form method="post" action="/boardItem/update">
			<table class="table-update">
				<tr>
					<td class="title"><b>번호</b></td>
					<td width="450px" colspan="3">${boardItem.id} <input
						type="hidden" name="id" value="${boardItem.id}">
					</td>
				</tr>
				<tr>
					<td class="title"><b>제목</b></td>
					<td colspan="3"><input type="text" name="title" size="62"
						maxlength="70" value="${boardItem.title}"></td>
				</tr>
				<tr>
					<td class="title"><b>등록일</b></td>
					<td colspan="3">${boardItem.date}</td>
				</tr>
				<tr>
					<td class="title"><b>내용</b></td>
					<td colspan="3"><textarea name="content" cols="70" row="600">${boardItem.content}</textarea>
					</td>
				</tr>
			</table>

			<br>

			<div class="div-button">
				<input type="submit" value="저장"> <input type="button"
					value="삭제"
					onclick="location.href='/boardItem/delete?id=${boardItem.id}'">
				<input type="button" value="취소"
					onclick="location.href='/boardItem/list'">
			</div>
		</form>
	</div>
</body>


</html>