<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="./error.jsp" %>					<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>입력</title>
	<style>
		table, tr, td {
			border: 1px solid black;	
			border-cellpadding : 0;		
			border-collapse: collapse;	
		}
		td {
			text-align : center;		
			height : 35px;				
		}
		
		.button {
			background-color: light gray;	
			height : 30px;
			width : 100px;
			cursor : pointer;
		}
		input::-webkit-inner-spin-button {
			appearance : none;			
		}
	</style>
</head>

<body>
	<div align="center">
		<h1>성적 입력</h1>
		
		<br>
		
		<form  id="insert_form" method="post" action="/ScoreAdmin/insertOne.jsp">
			<table>	
			<tr height=40px>	
					<td width=100px>
						이름
					</td>
					<td width=250px>
						<input type=text name="name" id="name">
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
						<input type=number name="kor" id="kor">
					</td>			
				</tr>		
				<tr>
					<td>
						영어
					</td>
					<td>
						<input type=number name="eng" id="eng">
					</td>			
				</tr>		
				<tr>
					<td>
						수학
					</td>
					<td>
						<input type=number name="mat" id="mat">
					</td>			
				</tr>		
			</table>
			<br><br>
			<input type=submit class="button" onsubmit="checkValidation(this.form);" value="저장" />	
			<!-- <button class="button" onclick="return checkValidation(this.form);">저장</button> -->
						<!-- [이슈] submit으로 하면 유효성이 안되고 button으로 하면 index.html에서 나눈 target을 lost --> 
			<input type="button" class="button" onclick="location.href='/ScoreAdmin/selectAll.jsp'" value="취소">
		</form>
	</div>
	
	<script>
		// 유효성 검사 함수
		function checkValidation(form) {
			// 이름 길이 1~20글자 제한
			name = form.name.value;
			if (name.length > 20 || name.length < 1) {
				window.alert("이름은 1~20글자로 입력 가능합니다.");
				form.name.focus();
				return false;
			}
			// 이름에 들어갈 수 있는 문자 한글, 영서 숫자로 제한
			const regex = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+$/;
			if (!regex.test(name)) {
				window.alert("이름에는 한글, 영어, 숫자 입력이 가능합니다.");
				form.name.focus();
				return false;
			}
			// 국어점수 필수 입력
			score_k = form.kor.value;
			if (score_k.length < 1) {
				window.alert("국어점수는 필수 입력입니다.");
				form.kor.focus();
				return false;
			}
			// 국어점수 0~100점 입력 제한
			if (score_k > 100 || score_k < 0) {
				window.alert("국어 점수는 0~100점까지 입력이 가능합니다.");
				form.kor.focus();
				return false;
			}
			// 영어점수 필수 입력
			score_e = form.eng.value;
			if (score_e.length < 1) {
				window.alert("영어 점수는 필수 입력입니다.");
				form.eng.focus();
				return false;
			}
			// 영어점수 0~100점 입력 제한			
			if (score_e > 100 || score_e < 0) {
				window.alert("영어 점수는 0~100점까지 입력이 가능합니다.");
				form.eng.focus();
				return false;
			}
			// 수학점수 필수 입력
			score_m = form.mat.value;
			if (score_m.length < 1) {
				window.alert("수학 점수는 필수 입력입니다.");
				form.mat.focus();
				return false;
			}	
			// 수학점수 0~100점 입력 제한			
			if (score_m > 100 || score_m < 0) {
				window.alert("수학 점수는 0~100점까지 입력이 가능합니다.");
				form.mat.focus();
				return false;
			}
		}
	</script>
</body>

</html>