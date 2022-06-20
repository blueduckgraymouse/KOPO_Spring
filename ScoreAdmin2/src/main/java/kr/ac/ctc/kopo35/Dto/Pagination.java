package kr.ac.ctc.kopo35.Dto;

public class Pagination {
	private int cPage;		// 현재 페이지 번호
	private int startPage;	// 현재 그룹의 시작 페이지 번호
	private int lastPage;	// 현재 그룹의 마지막 페이지 번호
	
	private int pPage;		// 이전 그룹 마지막 페이지 번호, 없으면 0
	private int ppPage;		// 첫 페이지 번호, 없으면 0
	
	private int nPage;		// 다음 그룹 첫 페이지 번호, 없으면 0
	private int nnPage;		// 총 페이지 번호, 없으면 0
	
	private int countPerPage;	// 한 페이지 당 노출시킬 레코드 수
	private int pageSize;		// 한 페이징 그룹에 들어가는 페이지 수
	private int totalRecordCount;	// 총 레코드 수
	private int totalPage;		// 총 페이지 수
	
	public int getcPage() {
		return cPage;
	}
	public void setcPage(int cPage) {
		this.cPage = cPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getpPage() {
		return pPage;
	}
	public void setpPage(int pPage) {
		this.pPage = pPage;
	}
	public int getPpPage() {
		return ppPage;
	}
	public void setPpPage(int ppPage) {
		this.ppPage = ppPage;
	}
	public int getnPage() {
		return nPage;
	}
	public void setnPage(int nPage) {
		this.nPage = nPage;
	}
	public int getNnPage() {
		return nnPage;
	}
	public void setNnPage(int nnPage) {
		this.nnPage = nnPage;
	}
	public int getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	@Override
	public String toString() {
		return "Pagination [cPage=" + cPage + ", startPage=" + startPage + ", lastPage=" + lastPage + ", pPage=" + pPage
				+ ", ppPage=" + ppPage + ", nPage=" + nPage + ", nnPage=" + nnPage + ", countPerPage=" + countPerPage
				+ ", pageSize=" + pageSize + ", totalRecordCount=" + totalRecordCount + ", totalPage=" + totalPage + "]";
	}
}
