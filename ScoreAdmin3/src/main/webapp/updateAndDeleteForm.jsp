<%@page import="kr.ac.ctc.kopo35.Domain.ScoreItem"%>
<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemServiceImpl"%>
<%@page import="kr.ac.ctc.kopo35.Service.ScoreItemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="./error.jsp" %>					<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
	<title>수정</title>
	
	<style>
		table, tr, td {
			border: 1px solid black;
			border-collapse: collapse;
		}
		td {
			text-align : center;		
			height : 40px;				
		}
		.name {
			width : 150px;
			height : 40px;
		}
		.value {
			width : 300px;
			height : 40px;
		}
		.button {
			background-color : light gray;	
			height : 30px;
			width : 100px;
		}
		input[type="text"],[type="number"] {
			width : 200px;
		}
		input::-webkit-inner-spin-button {
			appearance : none;			/* number type의 input태그 옆에 값 조절 버튼 제거 */
		}
		a {								/* a태그의 css속성 지정 */
			color : grey;				/* 글자 색 회색으로 지정 */
			text-decoration : none;		/* a태그 글자의 밑줄 삭제 */
		}
		a:hover {						/* a태그에 마우스가 올라왔을 때의 css속성 지정*/
			color : blue;				/* 글자 색 파란색으로 지정 */
		}
		button {
			cursor : pointer;
		}
	</style>
</head>

<body>
	<%
		ScoreItemService scoreItemService = new ScoreItemServiceImpl();
		ScoreItem scoreItem = scoreItemService.selectId(Integer.parseInt(request.getParameter("studentId")));
		
		ServletContext context = getServletContext();
		context.setAttribute("scoreItem", scoreItem);
	%>
	<div align=center >
		<h1>성적 조회 후 정정 / 삭제</h1>
		
		<br>
		
		<form id="insert_id" method="post" action="updateAndDeleteForm.jsp">
			<b>조회할 학번</b>&nbsp;<input type="number" name="studentId"  required>	&nbsp;<input type="submit" class="button" value="조회" />
		</form>
		
		<br>
		
		<form id="update_form" method="post">
			<table>
				<tr>
					<td  class="name">
						이름
					</td>
					<td class="value">
						<input type=text name="name" id="name" value="${scoreItem.name}" class="inputs">
					</td>
				</tr>
				<tr>
					<td  class="name">
						학번
					</td>
					<td class="value">
						<input type=number name="studentId" id="studentId" value="${scoreItem.studentId}" class="inputs" readOnly>
					</td>																<!-- 학번은 pk이므로 항상 readOnly -->
				</tr>
				<tr>
					<td  class="name">
						국어
					</td>
					<td class="value">
						<input type=number name="kor" id="kor" value="${scoreItem.kor}" class="inputs">
					</td>
				</tr>		
				<tr>
					<td  class="name">
						영어
					</td>
					<td class="value">
						<input type=number name="eng" id=eng value="${scoreItem.eng}" class="inputs">
					</td>
				</tr>
				<tr>
					<td  class="name">
						수학
					</td>
					<td class="value">
						<input type=number name="mat" id="mat" value="${scoreItem.mat}" class="inputs">
					</td>			
				</tr>		
			</table>
			
			<br>
			
			<div id="btn_onoff">									<!-- 학번이 없는 경우 버튼을 숨기기 위해 div태그로 감싸서 토글 -->
				<input type="button" onclick="update_record(this.form);" id="btn_save" class="button" value="저장" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" onclick="delete_record(this.form);" id="btn_delete" class="button" value="삭제" />
			</div>
		</form>
	</div>
	
	<script>
		// 조회한 학번의 레코드가 없는 경우 input태그를 모두 readOnly로 설정하고, "저장", "삭제"버튼을 숨김.
		if ("<%=scoreItem.getName()%>" === "해당 학번 없음") {
			var inputs = document.getElementsByClassName('inputs');
			for (var i = 0 ; i < inputs.length ; i++) {
				inputs[i].readOnly = true;
			}
			document.getElementById('btn_onoff').style.display = "none";
		}
		
		// 수정 버튼 클릭시 유효성검사 & 이동할 페이지를 수정 페이지로 지정하는 함수
		function update_record(form_update) {
			// 이름 길이 1~20글자 제한
			var name = form_update.name.value;
			if (name.length > 20 || name.length < 1) {
				window.alert("이름은 1~20글자로 입력 가능합니다.");
				form_update.name.focus();
				return false;
			}
			// 이름에 들어갈 수 있는 문자 한글, 영서 숫자로 제한
			let regex = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+$/;
			if (!regex.test(name)) {
				window.alert("이름에는 한글, 영어, 숫자 입력이 가능합니다.");
				form_update.name.focus();
				return false;
			}
			// 국어점수 필수 입력
			var score_k = form_update.kor.value;
			if (score_k.length < 1) {
				window.alert("국어점수는 필수 입력입니다.");
				form_update.kor.focus();
				return false;
			}
			// 국어점수 0~100점 입력 제한
			if (score_k > 100 || score_k < 0) {
				window.alert("국어 점수는 0~100점까지 입력이 가능합니다.");
				form_update.kor.focus();
				return false;
			}
			// 영어점수 필수 입력
			var score_e = form_update.eng.value;
			if (score_e.length < 1) {
				window.alert("영어 점수는 필수 입력입니다.");
				form_update.eng.focus();
				return false;
			}
			// 영어점수 0~100점 입력 제한				
			if (score_e > 100 || score_e < 0) {
				window.alert("영어 점수는 0~100점까지 입력이 가능합니다.");
				form_update.eng.focus();
				return false;
			}
			// 수학점수 필수 입력
			var score_m = form_update.mat.value;
			if (score_m.length < 1) {
				window.alert("수학 점수는 필수 입력입니다.");
				form_update.mat.focus();
				return false;
			}
			// 수학점수 0~100점 입력 제한					
			if (score_m > 100 || score_m < 0) {
				window.alert("수학 점수는 0~100점까지 입력이 가능합니다.");
				form_update.mat.focus();
				return false;
			}
			// 이동할 페이지 지정 & 이동
			form_update.action = "./updateOne.jsp";
			form_update.submit();
		}
		
		// 삭제 버튼 클릭시 팝업창으로 최종확인 & 이동할 페이지를 삭제 페이지로 지정하는 함수
		function delete_record(form_delete) {
			if(window.confirm("삭제하시겠습니까?") == false) {
				return false;
			}
			form_delete.action = "./deleteOne.jsp";
			form_delete.submit();
		}
	</script>
</body>

</html>