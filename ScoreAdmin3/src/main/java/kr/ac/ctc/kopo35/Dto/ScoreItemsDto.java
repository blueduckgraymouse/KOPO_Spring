package kr.ac.ctc.kopo35.Dto;

import java.util.List;

import kr.ac.ctc.kopo35.Domain.ScoreItem;

public class ScoreItemsDto {
	private List<ScoreItem> scoreItems;
	private Pagination pagination;
	
	public ScoreItemsDto(List<ScoreItem> scoreItems, Pagination pagination) {
		this.scoreItems = scoreItems;
		this.pagination = pagination;
	}
	
	public List<ScoreItem> getScoreItems() {
		return scoreItems;
	}
	
	public Pagination getPagination() {
		return pagination;
	}
}
