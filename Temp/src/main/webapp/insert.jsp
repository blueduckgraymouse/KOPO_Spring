<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<!-- http-equiv 속성은 content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
<%@ page contentType="text/html; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*,javax.sql.*, java.io.*"%>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>

<html>
<head>
	<style>
		table, tr, td {
			border: 1px solid black;	/* 꽉찬 검정 경계선 1px  */
			border-cellpadding : 0;		/* 셀간 경계선 사이 여백 제거  */
			border-collapse: collapse;	/* 셀간 경계선 충돌 허용 */
		}
		td {
			text-align : center;		/* 문자 중앙 정렬, 높이 35px 설정 */
			height : 35px;				
		}
		.button {
			background-color: yellow;	/* button 클래스의 배경 노란색, 높이 30px, 너비 100px 설정 */
			height : 30px;
			width : 100px;
		}
		
	</style>
	
	<%
		request.setCharacterEncoding("UTF-8");					// request객체의 인코딩을 utf-8로 명시
		
		String path = "";
		int sizeLimit = 1024;
		
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		String fileName = MultipartRequest.getFileNames();
	
		Class.forName("com.mysql.jdbc.Driver");				// jdbc 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
		String sql = "insert into temp_file(filelocation) values (?)";
		preparedStatement pstmt = new prepareStatement(sql);
		
		stmt.close();		// Statment 자원 반납
		conn.close();		// Connection 자원 반납
	%>
	
	<script>
		function goback() {
			window.alert("뒤로 가긴 하지만 데이터는 이미 저장된거 아시죠?");
			history.go(-1);
		}
	</script>
</head>

<body>

</body>
</html>