package kr.ac.ctc.kopo35.Dao;

import java.util.List;

import kr.ac.ctc.kopo35.Domain.ScoreItem;

public interface ScoreItemDao {
	// 전체 조회
	List<ScoreItem> selectAll(int startRecordNo, int countPerPage);
	
	// 상세 조회
	ScoreItem selectId(int id);
	
	List<ScoreItem> selectName(String name);
	
	int selectTotalCount();
	
	// 입력
	int selectNewId();
	
	int selectFirstId();
	
	int insertOne(ScoreItem scoreItem);
	
	// 수정
	int updateOne(ScoreItem scoreItem);
	
	
	// 삭제
	int deleteOne(int id);
	
	// 리셋
	int deleteAll();
}
