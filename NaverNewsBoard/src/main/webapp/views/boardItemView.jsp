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
	  .table-reply {
	  	width: 600px;
	  }
	  .table-reply td {
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
	        <td colspan="2" class="title"><b>작성자</b></td>
	        <td colspan="3">${boardItem.writer}</td>
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
	    <div class="align-right"><input type="button" value="댓글 달기" onclick="location.href='/replyItem/root/insert?id=${boardItem.id}'"></div>
	    <table class="table-reply">
			<c:forEach var="replyItem" items="${boardItem.newsReplyItems}">
				<c:if test="${replyItem.id eq replyItem.rootReplyItem.id}">
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						${replyItem.writer} | ${replyItem.date} | 
						<a href="/replyItem/sub/insert?id=${replyItem.id}">답글</a> |
						<a href="/replyItem/root/update?id=${replyItem.id}">수정</a> |
						<a href="/replyItem/root/delete?id=${replyItem.id}">삭제</a>
					</td>
				</tr>
				<tr>
 					<td colspan="2"><div class="align-left">${replyItem.content}</div></td>
				</tr>
				
  				<c:forEach var="subReplyItem" items="${replyItem.subReplyItems}">
 					<c:if test="${subReplyItem.id ne replyItem.id && subReplyItem.rootReplyItem.id eq replyItem.id}">
						<tr>
							<td width=5%>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td>
								${subReplyItem.writer} | 
								${subReplyItem.date} |
								<a href="/replyItem/sub/update?id=${subReplyItem.id}">수정</a> |
								<a href="/replyItem/sub/delete?id=${subReplyItem.id}">삭제</a>
			      			</td>
						</tr>
						<tr>
							<td  width=5%>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td><div class="align-left">${subReplyItem.content}</div></td>
						</tr>
					</c:if>
				</c:forEach>
				
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				</c:if>
			</c:forEach>
	    </table>
	    
	</div>
</body>


</html>