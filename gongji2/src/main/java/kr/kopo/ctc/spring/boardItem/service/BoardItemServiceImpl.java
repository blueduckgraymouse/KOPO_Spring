package kr.kopo.ctc.spring.boardItem.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.kopo.ctc.spring.boardItem.domain.ReplyBoardItem;
import kr.kopo.ctc.spring.boardItem.dto.PageDto;
import kr.kopo.ctc.spring.boardItem.dto.Pagination;
import kr.kopo.ctc.spring.boardItem.repository.BoardItemRepository;

@Service
public class BoardItemServiceImpl {
	private final int countPerPage = 20;
	private final int pageSize = 10;
	
	@Autowired
	BoardItemRepository boardItemRepository;
	
	
	public PageDto getList(String cPage) {
		int cPageNo = 0;
		if (cPage == null) {
			cPageNo = 0;
		} else if (Integer.parseInt(cPage) < 1) {
			cPageNo = 0;
		} else if (Integer.parseInt(cPage) > 1) {
			cPageNo = Integer.parseInt(cPage) - 1;
		}
		PageRequest pageable = PageRequest.of(cPageNo, countPerPage);
		//Page<ReplyBoardItem> BoardItems = boardItemRepository.findAll(pageable);
		Page<ReplyBoardItem> BoardItems = boardItemRepository.findAllByOrderByRootidDescRecntAsc(pageable);
		Pagination pagination = makePagination(cPageNo + 1, countPerPage, pageSize, (int)BoardItems.getTotalElements());
		PageDto PageDto = new PageDto(BoardItems, pagination);
		return PageDto;
	}
//	public PageDto getList(Integer cPage) {
//		if (cPage < 1) {
//			cPage = 0;
//		} else if (cPage >= 1) {
//			cPage--;
//		}
//		PageRequest pageable = PageRequest.of(cPage, countPerPage);
//		Page<ReplyBoardItem> BoardItems = boardItemRepository.findAll(pageable);
//		Pagination pagination = makePagination(cPage + 1, countPerPage, pageSize, (int)BoardItems.getTotalElements());
//		PageDto PageDto = new PageDto(BoardItems, pagination);
//		return PageDto;
//	}
	
	
	public ReplyBoardItem getView(int id) {
		ReplyBoardItem BoardItem = boardItemRepository.findById(id).get();
		return BoardItem;
	}
	
	public ReplyBoardItem insertItem(ReplyBoardItem boardItemInput) {
		ReplyBoardItem BoardItemSave = new ReplyBoardItem(
										boardItemInput.getTitle(),
										new Date(),
										boardItemInput.getContent(),
										0,
										0,
										0);
		ReplyBoardItem BoardItemSaved = boardItemRepository.save(BoardItemSave);
		BoardItemSaved.setRootid(BoardItemSaved.getId());
		ReplyBoardItem BoardItemFinal = boardItemRepository.save(BoardItemSaved);
		return BoardItemFinal;
	}
	
	public ReplyBoardItem updateItem(ReplyBoardItem boardItemInput) {
		ReplyBoardItem BoardItemBefore = boardItemRepository.findById(boardItemInput.getId()).get();
		BoardItemBefore.setTitle(boardItemInput.getTitle());
		BoardItemBefore.setContent(boardItemInput.getContent());
		ReplyBoardItem BoardItemUpdated = boardItemRepository.save(BoardItemBefore);
		return BoardItemUpdated;
	}
	
	public void delete(int id) {
		boardItemRepository.deleteById(id);
	}
	
	public ReplyBoardItem insertReply(int id, String title, String content, int rootid) {

		return null;
	}
	
	
	
	
	/* ??????????????? ??????, ???????????? ??? ????????? ???, ??? ????????? ????????? ??????, ??? ????????? ?????? ??????????????? ?????? ????????? ????????? ???????????? pagination????????? ???????????? ?????????  */
	public Pagination makePagination(int cPage, int countPerPage, int pageSize, int totalRecordCount) {

		Pagination pagination = new Pagination();
		
		// ??? ????????? ???
		int totalPage = 0;						
		if ((totalRecordCount % countPerPage) == 0) {		
			totalPage = totalRecordCount / countPerPage; 				
		} else {								
			totalPage = totalRecordCount / countPerPage + 1; 				
		}
		pagination.setTotalPage(totalPage);

		// ?????? ????????? ??????
		if (cPage >= totalPage) {
			cPage = totalPage;
		} else if (cPage < 1) {
			cPage = 1;
		}
		pagination.setcPage(cPage);
		
		// ??? ????????? ??????
		int startPage = (cPage / pageSize) * pageSize + 1;	
		if ((cPage % pageSize) == 0) {		
			startPage -= pageSize;
		}
		pagination.setStartPage(startPage);
		
		// ????????? ????????? ??????
		int lastPage = (startPage + pageSize - 1) >= totalPage ? totalPage : (startPage + pageSize - 1);
		pagination.setLastPage(lastPage);
		
		// ??? ????????? ?????? & ?????? ?????? ????????? ????????? ??????
		int ppPage = 0;
		int pPage = 0;
		if ( startPage != 1) {					
			ppPage = 1;
			pPage = startPage - 1;
		}
		pagination.setPpPage(ppPage);
		pagination.setpPage(pPage);
		
		// ?????? ?????? ??? ????????? ?????? & ????????? ????????? ??????
		int nnPage = 0;
		int nPage = 0;
		if (!(startPage <= totalPage && totalPage <= startPage + pageSize) && (totalPage != 0)) {					
			nnPage = totalPage;
			nPage = startPage + pageSize;
		}
		pagination.setNnPage(nnPage);
		pagination.setnPage(nPage);
		
		// ??? ????????? ??? ????????? ???, ??? ?????? ??? ????????? ???, ??? ????????? ???
		pagination.setCountPerPage(countPerPage);
		pagination.setPageSize(pageSize);
		pagination.setTotalRecordCount(totalRecordCount);
		
		// ???????????? 0??? ??? ?????? ?????? ??????
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
