package kr.ac.kopo.ctc.kopo35.service;

import java.util.List;

import kr.ac.kopo.ctc.kopo35.domain.BoardItem;
import kr.ac.kopo.ctc.kopo35.dto.Pagination;

public interface BoardItemService {
	//Pagination getPagination(int pageNo, int countPerPage, int pageSize);
	
	BoardItem create(BoardItem boardItem);
	
	// Read
	BoardItem selectOne(int id);
	List<BoardItem> selectAll(int page, int countPerPage);
}
