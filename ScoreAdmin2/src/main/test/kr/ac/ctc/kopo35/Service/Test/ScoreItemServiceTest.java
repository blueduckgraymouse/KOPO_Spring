package kr.ac.ctc.kopo35.Service.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import kr.ac.ctc.kopo35.Dao.ScoreItemDao;
import kr.ac.ctc.kopo35.Dao.Mock.ScoreItemDaoMock;
import kr.ac.ctc.kopo35.Dto.Pagination;
import kr.ac.ctc.kopo35.Service.ScoreItemService;
import kr.ac.ctc.kopo35.Service.ScoreItemServiceImpl;

class ScoreItemServiceTest {
	
	private ScoreItemService scoreItemService = new ScoreItemServiceImpl();
	//private ScoreItemDao scoreItemDao = new ScoreItemDaoMock();
	
	/* Pagenation unit test */
	// Pagenation, 페이징 그룹이 1개 이상일 때
	@Test
	void testGetpagination0() {	// 범위 내
		int cPage = 38;
		int countPerPage = 15;
		int pageSize = 50;
		int totalcount = 1025;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(1, pagination.getStartPage());		// 첫번째 숫자
		assertEquals(50, pagination.getLastPage());		// 마지막 숫자
		assertEquals(0, pagination.getPpPage());		// << 해당 숫자
		assertEquals(0, pagination.getpPage());			// < 해당 숫자
		assertEquals(51, pagination.getnPage());		// > 해당 숫자
		assertEquals(69, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(69, pagination.getTotalPage());	// 총 페이지 수
	}
	@Test
	void testGetpagination1() {	// 아래 경계값
		int cPage = 1;
		int countPerPage = 15;
		int pageSize = 50;
		int totalcount = 1025;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(1, pagination.getStartPage());		// 첫번째 숫자
		assertEquals(50, pagination.getLastPage());		// 마지막 숫자
		assertEquals(0, pagination.getPpPage());		// << 해당 숫자
		assertEquals(0, pagination.getpPage());			// < 해당 숫자
		assertEquals(51, pagination.getnPage());		// > 해당 숫자
		assertEquals(69, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(69, pagination.getTotalPage());	// 총 페이지 수
	}
	@Test
	void testGetpagination2() {	// 위 경계값
		int cPage = 50;
		int countPerPage = 15;
		int pageSize = 50;
		int totalcount = 1025;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(1, pagination.getStartPage());		// 첫번째 숫자
		assertEquals(50, pagination.getLastPage());		// 마지막 숫자
		assertEquals(0, pagination.getPpPage());		// << 해당 숫자
		assertEquals(0, pagination.getpPage());			// < 해당 숫자
		assertEquals(51, pagination.getnPage());		// > 해당 숫자
		assertEquals(69, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(69, pagination.getTotalPage());	// 총 페이지 수
	}
	@Test
	void testGetpagination3() {	// 아래 경계 밖
		int cPage = -100;
		int countPerPage = 15;
		int pageSize = 50;
		int totalcount = 1025;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(1, pagination.getStartPage());		// 첫번째 숫자
		assertEquals(50, pagination.getLastPage());		// 마지막 숫자
		assertEquals(0, pagination.getPpPage());		// << 해당 숫자
		assertEquals(0, pagination.getpPage());			// < 해당 숫자
		assertEquals(51, pagination.getnPage());		// > 해당 숫자
		assertEquals(69, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(69, pagination.getTotalPage());	// 총 페이지 수
	}
	@Test
	void testGetpagination4() {	// 위 경계 밖
		int cPage = 69;
		int countPerPage = 15;
		int pageSize = 50;
		int totalcount = 1025;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(51, pagination.getStartPage());	// 첫번째 숫자
		assertEquals(69, pagination.getLastPage());		// 마지막 숫자
		assertEquals(1, pagination.getPpPage());		// << 해당 숫자
		assertEquals(50, pagination.getpPage());			// < 해당 숫자
		assertEquals(0, pagination.getnPage());		// > 해당 숫자
		assertEquals(0, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(69, pagination.getTotalPage());	// 총 페이지 수
	}
	@Test
	void testGetpagination5() {	// 두번째 그룹 내 페이지
		int cPage = 53;
		int countPerPage = 15;
		int pageSize = 50;
		int totalcount = 1025;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(51, pagination.getStartPage());	// 첫번째 숫자
		assertEquals(69, pagination.getLastPage());		// 마지막 숫자
		assertEquals(1, pagination.getPpPage());		// << 해당 숫자
		assertEquals(50, pagination.getpPage());			// < 해당 숫자
		assertEquals(0, pagination.getnPage());		// > 해당 숫자
		assertEquals(0, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(69, pagination.getTotalPage());	// 총 페이지 수
	}
	
	// Pagenation, 페이징 그룹이 1개 일 때
	@Test
	void testGetpagination6() {	// 범위 내
		int cPage = 17;
		int countPerPage = 15;
		int pageSize = 50;
		int totalcount = 525;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(1, pagination.getStartPage());		// 첫번째 숫자
		assertEquals(35, pagination.getLastPage());		// 마지막 숫자
		assertEquals(0, pagination.getPpPage());		// << 해당 숫자
		assertEquals(0, pagination.getpPage());			// < 해당 숫자
		assertEquals(0, pagination.getnPage());		// > 해당 숫자
		assertEquals(0, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(35, pagination.getTotalPage());	// 총 페이지 수
	}
	@Test
	void testGetpagination7() {	// 아래 경계값
		int cPage = 1;
		int countPerPage = 15;
		int pageSize = 50;
		int totalcount = 525;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(1, pagination.getStartPage());		// 첫번째 숫자
		assertEquals(35, pagination.getLastPage());		// 마지막 숫자
		assertEquals(0, pagination.getPpPage());		// << 해당 숫자
		assertEquals(0, pagination.getpPage());			// < 해당 숫자
		assertEquals(0, pagination.getnPage());		// > 해당 숫자
		assertEquals(0, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(35, pagination.getTotalPage());	// 총 페이지 수
	}
	@Test
	void testGetpagination8() {	// 위 경계값
		int cPage = 35;
		int countPerPage = 15;
		int pageSize = 50;
		int totalcount = 525;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(1, pagination.getStartPage());		// 첫번째 숫자
		assertEquals(35, pagination.getLastPage());		// 마지막 숫자
		assertEquals(0, pagination.getPpPage());		// << 해당 숫자
		assertEquals(0, pagination.getpPage());			// < 해당 숫자
		assertEquals(0, pagination.getnPage());			// > 해당 숫자
		assertEquals(0, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(35, pagination.getTotalPage());	// 총 페이지 수
	}
	@Test
	void testGetpagination9() {	// 아래 경계 밖
		int cPage = -100;
		int countPerPage = 15;
		int pageSize = 50;
		int totalcount = 525;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(1, pagination.getStartPage());		// 첫번째 숫자
		assertEquals(35, pagination.getLastPage());		// 마지막 숫자
		assertEquals(0, pagination.getPpPage());		// << 해당 숫자
		assertEquals(0, pagination.getpPage());			// < 해당 숫자
		assertEquals(0, pagination.getnPage());			// > 해당 숫자
		assertEquals(0, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(35, pagination.getTotalPage());	// 총 페이지 수
	}
	@Test
	void testGetpagination10() {	// 위 경계 밖
		int cPage = 100;
		int countPerPage = 15;
		int pageSize = 50;
		int totalcount = 525;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(1, pagination.getStartPage());		// 첫번째 숫자
		assertEquals(35, pagination.getLastPage());		// 마지막 숫자
		assertEquals(0, pagination.getPpPage());		// << 해당 숫자
		assertEquals(0, pagination.getpPage());			// < 해당 숫자
		assertEquals(0, pagination.getnPage());			// > 해당 숫자
		assertEquals(0, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(35, pagination.getTotalPage());	// 총 페이지 수
	}
}
