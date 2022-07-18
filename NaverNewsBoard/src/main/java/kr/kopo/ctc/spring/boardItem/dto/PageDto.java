package kr.kopo.ctc.spring.boardItem.dto;

import org.springframework.data.domain.Page;

import kr.kopo.ctc.spring.boardItem.domain.NewsBoardItem;

public class PageDto {
	Page<NewsBoardItem> boardItems;
	Pagination pagination;
	
	public PageDto(Page<NewsBoardItem> boardItems, Pagination pagination) {
		this.boardItems = boardItems;
		this.pagination = pagination;
	}

	public Page<NewsBoardItem> getBoardItems() {
		return boardItems;
	}

	public Pagination getPagination() {
		return pagination;
	}
}
