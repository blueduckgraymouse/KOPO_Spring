package kr.ac.ctc.kopo35.Service;

import java.sql.Connection;
import java.util.List;

import kr.ac.ctc.kopo35.Domain.ScoreItem;
import kr.ac.ctc.kopo35.Dto.Pagination;

public interface ScoreItemService {
	// 조회
	List<ScoreItem> selectAll(String cPage);
	int getTotalCount() throws Exception;
	int checkCPage(String strCPage);
	Pagination getPagination(String StrCPage);
	Pagination calPagination(int cPage, int countPerPage, int pageSize, int total);
	List<ScoreItem> selectOne(String name);
	
	// 입력
	boolean insertOne(String name, String kor, String eng, String mat);
	
	// 수정
	ScoreItem selectOne(int id);
	boolean updateOne(String name, String id, String kor, String eng, String mat);
	
	// 삭제
	boolean deleteOne(int id);
	
	// 테이블 리셋
	boolean resetTable();
	
	// 롤백
	public void rollback(Connection conn);
	
	// 커밋
	public void close(Connection conn);
}
