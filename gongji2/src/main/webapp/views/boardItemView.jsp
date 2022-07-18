<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>게시물 상세 조회</title>
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
	    text-align: right;
	  }
	  a {
	    color: black;
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
	</style>
</head>


<body>
	<div class="container">
	    <jsp:include page="boardBanner.jsp"/>
	
	    <h1>공지사항</h1>
	
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
	      <tr>
	        <td colspan="2" class="title"><b>원글</b></td>
	        <td colspan="3">${boardItem.rootid}</td>
	      </tr>
	      <tr>
	        <td colspan="2" class="title"><b>댓글 수준</b></td>
	        <td>${boardItem.relevel}</td>
	        <td class="title" width="150px"><b>댓글 내 순서</b></td>
	        <td>${boardItem.recnt}</td>
	      </tr>
	    </table>
	
	    <br>
	    
	    <div class="div-button">
	      <input type="button" value="목록" onclick="location.href='/boardItem/list'">
	      <input type="button" value="수정" onclick="location.href='/boardItem/update?id=${boardItem.id}'">
	      <input type="button" value="삭제" onclick="location.href='/boardItem/delete?id=${boardItem.id}'">
	      <input type="button" value="댓글" onclick="location.href='/boardItem/insertReply?id=${boardItem.id}'">
	    </div>
	</div>
</body>


</html>