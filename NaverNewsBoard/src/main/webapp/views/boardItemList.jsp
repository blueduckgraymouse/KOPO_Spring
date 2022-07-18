<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page errorPage="./gongji_error.jsp" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>게시물 전체 조회</title>
	<style>
	  .container {
	    max-width: 600px;
	    margin:0 auto;
	  }
	  table {
	    text-align: center;
	  }
	  .align-left {
	    text-align: left;
	  }
	  .table-view {
	    width: 600px;
	    border: 1px solid black;
	    border-collapse: collapse;
	  }
	  .table-view td {
	    height: 30px;
	    border: 1px solid grey;
	  }
	  .title {
	    background-color: lightgrey;
	    border-bottom: 3px double black;
	  }
	  a {
	    color: black;
	    text-decoration: none;
	  }
	  a:hover {
	    text-decoration: underline;
	  }
	  div.div-button {
	    text-align: right;
	  }
	  .pagination {
	    margin:0 auto;
	  }
	</style>
</head>


<body>
	<div class="container">
    <jsp:include page="boardBanner.jsp"/>

    <h1>공지사항</h1>

    <table class="table-view">
      <tr class="title">
        <td width="50px"><b>번호</b></td>
        <td width="500px"><b>제목</b></td>
        <td width="100px"><b>조회수</b></td>
        <td width="100px"><b>등록일</b></td>
      </tr>
	  <c:forEach var="boardItem" items="${PageDto.boardItems.content}">
	  <tr>
	  	<td>${boardItem.id}</td>
	  	<td class='align-left'>
	  	  <a href='/boardItem/view?id=${boardItem.id}'>
			${boardItem.title}
	  	  </a>
	  	</td>
	  	<td>${boardItem.viewcnt}</td>
	  	<td>${boardItem.date}</td>
	  </tr>
	  </c:forEach>
    </table>

    <br>
    
    <div class="pagination">
      <c:set var="pagination" value="${PageDto.pagination}"/>
	  <c:if test="${pagination.ppPage != 0 && pagination.pPage != 0}">
			<a href='/boardItem/list?cPage=${pagination.ppPage}'>&lt;&lt;</a>
			<a href='/boardItem/list?cPage=${pagination.pPage}'>&lt;</a>
		</c:if>
		
		<c:forEach var="noPage" begin="${pagination.startPage}" end="${pagination.lastPage}">
			<c:if test="${noPage != 0}">
				<c:choose>
					<c:when test="${noPage == pagination.cPage}">
						<b><a style='text-decoration: underline;' href='/boardItem/list?cPage=${noPage}'>${noPage}</a></b>
					</c:when>
					<c:when test="${noPage != pagination.cPage}">
						<a href='/boardItem/list?cPage=${noPage}'>${noPage}</a>
					</c:when>
				</c:choose>
			</c:if>
		</c:forEach>
		
		<c:if test="${pagination.nnPage != 0 && pagination.nPage != 0}">
			<a href='/boardItem/list?cPage=${pagination.nPage}'>&gt;</a>
			<a href='/boardItem/list?cPage=${pagination.nnPage}'>&gt;&gt;</a>
		</c:if>
    </div>
    
    <br>
    
    <div class="div-button">
      <input type="button" value="신규" onclick="window.location='/boardItem/insert'">
    </div>
  </div>
</body>


</html>