package kr.ac.ctc.kopo35.Dao;

import java.sql.Connection;
import java.util.List;

import kr.ac.ctc.kopo35.Domain.ScoreItem;

public interface ScoreItemDao {
	// 전체 조회
	List<ScoreItem> selectAll(Connection conn, int startRecordNo, int countPerPage);
	
	// 상세 조회
	ScoreItem selectId(Connection conn, int id);
	
	List<ScoreItem> selectName(Connection conn, String name);
	
	int selectTotalCount(Connection conn);
	
	// 입력
	int selectNewId(Connection conn);
	
	int selectFirstId(Connection conn);
	
	int insertOne(Connection conn, ScoreItem scoreItem);
	
	// 수정
	int updateOne(Connection conn, ScoreItem scoreItem);
	
	
	// 삭제
	int deleteOne(Connection conn, int id);
	
	// 리셋
	int deleteAll(Connection conn);
}
