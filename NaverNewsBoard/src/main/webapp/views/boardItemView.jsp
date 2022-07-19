<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>뉴스 보기</title>
	<style>
	  .container {
	    max-width: 600px;
	    margin:0 auto;
	  }
	  table {
	    text-align: center;
	  }
	  td {
	    border: 1px solid black;
	  }
	  .table-view {
	    width: 600px;
	    border-collapse: collapse;
	  }
	  .table-view td {
	    height: 30px;
	    border: 1px solid grey;
	  }
	  td.title {
	    border-right: 3px double grey;
	    background-color: lightgrey;
	  }
	  .align-left {
	    text-align: left;
	  }
	  a {
	    color: grey;
	    text-decoration: none;
	  }
	  a:hover {
	    text-decoration: underline;
	  }
	  #content {
	    height: 200px;
	  }
	  div.div-button {
	    text-align: right;
	  }
	  textarea {
	    width: 98%;
	    height: 10em;
	    border: none;
	    resize: none;
	  }
	  .table-reply td {
	    width: 600px;
	  	border : none;
	  	font-size: 70%;
	  }
	  .border-top {
	  	border-top : 10px double grey;
	  }
	  .display {
	  	display: inline; 
	  }
	  .align-right {
	    text-align: right;
	  }
	  
	</style>
</head>


<body>
	<div class="container">
	    <jsp:include page="boardBanner.jsp"/>
	
	    <h1>뉴스</h1>
	
	    <table class="table-view">
	      <tr>
	        <td colspan="2" class="title"><b>번호</b></td>
	        <td colspan="3" width="450px">
	         ${boardItem.id}
	        </td>
	      </tr>
	      <tr>
	        <td colspan="2" class="title"><b>제목</b></td>
	        <td colspan="3">
	          ${boardItem.title}
	        </td>
	      </tr>
	      <tr>
	        <td colspan="2" class="title"><b>등록일자</b></td>
	        <td colspan="3">${boardItem.date}</td>
	      </tr>
	      <tr>
	        <td colspan="2" class="title"><b>조회수</b></td>
	        <td colspan="3">${boardItem.viewcnt}</td>
	      </tr>
	      <tr>
	        <td colspan="2" class="title"><b>내용</b></td>
	        <td colspan="3" id="content"><textarea>${boardItem.content}</textarea>
	        </td>
	      </tr>
	    </table>
	    <br>
	    <div class="div-button">
	      <input type="button" value="목록" onclick="location.href='/boardItem/list'">
	      <input type="button" value="수정" onclick="location.href='/boardItem/update?id=${boardItem.id}'">
	      <input type="button" value="삭제" onclick="location.href='/boardItem/delete?id=${boardItem.id}'">
	    </div>
	
	    <br>
	    
	    <h3 class="display">댓글</h3>
	    <div class="align-right"><input type="button" value="댓글 달기" onclick="location.href='/replyItem/insert?id=${boardItem.id}'"></div>
	    <table class="table-reply">
			<c:forEach var="replyItem" items="${boardItem.newsReplyItems}">
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${replyItem.writer}</td>
					<td>작성일시</td>
					<td>${replyItem.date}</td>
					<td>
						<a href="/replyItem/update?id=${replyItem.id}">수정</a>&nbsp;
						<a href="/replyItem/delete?id=${replyItem.id}">삭제</a>
	      			</td>
				</tr>
				<tr>
					<td colspan="5"><div class="align-left">${replyItem.content}</div></td>
				</tr>
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
			</c:forEach>
	    </table>
	    
	</div>
</body>


</html>