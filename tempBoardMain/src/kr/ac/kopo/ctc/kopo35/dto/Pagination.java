package kr.ac.kopo.ctc.kopo35.dto;

public class Pagination {	// Pagination와 목록 조회 data dto를 합치는게 나은지? 분리하는게 나은지? 
	private int ppPage;
	private int pPage;
	private int nPage;
	private int nnPage;
	private int cPage;
	
	private int countPerPage;	// 한 페이지 당 노출시킬 레코드 수
	private int pageSize;		// 한 페이징 그룹에 들어가는 페이지 수
	private int totalCount;
	
	
	
}
