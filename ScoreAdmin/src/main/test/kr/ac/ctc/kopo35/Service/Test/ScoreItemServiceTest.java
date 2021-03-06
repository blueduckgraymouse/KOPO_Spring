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
	
	// Pagenation, cPage범위 내 연산 검증1 - 페이지 그룹이 1개일 때
	@Test
	void testGetpagination0() {
		int cPage = 3;
		int countPerPage = 10;
		int pageSize = 10;
		int totalcount = 58;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(1, pagination.getStartPage());		// 첫번째 숫자
		assertEquals(6, pagination.getLastPage());		// 마지막 숫자
		assertEquals(0, pagination.getPpPage());		// << 해당 숫자
		assertEquals(0, pagination.getpPage());			// < 해당 숫자
		assertEquals(0, pagination.getnPage());			// > 해당 숫자
		assertEquals(0, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(6, pagination.getTotalPage());		// 총 페이지 수
	}
	
	// Pagenation, cPage범위 내 연산 검증2 - 페이지 그룹이 1개이상 일 때
	@Test
	void testGetpagination1() {
		int cPage = 17;
		int countPerPage = 10;
		int pageSize = 10;
		int totalcount = 351;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(11, pagination.getStartPage());	// 첫번째 숫자
		assertEquals(20, pagination.getLastPage());		// 마지막 숫자
		assertEquals(1, pagination.getPpPage());		// << 해당 숫자
		assertEquals(10, pagination.getpPage());		// < 해당 숫자
		assertEquals(21, pagination.getnPage());		// > 해당 숫자
		assertEquals(36, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(36, pagination.getTotalPage());	// 총 페이지 수
	}
	
	// Pagenation, cPage범위 초과 연산 검증
	@Test
	void testGetpagination2() {
		int cPage = 100;
		int countPerPage = 10;
		int pageSize = 10;
		int totalcount = 351;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(31, pagination.getStartPage());	// 첫번째 숫자
		assertEquals(36, pagination.getLastPage());		// 마지막 숫자
		assertEquals(1, pagination.getPpPage());		// << 해당 숫자
		assertEquals(30, pagination.getpPage());		// < 해당 숫자
		assertEquals(0, pagination.getnPage());			// > 해당 숫자
		assertEquals(0, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(36, pagination.getTotalPage());	// 총 페이지 수
	}
	
	// Pagenation, cPage범위 미만 연산 검증
	@Test
	void testGetpagination3() {
		int cPage = -100;
		int countPerPage = 10;
		int pageSize = 10;
		int totalcount = 351;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(1, pagination.getStartPage());		// 첫번째 숫자
		assertEquals(10, pagination.getLastPage());		// 마지막 숫자
		assertEquals(0, pagination.getPpPage());		// << 해당 숫자
		assertEquals(0, pagination.getpPage());			// < 해당 숫자
		assertEquals(11, pagination.getnPage());		// > 해당 숫자
		assertEquals(36, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(36, pagination.getTotalPage());	// 총 페이지 수
	}
	
	// Pagenation, cPage범위 아래 경계값 검증
	@Test
	void testGetpagination4() {
		int cPage = 1;
		int countPerPage = 10;
		int pageSize = 10;
		int totalcount = 351;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(1, pagination.getStartPage());		// 첫번째 숫자
		assertEquals(10, pagination.getLastPage());		// 마지막 숫자
		assertEquals(0, pagination.getPpPage());		// << 해당 숫자
		assertEquals(0, pagination.getpPage());			// < 해당 숫자
		assertEquals(11, pagination.getnPage());		// > 해당 숫자
		assertEquals(36, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(36, pagination.getTotalPage());	// 총 페이지 수
	}
	
	// Pagenation, cPage범위 위 경계값 검증
	@Test
	void testGetpagination5() {
		int cPage = 36;
		int countPerPage = 10;
		int pageSize = 10;
		int totalcount = 351;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(31, pagination.getStartPage());	// 첫번째 숫자
		assertEquals(36, pagination.getLastPage());		// 마지막 숫자
		assertEquals(1, pagination.getPpPage());		// << 해당 숫자
		assertEquals(30, pagination.getpPage());		// < 해당 숫자
		assertEquals(0, pagination.getnPage());			// > 해당 숫자
		assertEquals(0, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(36, pagination.getTotalPage());	// 총 페이지 수
	}
	
	@Test
	void testGetpagination6() {
		int cPage = 1;
		int countPerPage = 10;
		int pageSize = 10;
		int totalcount = 0;
		
		Pagination pagination = scoreItemService.getPagination(cPage, countPerPage, pageSize, totalcount);
		
		assertEquals(0, pagination.getStartPage());		// 첫번째 숫자
		assertEquals(0, pagination.getLastPage());		// 마지막 숫자
		assertEquals(0, pagination.getPpPage());		// << 해당 숫자
		assertEquals(0, pagination.getpPage());			// < 해당 숫자
		assertEquals(0, pagination.getnPage());			// > 해당 숫자
		assertEquals(0, pagination.getNnPage());		// >> 해당 숫자
		assertEquals(0, pagination.getTotalPage());		// 총 페이지 수
	}
}
