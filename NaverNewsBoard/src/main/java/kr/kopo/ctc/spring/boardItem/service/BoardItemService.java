package kr.kopo.ctc.spring.boardItem.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.kopo.ctc.spring.boardItem.domain.NewsBoardItem;
import kr.kopo.ctc.spring.boardItem.dto.PageDto;
import kr.kopo.ctc.spring.boardItem.dto.Pagination;
import kr.kopo.ctc.spring.boardItem.repository.BoardItemRepository;

@Service
public class BoardItemService {
	
	private final int countPerPage = 20;
	private final int pageSize = 10;
	
	@Autowired
	BoardItemRepository boardItemRepository;
	
	
	/* 목록 조회 */
	public PageDto getList(String cPage) {
		int cPageNo = checkCPage(cPage, countPerPage);
		
		PageRequest pageable = PageRequest.of(cPageNo, countPerPage);
		Page<NewsBoardItem> BoardItems = boardItemRepository.findAll(pageable);
		

		Pagination pagination = makePagination(cPageNo + 1, countPerPage, pageSize, (int)BoardItems.getTotalElements());
		PageDto PageDto = new PageDto(BoardItems, pagination);
		return PageDto;
	}
	
	
	/* 단일 조회 */
	public NewsBoardItem getView(int id) {
		NewsBoardItem BoardItem = boardItemRepository.findById(id).get();
		boardItemRepository.increaseViewcnt(id);
		boardItemRepository.save(BoardItem);
		return BoardItem;
	}
	
	
	/* 입력 처리 */
	public NewsBoardItem insertItem(NewsBoardItem boardItemInput) {
		NewsBoardItem BoardItemSave = new NewsBoardItem(
										boardItemInput.getTitle(),
										boardItemInput.getWriter(),
										new Date(),
										boardItemInput.getContent(),
										0);
		NewsBoardItem BoardItemInserted = boardItemRepository.save(BoardItemSave);
		return BoardItemInserted;
	}
	
	
	/* 수정 처리 */
	public NewsBoardItem updateItem(NewsBoardItem boardItemInput) {
		NewsBoardItem BoardItem = boardItemRepository.findById(boardItemInput.getId()).get();
		BoardItem.setTitle(boardItemInput.getTitle());
		BoardItem.setContent(boardItemInput.getContent());
		NewsBoardItem BoardItemUpdated = boardItemRepository.save(BoardItem);
		return BoardItemUpdated;
	}
	
	
	/* 삭제 처리 */
	public void delete(int id) {
		boardItemRepository.deleteById(id);
	}
	
	
	/* -------------------------------------------------------------------------------------------------- */
	
	
	/* 현재페에지 번호, 한페이지 당 레코드 수, 한 그룹당 페이지 개수, 총 레코드 수를 파라미터로 받아 페이지 정보를 계산하여 pagination객체로 반환하는 메서드  */
	public Pagination makePagination(int cPage, int countPerPage, int pageSize, int totalRecordCount) {

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
	
	
	/* 목록 조회에서의 페이지 번호에 대한 예외 처리 */
	public int checkCPage(String cPage, int countPerPage) {
		int totalcount = boardItemRepository.getTotalCount();
		int totalPage = totalcount / countPerPage + (totalcount % countPerPage > 0 ? 1 : 0);
		int cPageNo = 0;
		try {
			if (Integer.parseInt(cPage) < 1) {
				cPageNo = 0; 
			} else if (Integer.parseInt(cPage) > 1 && Integer.parseInt(cPage) <= totalPage) {
				cPageNo = Integer.parseInt(cPage) - 1;
			} else {
				cPageNo = totalPage - 1;
			}
		} catch(Exception e) {
		}
		return cPageNo;
	}
}
