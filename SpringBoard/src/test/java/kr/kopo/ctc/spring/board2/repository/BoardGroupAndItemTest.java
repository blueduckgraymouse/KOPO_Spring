package kr.kopo.ctc.spring.board2.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.kopo.ctc.spring.board2.domain.BoardGroup;
import kr.kopo.ctc.spring.board2.domain.BoardItem;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardGroupAndItemTest {
	@Autowired
	private BoardGroupRepository boardGroupRepository;
	@Autowired
	private BoardItemRepository BoardItemRepository;
	
//	@Test
//	@Order(1)
//	void test1() {
//		boardGroupRepository.deleteAll();
//		BoardItemRepository.deleteAll();
//		
//		BoardGroup first = new BoardGroup("first");
//		boardGroupRepository.save(first);
//		BoardGroup second = new BoardGroup("second");
//		boardGroupRepository.save(second);
//		
//		List<BoardGroup> list1 = boardGroupRepository.findAll();
//		System.out.println("group확인");
//		for (BoardGroup boardGroup : list1) {
//			System.out.println(boardGroup.toString());
//		}
//		
//		List<BoardGroup> listGroup = boardGroupRepository.findAllByName("first");
//		BoardItem boardItem1 = new BoardItem(101, "f제목1", "f작성자1", new Date(), 0, listGroup.get(0));
//		BoardItem boardItem2 = new BoardItem(102, "f제목2", "f작성자2", new Date(), 0, listGroup.get(0));
//		BoardItemRepository.save(boardItem1);
//		BoardItemRepository.save(boardItem2);
//		
//		listGroup = boardGroupRepository.findAllByName("second");
//		BoardItem boardItem3 = new BoardItem(201, "s제목1", "s작성자1", new Date(), 0, listGroup.get(0));
//		BoardItem boardItem4 = new BoardItem(202, "s제목2", "s작성자2", new Date(), 0, listGroup.get(0));
//		BoardItemRepository.save(boardItem3);
//		BoardItemRepository.save(boardItem4);
//
//		List<BoardItem> list2 = BoardItemRepository.findAll();
//		System.out.println("item확인");
//		for (BoardItem boardItem : list2) {
//			System.out.println(boardItem.toString());
//		}
//	}
	
	
	
	/* BoardItem.java에서의 @ManyToOne의 fetch=FetchType.EAGER / LAZY 실습
	 *  * fetch=FetchType.EAGER -> 즉시 패치, 관계된 domain의 필드값까지 조회해서 채움.
	 *  * fetch=FetchType.LAZY -> 패치 미룸, 해당 domain에 해당하는 필드값만 조회해서 채움. 조회 안한 필드는 null로 채운다.
	 */
//	@Test
//	@Order(2)
//	void test2() {
//		List<BoardItem> BoardItems = BoardItemRepository.findAll();
//		for (BoardItem boardItem : BoardItems) {
//			System.out.println(boardItem.toString());
//		}
//	}
	
	
	
	/* BoardItem.java에서의 @OneToMany의 fetch=FetchType.EAGER / LAZY 실습
	 *  * fetch=FetchType.EAGER -> 즉시 패치, 관계된 domain의 필드값까지 조회해서 채움.
	 *  * fetch=FetchType.LAZY -> 패치 미룸, 해당 domain에 해당하는 필드값만 조회해서 채움. 조회 안한 필드는 접근시 에러 발생.
	 */
//	@Test
//	@Order(3)
//	void test3() {
//		Optional<BoardGroup> boardGroup = boardGroupRepository.findById(4);
//		System.out.println(boardGroup);
//	}
	
	
	
	/* BoardItem.java에서의 @OneToMany의 cascade=CascadeType.ALL 실습
	 *  * 특정 레코드가 삭제 되면 연관된 테이블의 외래키 컬럼에서 그 값과 일치하는 레코드도 연쇄적으로 삭제(수정)
	 */
	@Test
	@Order(4)
	void test4() {
		List<BoardGroup> listGroup = boardGroupRepository.findAllByName("first");
		
		System.out.println("삭제 전 조회");
		List<BoardItem> BoardItemsBefore = BoardItemRepository.findAllByBoardGroupId(listGroup.get(0).getId());
		for (BoardItem boardItem : BoardItemsBefore) {
			System.out.println(boardItem.toString());
		}
		
		boardGroupRepository.delete(listGroup.get(0));
		
		System.out.println("삭제 후 조회");
		List<BoardItem> BoardItemsAfter = BoardItemRepository.findAllByBoardGroupId(listGroup.get(0).getId());
		for (BoardItem boardItem : BoardItemsAfter) {
			System.out.println(boardItem.toString());
		}
	}

}
