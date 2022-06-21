package kr.ac.ctc.kopo35.Service;

import java.util.List;

import kr.ac.ctc.kopo35.Dao.ScoreItemDao;
import kr.ac.ctc.kopo35.Dao.ScoreItemDaoImpl;
import kr.ac.ctc.kopo35.Domain.ScoreItem;
import kr.ac.ctc.kopo35.Dto.Pagination;
import kr.ac.ctc.kopo35.Dto.ScoreItemsDto;

public class ScoreItemServiceImpl implements ScoreItemService {
	
	private ScoreItemDao ScoreItemDao = new ScoreItemDaoImpl();
	private final int countPerPage = 10;
	private final int pageSize = 15;
	
	@Override
	public ScoreItemsDto scoreItemSelectAll(String strCPage) {
		// 현재 페이지 번호 null 체크
		int cPage = 0;
		if (strCPage == null) {
			cPage = 1;
		} else {
			cPage = Integer.parseInt(strCPage);
		}
		
		// 레코드 목록 조회
		ScoreItemsDto scoreItemsDto = new ScoreItemsDto();
		int startRecordNo = (cPage - 1) * countPerPage;
		
		// 페이징 처리
		List<ScoreItem> ScoreItems = ScoreItemDao.selectAll(startRecordNo, countPerPage);
		int totalRecordCount = ScoreItemDao.selectTotalCount();	// 전체 레코드 수 조회
		Pagination pagination = getPagination(cPage, countPerPage, pageSize, totalRecordCount);
		
		scoreItemsDto.setScoreItems(ScoreItems);
		scoreItemsDto.setPagination(pagination);

		return scoreItemsDto;
	}

	
	@Override
	public List<ScoreItem> scoreItemSelectName(String name) {
		return ScoreItemDao.selectName(name);
	}
	
	@Override
	public ScoreItem scoreItemSelectId(int id) {
		return ScoreItemDao.selectId(id);
	}

	@Override
	public boolean scoreItemInsertOne(String name, String kor, String eng, String mat) {
		// 209901을 첫 ID로 기준 자동 부여될 새로운 id값 계산
		int newId = ScoreItemDao.selectNewId();
		int firstId = ScoreItemDao.selectFirstId();	
		if ( firstId != 209901) {
			newId = 209901;
		}
		
		ScoreItem scoreItem = new ScoreItem(name, newId, Integer.parseInt(kor), Integer.parseInt(eng), Integer.parseInt(mat));
		return ScoreItemDao.insertOne(scoreItem) == 1 ? true : false;
	}

	@Override
	public boolean scoreItemUpdateOne(String name, String id, String kor, String eng, String mat) {
		ScoreItem scoreItem = new ScoreItem(name, Integer.parseInt(id), Integer.parseInt(kor), Integer.parseInt(eng), Integer.parseInt(mat));
		return ScoreItemDao.updateOne(scoreItem) == 1 ? true : false;
	}

	@Override
	public boolean scoreItemDeleteOne(int id) {
		return ScoreItemDao.deleteOne(id) == 1 ? true : false;
	}
	

	@Override
	public boolean scoreItemsReset() {
		int countAll = ScoreItemDao.selectTotalCount();
		int countEffected = ScoreItemDao.deleteAll();
		return (countAll == countEffected ? true : false);
	}

	/* 페이지 정보를 계산 하는 메서드 */
	public Pagination getPagination(int cPage, int countPerPage, int pageSize, int totalRecordCount) {
		Pagination pagination = new Pagination();
		
		// 총 페이지 수
		int totalPage = 0;						
		if ((totalRecordCount % countPerPage) == 0) {		
			totalPage = totalRecordCount / countPerPage; 				
		} else {								
			totalPage = totalRecordCount / countPerPage + 1; 				
		}
		pagination.setTotalPage(totalPage);

		// 현재 페이지 번호
		if (cPage >= totalPage) {
			cPage = totalPage;
		} else if (cPage < 1) {
			cPage = 1;
		}
		pagination.setcPage(cPage);
		
		// 첫 페이지 번호
		int startPage = (cPage / pageSize) * pageSize + 1;	
		if ((cPage % pageSize) == 0) {		
			startPage -= pageSize;
		}
		pagination.setStartPage(startPage);
		
		// 마지막 페이지 번호
		int lastPage = (startPage + pageSize - 1) >= totalPage ? totalPage : (startPage + pageSize - 1);
		pagination.setLastPage(lastPage);
		
		// 첫 페이지 번호 & 이전 그룹 마지막 페이지 번호
		int ppPage = 0;
		int pPage = 0;
		if ( startPage != 1) {					
			ppPage = 1;
			pPage = startPage - 1;
		}
		pagination.setPpPage(ppPage);
		pagination.setpPage(pPage);
		
		// 다음 그룹 첫 페이지 번호 & 마지막 페이지 번호
		int nnPage = 0;
		int nPage = 0;
		if (!(startPage <= totalPage && totalPage <= startPage + pageSize) && (totalPage != 0)) {					
			nnPage = totalPage;
			nPage = startPage + pageSize;
		}
		pagination.setNnPage(nnPage);
		pagination.setnPage(nPage);
		
		// 한 페이지 당 레코드 수, 한 그룹 당 페이지 수, 총 게시물 수
		pagination.setCountPerPage(countPerPage);
		pagination.setPageSize(pageSize);
		pagination.setTotalRecordCount(totalRecordCount);
		
		// 레코드가 0개 일 경우 예외 처리
		if (totalRecordCount == 0) {
			pagination.setStartPage(0);
			pagination.setLastPage(0);
			pagination.setPpPage(0);
			pagination.setpPage(0);
			pagination.setNnPage(0);
			pagination.setnPage(0);
			pagination.setTotalPage(0);
		}
		
		return pagination;
	}
}
