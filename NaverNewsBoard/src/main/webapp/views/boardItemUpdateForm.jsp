<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>뉴스 수정</title>
<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
	crossorigin="anonymous"></script>
	
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<style>
</style>
</head>


<body>
	<jsp:include page="boardBanner.jsp" />

	<div class="container">

		<br><h1 class="success">뉴스 수정</h1><br>
		
		<form method="post" action="/boardItem/update">
			<table class="table">
				<tr class="table-light">
					<th><b>제목</b></th>
					<td>
						<input type="text" name="title" style="width:100%" value="${boardItem.title}">
						<input type="hidden" name="id" value="${boardItem.id}">
					</td>
				</tr>
				<tr class="table-light">
					<th><b>작성자</b></th>
					<td><input type="text" name="writer" style="width:100%" value="${boardItem.writer}">
					</td>
				</tr>
				<tr class="table-light">
					<th><b>내용</b></th>
					<td>
						<textarea id="summernote" name="content">${boardItem.content}</textarea>
					</td>
				</tr>
			</table>

			<br>
			
			<div class="button" style="text-align: right">
				<input type="submit" class="btn btn-primary" value="저장">
				<input type="button" class="btn btn-primary" onclick="location.href='/boardItem/list'" value="취소">
			</div>
		</form>
	</div>
	
	
	
	<script>
		$(document).ready(function() {
			$('#summernote').summernote({
				height: 300,                 		// 에디터 높이
				minHeight: null,             		// 최소 높이
				maxHeight: null,             		// 최대 높이
				focus: true,                 		// 에디터 로딩후 포커스를 맞출지 여부
				lang: "ko-KR",						// 한글 설정
				placeholder: '기사 내용을 작성하세요.',	//placeholder 설정
				callbacks: {	
 				onImageUpload : function(files) {	// 파일 업로드시 실행되는 함수
						uploadSummernoteImageFile(files[0], this);
					}
				} 
			});
		});
			
		/* 이미지 파일 업로드 */
			function uploadSummernoteImageFile(file, el) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "/boardItem/uploadSummernoteImageFile",
				contentType : false,
				processData : false,
				enctype : 'multipart/form-data;charset="UTF-8',
				success : function(data) {
					/* $(editor).summernote('insertImage', data);		// 백단에서 저장 후 저장경로인 url을 반환해와야 한다. */
					$(el).summernote('insertImage', data, function($image) {
						$image.css('width', "25%");
					});
				}
			});
		}
	</script>
</body>


</html>