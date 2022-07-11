package kr.ac.ctc.kopo35.Dto;

import java.util.List;

import kr.ac.ctc.kopo35.Domain.ScoreItem;

public class ScoreItemsDto {
	private List<ScoreItem> ScoreItems;
	private Pagination pagination;
	
	public List<ScoreItem> getScoreItems() {
		return ScoreItems;
	}
	public void setScoreItems(List<ScoreItem> scoreItems) {
		ScoreItems = scoreItems;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	@Override
	public String toString() {
		return "ScoreItemsDto [ScoreItems=" + ScoreItems + ", pagination=" + pagination + "]";
	}
	
	
	
}
