package kr.kopo.ctc.spring.board2.repository;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import kr.kopo.ctc.spring.board2.domain.BoardItem;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardItemRepositoryTest {
	
	@Autowired
	private BoardItemRepository boardItemRepository;
	
	
	/* 새로운 게시물 입력 테스트 */
	@Test
	@Order(1)
	void testCreate() {
		// 입력할 BoardItem 객체 생성
		Date date = new Date();
		date.setTime(0);
		BoardItem boardItemInput = new BoardItem(112, "글12", "작성자12", date, 0);
		
		// 입력
		boardItemRepository.save(boardItemInput);
		
		// 확인
		List<BoardItem> boardItemOutput = boardItemRepository.findAllByNo(112);
		System.out.println("\n*** create - 글12 insert 후 조회");
		printList(boardItemOutput);
	}
	
	
	/* 페이징 없이 전체 조회 */
	@Test
	@Order(2)
	void testSelectAll01() {
		// 조회
		List<BoardItem> BoardItems = boardItemRepository.findAll();
		
		// 출력
		System.out.println("\n*** selectAll 1 - 페이징X, 검색X");
		printList(BoardItems);
	}
	
	
	/* 사이즈 10인 첫번째 페이지 조회 */
	@Test
	@Order(3)
	void testSelectAll02() {
		// 조회
		PageRequest pageable = PageRequest.of(0, 10);
		Page<BoardItem> page = boardItemRepository.findAll(pageable);
		
		// 출력
		System.out.println("\n*** selectAll 2 - 페이징O, 검색X ");
		System.out.println("  * pagination : " + page.getPageable().toString());
		System.out.println("  * getSize : " + page.getSize());						// 한 페이지를 구성하는 레코드 개수
		System.out.println("  * getNumber : " + page.getNumber());					// 현재 페이지 번호
		System.out.println("  * getTotalPages : " + page.getTotalPages());			// 총 페이지 개수
		System.out.println("  * getTotalElements : " + page.getTotalElements());		// 총 레코드 개수
		System.out.println("  * getNumberOfElements : " + page.getNumberOfElements());	// 현재 페이지에서의 레코드 개수
		System.out.println("  * Contents : ");
		printList(page.getContent());
	}
	
	
	/* "제목"이라는 글자를 갖는 title인 게시물 검색 조회 */
	@Test
	@Order(4)
	void testSelectAll03() {
		// 조회
		List<BoardItem> BoardItems = boardItemRepository.findAllByTitleLike("%제목%");
		
		// 출력
		System.out.println("\n*** selectAll 3 - 페이징x, 검색O, 키워드 '제목'");
		printList(BoardItems);
	}
	
	
	/* "제목"이라는 글자를 갖는 title인 게시물 검색 조회 + 페이징 */
	@Test
	@Order(5)
	void testSelectAll04() {
		// 조회
		PageRequest pageable = PageRequest.of(0, 10);
		Page<BoardItem> page = boardItemRepository.findAllByTitleLike("%제목%", pageable);

		// 출력
		System.out.println("\n*** selectAll 4 - 페이징O, 검색O, 키워드 '제목' ");
		System.out.println("  * pagination : " + page.getPageable().toString());
		System.out.println("  * getSize : " + page.getSize());						// 한 페이지를 구성하는 레코드 개수
		System.out.println("  * getNumber : " + page.getNumber());					// 현재 페이지 번호
		System.out.println("  * getTotalPages : " + page.getTotalPages());			// 총 페이지 개수
		System.out.println("  * getTotalElements : " + page.getTotalElements());		// 총 레코드 개수
		System.out.println("  * getNumberOfElements : " + page.getNumberOfElements());	// 현재 페이지에서의 레코드 개수
		System.out.println("  * Contents : ");
		printList(page.getContent());
	}
	
	
	/* no가 101인 게시물 조회 */
	@Test
	@Order(6)
	void testSelectOne() {
		// 조회
		List<BoardItem> boardItem = boardItemRepository.findAllByNo(101);
		
		// 출력
		System.out.println("\n*** selectOne - no가 101인 게시물 조회");
		printList(boardItem);
	}
	
	
	/* "제목"이라는 글자를 갖는 title인 게시물 검색 조회 + 페이징 */
	@Test
	@Order(7)
	void testUpdate() {
		// 수정할 글의 no를 통해 id 확인 <- id기준이면 그냥 save로 update 동작 가능
		List<BoardItem> boardItemOutput = boardItemRepository.findAllByNo(112);
		
		// 확인한 id를 포함한 BoardItem 객체 완성 후 수정
		BoardItem boardItemInput = new BoardItem(112, "글12_수정", "작성자4", new Date(), 100);
		for (BoardItem boardItem : boardItemOutput) {
			boardItemInput.setId(boardItem.getId());
			boardItem = boardItemInput;
			boardItemRepository.save(boardItem);
		}
		
		// 업데이트 확인 조회
		List<BoardItem> boardItemSelected = boardItemRepository.findAllByNo(112);
		System.out.println("\n*** update - 글12 update 후 조회 ");
		printList(boardItemSelected);
	}

	
	/* no가 112인 게시물 삭제 */
	@Test
	@Order(8)
	void testDeleteOne() {
		// 삭제
		boardItemRepository.deleteByNo(112); 
		
		// 조회
		List<BoardItem> boardItem = boardItemRepository.findAllByNo(112);
		System.out.println("\n*** delete - 글12 삭제 후 조회");
		printList(boardItem);
		System.out.println("\nend.\n");
	}
	
	void printList(List<BoardItem> boardItems) {
		for (BoardItem boardItem : boardItems) {
			System.out.println("   * " + boardItem.toString());
		}
	}
}
