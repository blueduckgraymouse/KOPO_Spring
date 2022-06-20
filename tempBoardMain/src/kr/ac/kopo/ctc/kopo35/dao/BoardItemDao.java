package kr.ac.kopo.ctc.kopo35.dao;

import java.util.List;

import kr.ac.kopo.ctc.kopo35.domain.BoardItem;

public interface BoardItemDao {
	// Craete
	BoardItem create(BoardItem boardItem);
	
	// Read
	BoardItem selectOne(int id);
	List<BoardItem> selectAll(int page, int countPerPage);
	
	// Update
	
	
	// Delete
}