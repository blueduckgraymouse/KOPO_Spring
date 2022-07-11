<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<!-- http-equiv 속성은 content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
<%@ page contentType="text/html; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<html>
<head>
	<style>
		a {							/* a태그의 css속성 지정 */
			color : grey;			/* 글자 색 회색으로 지정 */
			text-decoration : none;	/* a태그 글자의 밑줄 삭제 */
		}
		a:hover {					/* a태그에 마우스가 올라왔을 때의 css속성 지정*/
			color : blue;			/* 글자 색 파란색으로 지정 */
		}
		#paging td {				
			font-size : 20px;		/* 페이징처리 부분의 글자 크기를 20px로 설정 */
			padding : 5px;
		}
	</style>
	<%
		Class.forName("com.mysql.jdbc.Driver");		// jdbc 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
		Statement stmt = conn.createStatement();	// DB에 쿼리문를 날려주는 statement 객체 생성
		
		String query = "select * from temp_file;";
		ResultSet rset = stmt.executeQuery(query);
	%>
</head>

<body>
	<div align="center">
		<table cellspacing=1 border=1>		<!-- 테이블의 셀간 여백 1, 선 두께 1로 지정 -->
		<tr height=40px  bgcolor=gray>		<!-- 현재 행의 높이를 40px, 배경색을 회색으로 지정 -->
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				id
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				저장위치
			</th>	
		</tr>
		<%
			while (rset.next()) {						// ResultSet에 저장된 모든 레코드에 대하여 반복
				out.println("<tr height=50px>");												// 이름을 a태그로 감싸고 링크에 이름을 파라미터로 갖는 oneviewDB.jsp로 지정
				out.println("<td width=50><p align='center'>" + rset.getInt(1) + "</p></td>");	// 국어 점수 출력
				out.println("<td width=50><p align='center'>" + rset.getString(2) + "</p></td>");	// 영어 점수 출력
				out.println("</tr>");
			}
		%>
		</table>
		
		<%	
			rset.close();		// ResultSet 자원 반납
			rset.close();		// ResultSet 자원 반납
			stmt.close();		// Statment 자원 반납
			conn.close();		// Connection 자원 반납
		%>
	</div>
</body>
</html>
