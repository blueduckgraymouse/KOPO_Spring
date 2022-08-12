package kr.kopo.ctc.spring.boardItem.service;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.kopo.ctc.spring.boardItem.domain.NewsBoardItem;
import kr.kopo.ctc.spring.boardItem.dto.PageDto;
import kr.kopo.ctc.spring.boardItem.dto.Pagination;
import kr.kopo.ctc.spring.boardItem.repository.BoardItemRepository;

@Service
public class BoardItemService {

	private final int countPerPage = 10;
	private final int pageSize = 10;

	@Autowired
	BoardItemRepository boardItemRepository;

	/* 목록 조회 */
	public PageDto getList(Integer cPage, String keyword) {
		
		int cPageNo = checkCPage(cPage, countPerPage, keyword);
		
		PageRequest pageable = PageRequest.of(cPageNo, countPerPage);
		Page<NewsBoardItem> BoardItems = (keyword == null || keyword.equals("") ? 
			boardItemRepository.findAllByOrderByIdDesc(pageable) :
			boardItemRepository.findAllByTitleContainingOrderByIdDesc(keyword, pageable));
		
		Pagination pagination = makePagination(cPageNo + 1, countPerPage, pageSize, (int) BoardItems.getTotalElements());
		
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
		NewsBoardItem BoardItemSave = new NewsBoardItem(boardItemInput.getTitle(),
														boardItemInput.getWriter(),
														new Date(),
														boardItemInput.getContent(),
														0);
		NewsBoardItem BoardItemInserted = boardItemRepository.save(BoardItemSave);
		
		/* temp파일에 모든 이미지가 저장되는 걸 피하기 위해
		 * 저장 처리가 되면 temp의 파일을 id번호를 이름으로 갖는 폴더로 옮기려고 헀으나.
		*  트렌젝션 이슈.
		*  
		*  입력 페이지에서는 id가 부여되지 않아 저장 처리시 파일이동을 해야하는데 그러면 입력폼에서의 미리보기와 상세페이지의 미리보기 2경우를 모두 만족시킬 수 없음.
		*  고민해보자.
		*/
//		try {
//		      File folderTemp = new File("C:/KOPO/git_tracking/spring/NaverNewsBoardImageFile/temp/"); 
//		      File folderId = new File("C:/KOPO/git_tracking/spring/NaverNewsBoardImageFile/" + BoardItemInserted.getId() + "/"); 
//		      File[] files = null;
//
//		      folderId.mkdir();
//		      
//	          files = folderTemp.listFiles();
//		          
//	          for (File file : files) {
//	        	  String fileName = file.getName();
//	        	  File target = new File(folderId + File.separator + fileName);
//	            file.renameTo(target);
//	            System.out.println("이동이동");
//	          }
//		  } catch (Exception e) {
//		      e.printStackTrace();
//		  }
		
		return BoardItemInserted;
	}
	
	/* summernote에 이미지 업로드시 temp 폴더에 저장 */
	public String uploadImageFileInTemp(MultipartFile multipartFile) {
		String locFilePath = "C:/KOPO/git_tracking/spring/NaverNewsBoardImageFile/temp/";
		String WebFilePath = "/NaverNewsBoardImageFile/temp/";
		String fileUrl = null;
		
//		// 원본 파일명 살려서 저장.
//        try {
//            File destination = new File(locFilePath + File.separator + multipartFile.getOriginalFilename());
//            multipartFile.transferTo(destination);
//            
//            fileUrl = WebFilePath + multipartFile.getOriginalFilename();
//        } catch(Exception e) {
//        	e.printStackTrace();
//        }
//		return fileUrl;
		
		// summernote에서 이미지 파일이 증발해버리던 것을 로컬에 저장시킴.
        try {
        	// summernote에서 이미지 파일이 증발해버리던 것을 로컬에 저장시킴.
        	String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
    		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
    		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
            File destination = new File(locFilePath + File.separator + savedFileName);
            multipartFile.transferTo(destination);
            
            fileUrl = WebFilePath + savedFileName;
        } catch(Exception e) {
        	e.printStackTrace();
        }
		return fileUrl;
	}

	/* 수정 처리 */
	public NewsBoardItem updateItem(NewsBoardItem boardItemInput) {
		NewsBoardItem BoardItem = boardItemRepository.findById(boardItemInput.getId()).get();
		BoardItem.setTitle(boardItemInput.getTitle());
		BoardItem.setWriter(boardItemInput.getWriter());
		BoardItem.setContent(boardItemInput.getContent());
		NewsBoardItem BoardItemUpdated = boardItemRepository.save(BoardItem);
		return BoardItemUpdated;
	}

	/* 삭제 처리 */
	public void delete(int id) {
		boardItemRepository.deleteById(id);
	}

	
	
	
	/*
	 * 현재페에지 번호, 한페이지 당 레코드 수, 한 그룹당 페이지 개수, 총 레코드 수를 파라미터로 받아 페이지 정보를 계산하여
	 * pagination객체로 반환하는 메서드
	 */
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
		if (startPage != 1) {
			ppPage = 1;
			pPage = startPage - 1;
		}
		pagination.setPpPage(ppPage);
		pagination.setpPage(pPage);

		// 다음 그룹 첫 페이지 번호 & 마지막 페이지 번호
		int nnPage = 0;
		int nPage = 0;
		if (!(startPage <= totalPage && totalPage <= startPage + pageSize - 1) && (totalPage != 0)) {
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
	public int checkCPage(Integer cPage, int countPerPage, String keyword) {
		int totalcount = (int)(keyword == null || keyword.equals("") ?
			boardItemRepository.count() :
			boardItemRepository.countByTitleContaining(keyword));
		int totalPage = totalcount / countPerPage + (totalcount % countPerPage > 0 ? 1 : 0);
		
		/* 예외 처리 */
		if (cPage <= 0 || totalcount == 0) {	// cPage가 음수 혹은 레코드가 없을 경우
			cPage = 0;
		} else if (cPage > 0 && cPage <= totalPage) {	// 정상일 때 조회를 위해 cPage--
			cPage--;
		} else {								// cPage가 총 페이지 수보다 클 때
			cPage = totalPage - 1;
		}

		return cPage;
	}
}
