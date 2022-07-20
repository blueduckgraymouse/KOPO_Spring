<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
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

.table-input {
	width: 600px;
	border-collapse: collapse;
}

.table-input td {
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

		<h1>댓글 수정</h1>

		<form method="post" action="/replyItem/root/update">
			<table class="table-input">
				<tr>
					<td class="title"><b>뉴스 번호</b></td>
					<td width="450px" colspan="3">
						${newsReplyItem.newsBoardItem.id} <input type="hidden" name="id"
						value="${newsReplyItem.id}">
					</td>
				</tr>
				<tr>
					<td class="title"><b>작성자</b></td>
					<td colspan="3">${newsReplyItem.writer}</td>
				</tr>
				<tr>
					<td class="title"><b>일자</b></td>
					<td colspan="3">${newsReplyItem.date}</td>
				</tr>
				<tr>
					<td class="title"><b>내용</b></td>
					<td colspan="3"><textarea name="content" cols="70" row="600">${newsReplyItem.content}</textarea>
					</td>
				</tr>
			</table>

			<br>

			<div class="div-button">
				<input type="submit" value="등록"> <input type="button"
					value="취소" onclick="history.go(-1)">
			</div>
		</form>
	</div>
</body>


</html>