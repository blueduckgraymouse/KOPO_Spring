package kr.kopo.ctc.spring.board2.repository;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import kr.kopo.ctc.spring.board2.domain.BoardGroup;
import kr.kopo.ctc.spring.board2.domain.BoardItem;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardGroupAndItemTest2 {
	@Autowired
	private BoardGroupRepository boardGroupRepository;
	
	@Test
	@Order(1)
	void oneToMany_TwoWay() {
		BoardGroup first = new BoardGroup("first");
		first.addBoardItems(new BoardItem(101, "f제목1", "f작성자1", new Date(), 0));
		first.addBoardItems(new BoardItem(102, "f제목2", "f작성자2", new Date(), 0));

		for (BoardItem boardItem :first.getBoardItems()) {
			boardItem.setBoardGroup(first);
		}
		
		BoardGroup second = new BoardGroup("second");
		second.addBoardItems(new BoardItem(201, "s제목1", "s작성자1", new Date(), 0));
		second.addBoardItems(new BoardItem(202, "s제목1", "s작성자2", new Date(), 0));
		
		for (BoardItem boardItem :second.getBoardItems()) {
			boardItem.setBoardGroup(second);
		}
		
		boardGroupRepository.save(first);
		boardGroupRepository.save(second);
		
		
		List<BoardGroup> list = boardGroupRepository.findAll();
		
		for (BoardGroup boardGroup : list) {
			System.out.println(boardGroup.toString());
		}
		
		//boardGroupRepository.deleteAll();
	}

}
