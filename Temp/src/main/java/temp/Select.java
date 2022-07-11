package temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Select {
	public selectAll() {
		Class.forName("com.mysql.jdbc.Driver");		// jdbc 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
		Statement stmt = conn.createStatement();	// DB에 쿼리문를 날려주는 statement 객체 생성
		
		String query1 = "select count(*) from examtable;";
		ResultSet rset1 = stmt.executeQuery(query1);		// 총 레코드 수를 조회하는 쿼리문 실행하여 결과를 ResetSet 객체에 저장
		rset1.next();
		count_total = rset1.getInt(1);						// 페이징 처리를 위한 총 레코드 개수
		
		String query2 = "select *, " + 						// examtable 테이블의 현재 페이지 번호에 해당하는 레코드 조회 쿼리문을 query변수에 저장
						"(t.kor+t.eng+t.mat) as sum, " +
						"(t.kor+t.eng+t.mat)/3 as ave, " + 
						"(select count(*)+1 from examtable as r where r.kor+r.eng+r.mat > sum) as rank_sum " + 
						"from examtable as t limit "+ ((Integer.parseInt(no_page)-1)*size_page) + ", " + size_page + ";";
		ResultSet rset2 = stmt.executeQuery(query2); 	// 쿼리문 실행한 결과를 ResultSet객체에 저장
	}
}
