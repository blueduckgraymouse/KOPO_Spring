package kr.ac.ctc.kopo35.Service;

import java.sql.SQLException;
import java.util.List;

import kr.ac.ctc.kopo35.Domain.ScoreItem;
import kr.ac.ctc.kopo35.Dto.Pagination;
import kr.ac.ctc.kopo35.Dto.ScoreItemsDto;

public interface ScoreItemService {
	// 조회
	ScoreItemsDto scoreItemSelectAll(String cPage) throws SQLException;
	
	ScoreItem scoreItemSelectId(int id) throws SQLException;
	
	List<ScoreItem> scoreItemSelectName(String name) throws SQLException;

	Pagination getPagination(int cPage, int countPerPage, int pageSize, int total) throws SQLException;
	
	// 입력
	boolean scoreItemInsertOne(String name, String kor, String eng, String mat) throws SQLException;
	
	// 수정
	boolean scoreItemUpdateOne(String name, String id, String kor, String eng, String mat) throws SQLException;
	
	// 삭제
	boolean scoreItemDeleteOne(int id) throws SQLException;
	
	// 테이블 리셋
	boolean scoreItemsReset() throws SQLException;
}
