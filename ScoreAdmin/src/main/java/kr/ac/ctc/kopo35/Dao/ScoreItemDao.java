package kr.ac.ctc.kopo35.Dao;

import java.util.List;

import kr.ac.ctc.kopo35.Domain.ScoreItem;

public interface ScoreItemDao {
	List<ScoreItem> selectAll(int startRecordNo, int countPerPage);
	
	ScoreItem selectId(int id);
	List<ScoreItem> selectName(String name);
	
	int selectTotalCount();
	
	int selectNewId();
	
	int selectFirstId();
	
	int insertOne(ScoreItem scoreItem);
	
	int updateOne(ScoreItem scoreItem);
	
	int deleteOne(int id);
}
