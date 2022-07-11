<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align=center >
		<h1>입력</h1>
		
		<form id="insert_form" method="get" action="insert.jsp" enctype="multipart/form-data">
			<table>
				<tr>
					<td width=100px>
						id
					</td>
					<td width=250px>
						자동 부여
					</td>			
				</tr>
				<tr>
					<td>
						파일
					</td>
					<td>
						<!-- <input type=file name="fileLocation" onchange="readRUL(this, 'preview')"> -->
						<input type=file name="fileLocation">
					</td>			
				</tr>		
<!-- 				<tr>
					<td>
						미리보기
					</td>
					<td>
						<img id="preview" src="">
					</td>			
				</tr> -->	
			</table>
			<br><br>
			<input type="submit" class="button" value="저장" />
		</form>	
		
		<script>
/* 		function readURL(input,preview)
		{
			if(input.files && input.files[0])
			{
				var reader = new FileReader();
				reader.onload = function(e)
				{
					$("#"+preview).attr("src", e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		} */
		</script>
</body>
</html>