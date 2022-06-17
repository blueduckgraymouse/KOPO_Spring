package kr.ac.ctc.kopo35.Service;

import java.util.List;

import kr.ac.ctc.kopo35.Domain.ScoreItem;
import kr.ac.ctc.kopo35.Dto.Pagination;
import kr.ac.ctc.kopo35.Dto.ScoreItemsDto;

public interface ScoreItemService {
	ScoreItemsDto scoreItemSelectAll(String cPage);
	
	ScoreItem scoreItemSelectId(int id);
	
	List<ScoreItem> scoreItemSelectName(String name);

	Pagination getPagination(int cPage, int countPerPage, int pageSize, int total);
	
	boolean scoreItemInsertOne(String name, String kor, String eng, String mat);
	
	boolean scoreItemUpdateOne(String name, String id, String kor, String eng, String mat);
	
	boolean scoreItemDeleteOne(int id);
}
