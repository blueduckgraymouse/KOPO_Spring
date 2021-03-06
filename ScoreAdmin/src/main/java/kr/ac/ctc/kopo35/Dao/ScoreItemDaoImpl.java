package kr.ac.ctc.kopo35.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.ctc.kopo35.Domain.ScoreItem;

public class ScoreItemDaoImpl implements ScoreItemDao {

	public ScoreItemDaoImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
		} catch (Exception e) {
			throw new IllegalStateException("jdbc 드라이버 로드 실패 : " + e.getMessage());
		}
	}
	
	
	@Override
	public List<ScoreItem> selectAll(int startRecordNo, int countPerPage) {
		String sql = "select * from examtable limit ?, ?";
		List<ScoreItem> ScoreItems = new ArrayList<ScoreItem>();

		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setInt(1, startRecordNo);
			pstmt.setInt(2, countPerPage);
			try (ResultSet rset = pstmt.executeQuery();) {
				while (rset.next()) {
					ScoreItem scoreItem = new ScoreItem();
					scoreItem.setName(rset.getString("name"));
					scoreItem.setStudentId(rset.getInt("studentid"));
					scoreItem.setKor(rset.getInt("kor"));
					scoreItem.setEng(rset.getInt("eng"));
					scoreItem.setMat(rset.getInt("mat"));
					
					ScoreItems.add(scoreItem);
				}
			}
		} catch (Exception e) {
			throw new IllegalStateException("db 연결 실패" + e.getMessage());
		}
		
		return ScoreItems;
	}

	
	@Override
	public int selectTotalCount() {

		String sql = "select count(*) from examtable";
		int result = 0;
		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/kopoctc", "root", "abcd1234");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
			) {
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new IllegalStateException("db 연결 실패" + e.getMessage());
		}
		
		return result;
	}
	
	
	@Override
	public ScoreItem selectId(int id) {
		String sql = "select * from examtable where studentid=?";
		ScoreItem scoreItem = new ScoreItem();

		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setInt(1, id);
			try (ResultSet rset = pstmt.executeQuery();) {
				rset.next();
					scoreItem = new ScoreItem();
					scoreItem.setName(rset.getString("name"));
					scoreItem.setStudentId(rset.getInt("studentid"));
					scoreItem.setKor(rset.getInt("kor"));
					scoreItem.setEng(rset.getInt("eng"));
					scoreItem.setMat(rset.getInt("mat"));
				}
		} catch (Exception e) {
			throw new IllegalStateException("db 연결 실패" + e.getMessage());
		}
		
		return scoreItem;
	}
	
	@Override
	public List<ScoreItem> selectName(String Name) {
		String sql = "select * from examtable where Name=?";
		List<ScoreItem> ScoreItems = new ArrayList<ScoreItem>();

		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setString(1, Name);
			try (ResultSet rset = pstmt.executeQuery();) {
				while(rset.next()) {
					ScoreItem scoreItem = new ScoreItem();
					scoreItem.setName(rset.getString("name"));
					scoreItem.setStudentId(rset.getInt("studentid"));
					scoreItem.setKor(rset.getInt("kor"));
					scoreItem.setEng(rset.getInt("eng"));
					scoreItem.setMat(rset.getInt("mat"));
					
					ScoreItems.add(scoreItem);
				}
			}
		} catch (Exception e) {
			throw new IllegalStateException("db 연결 실패" + e.getMessage());
		}
		
		return ScoreItems;
	}

	@Override
	public int selectNewId() {
		String sql = "select studentid+1 from examtable where (studentid+1) not in (select studentid from examtable)";
		int newId = 0;
		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			try (ResultSet rset = pstmt.executeQuery();) {
				rset.next();
				newId = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new IllegalStateException("db 연결 실패" + e.getMessage());
		}
		
		return newId;
	}

	@Override
	public int selectFirstId() {
		String sql = "select studentid from examtable limit 1";
		int firstId = 0;
		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			try (ResultSet rset = pstmt.executeQuery();) {
				rset.next();
				firstId = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new IllegalStateException("db 연결 실패" + e.getMessage());
		}
		return firstId;
	}
	
	@Override
	public int insertOne(ScoreItem scoreItem) {
		String sql = "insert into examtable values(?, ?, ?, ?, ?)";
		int result = 0;

		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/kopoctc", "root", "abcd1234");
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setString(1, scoreItem.getName());
			pstmt.setInt(2, scoreItem.getStudentId());
			pstmt.setInt(3, scoreItem.getKor());
			pstmt.setInt(4, scoreItem.getEng());
			pstmt.setInt(5, scoreItem.getMat());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw new IllegalStateException("db 연결 실패" + e.getMessage());
		}
		
		return result;
	}


	@Override
	public int updateOne(ScoreItem scoreItem) {
		String sql = "update examtable set name=?, kor=?, eng=?, mat=? where studentid=?";
		int result = 0;

		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/kopoctc", "root", "abcd1234");
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setString(1, scoreItem.getName());
			pstmt.setInt(2, scoreItem.getKor());
			pstmt.setInt(3, scoreItem.getEng());
			pstmt.setInt(4, scoreItem.getMat());
			pstmt.setInt(5, scoreItem.getStudentId());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw new IllegalStateException("db 연결 실패" + e.getMessage());
		}
		
		return result;
	}


	@Override
	public int deleteOne(int id) {
		String sql = "delete from examtable where studentid=?";
		int result = 0;

		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/kopoctc", "root", "abcd1234");
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw new IllegalStateException("db 연결 실패" + e.getMessage());
		}
		
		return result;
	}
	
}
