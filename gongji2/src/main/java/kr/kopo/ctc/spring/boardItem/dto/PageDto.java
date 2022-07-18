package kr.kopo.ctc.spring.boardItem.dto;

import org.springframework.data.domain.Page;

import kr.kopo.ctc.spring.boardItem.domain.ReplyBoardItem;

public class PageDto {
	Page<ReplyBoardItem> boardItems;
	Pagination pagination;
	
	public PageDto(Page<ReplyBoardItem> boardItems, Pagination pagination) {
		this.boardItems = boardItems;
		this.pagination = pagination;
	}

	public Page<ReplyBoardItem> getBoardItems() {
		return boardItems;
	}

	public Pagination getPagination() {
		return pagination;
	}
}
