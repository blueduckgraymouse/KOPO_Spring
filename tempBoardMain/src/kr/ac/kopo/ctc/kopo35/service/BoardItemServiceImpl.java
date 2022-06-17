package kr.ac.kopo.ctc.kopo35.service;

import java.util.List;

import kr.ac.kopo.ctc.kopo35.dao.BoardItemDao;
import kr.ac.kopo.ctc.kopo35.dao.BoardItemDaoImpl;
import kr.ac.kopo.ctc.kopo35.domain.BoardItem;

public class BoardItemServiceImpl implements BoardItemService {
	private BoardItemDao boarItemDao = new BoardItemDaoImpl();

	@Override
	public BoardItem create(BoardItem boardItem) {
		return boarItemDao.create(boardItem);
	}

	@Override
	public BoardItem selectOne(int id) {
		return boarItemDao.selectOne(id);
	}

	@Override
	public List<BoardItem> selectAll(int page, int countPerPage) {
		return boarItemDao.selectAll(page, countPerPage);
	}
}
