package kr.kopo.ctc.spring.board2.repository.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.kopo.ctc.spring.board2.domain.BoardItem;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardItemMapperTest2 {

	//private static final Logger logger = LoggerFactory.getLogger(BoardItemMapperTest.class);
	
	@Autowired
	BoardItemMapper boardItemMapper;
	
	
	/* 입력 - 122번 글 등록 */
	@Test
	@Order(1)
	void insertOne() {
		// 입력할 BoardItem 객체 생성
		BoardItem boardItemInput = new BoardItem(122, "글22", "작성자22");
		
		// 입력
		boardItemMapper.insertOne(boardItemInput);
		
		// 조회 & 출력
		List<BoardItem> boardItems = boardItemMapper.selectAll(122);
		System.out.println("*** insert - 글22 등록 후 조회");
		printList(boardItems);
		System.out.println();
	}
	
	
	/* 전체 조회 - 페이징x, 검색x*/
	@Test
	@Order(2)
	void selectAll() {
		// 조회
		List<BoardItem> boardItems = boardItemMapper.selectAll();
		
		// 출력
		System.out.println("*** selectAll - 페이징x, 검색x");
		printList(boardItems);
		System.out.println();
	}
	
	
	/* 전제 조회 - 페이징o, 검색x */
	@Test
	@Order(3)
	void selectAllInAPage() {
		// 조회
		RowBounds rowBounds = new RowBounds(0, 10);
		List<BoardItem> boardItems = boardItemMapper.selectAll(rowBounds);
		
		// 출력
		System.out.println("*** selectAll - 페이징o, 검색x");
		printList(boardItems);
		System.out.println();
	}

	
	/* 전제 조회 - 페이징x, 검색o */
	@Test
	@Order(4)
	void selectAllByTitle() {
		// 조회
		List<BoardItem> boardItems = boardItemMapper.selectAllByTitle("%글%");
		
		// 출력
		System.out.println("*** selectAll - 페이징x, 검색o : '글'");
		printList(boardItems);
		System.out.println();
	}
	
	
	/* 전제 조회 - 페이징o, 검색o */
	@Test
	@Order(5)
	void selectAllByTitleInAPage() {
		// 조회
		RowBounds rowBounds = new RowBounds(0, 10);
		List<BoardItem> boardItems = boardItemMapper.selectAllByTitle("%제목%", rowBounds);
		
		// 출력
		System.out.println("*** selectAll - 페이징o, 검색o : '제목'");
		printList(boardItems);
		System.out.println();
	}
	
	
	/* 단일 조회 - id 1인 게시물 조회 */
	@Test
	@Order(6)
	void selectOne() {
		// 조회
		BoardItem boardItem = boardItemMapper.selectOne(1);
		
		// 출력
		System.out.println("*** selectOne - id가 1인 게시물 조회");
		System.out.println("   * " + boardItem.toString() + "\n");
	}
	
	
	/* 수정 - 122번글 수정 */
	@Test
	@Order(7)
	void updateOne() {
		// 수정할 데이터가 담긴 BoardItem 생성
		BoardItem boardItemUpdate = new BoardItem(122, "글22_수정", "작성자22_수정");
		
		// 글 번호로 id 조회 후 update 반복
		List<BoardItem> boardItems = boardItemMapper.selectAll(122);
		for (BoardItem boardItem : boardItems) {
			boardItemUpdate.setId(boardItem.getId());
			boardItem = boardItemUpdate;
			boardItemMapper.updateOneById(boardItem);
		}
		
		// 조회 & 출력
		List<BoardItem> boardItemSelect = boardItemMapper.selectAll(122);
		System.out.println("*** update - no 122 인 게시물 수정 후 조회");
		printList(boardItemSelect);
		System.out.println();
	}

	
	/* 수정 - 122번글 수정 */
	@Test
	@Order(8)
	void deleteOne() {
		boardItemMapper.deleteAll(122);
		
		List<BoardItem> boardItemSelect = boardItemMapper.selectAll(122);
		System.out.println("*** delete - no 122 인 게시물 삭제 후 조회");
		printList(boardItemSelect);
		System.out.println("end.\n");
	}

	
	void printList(List<BoardItem> boardItems) {
		for (BoardItem boardItem : boardItems) {
			System.out.println("   * " + boardItem.toString());
		}
	}
}
