package kr.kopo.ctc.spring.board2.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.kopo.ctc.spring.board2.domain.BoardGroup;
import kr.kopo.ctc.spring.board2.domain.BoardItem;
import kr.kopo.ctc.spring.board2.repository.BoardGroupRepository;
import kr.kopo.ctc.spring.board2.repository.BoardItemRepository;

@RestController
@RequestMapping(value= "/rest")
public class MvcBoardItemRestController {

	BoardItem boardItem1 = new BoardItem(1, 1, "제목1", "작성자1", new Date(), 1, new BoardGroup(1, "게시판1"));
	BoardItem boardItem2 = new BoardItem(2, 2, "제목2", "작성자2", new Date(), 2, new BoardGroup(1, "게시판1"));
	
	
	// response의 값 조작할 필요가 있을 때 ResponseEntity 사용
	@RequestMapping(value= "/boardItem1")
	public ResponseEntity<BoardItem> BoardItem1() {
		return ResponseEntity.ok(boardItem1);
	}
	
	@RequestMapping(value= "/boardItems1")
	public ResponseEntity<List<BoardItem>> BoardItems1() {
		List<BoardItem> boardItems = new ArrayList<BoardItem>();
		boardItems.add(boardItem1);
		boardItems.add(boardItem2);
		return ResponseEntity.ok(boardItems);
	}
	
	
	
	@Autowired
	BoardGroupRepository boardGroupRepository;
	@Autowired
	BoardItemRepository boardItemRepository;
	
	// 간단하게 사용
	@RequestMapping(value= "/boardItem2")
	public BoardItem BoardItem2(@RequestParam(value="id") Integer id) {
		BoardItem boardItem = boardItemRepository.findById(id).get();
		return boardItem;
	}
	
	@RequestMapping(value= "/boardItems2")
	public List<BoardItem> BoardItems2(@RequestParam(value="name") String name) {
		List<BoardGroup> boardGroups = boardGroupRepository.findAllByName(name);
		List<BoardItem> boardItems = boardItemRepository.findAllByBoardGroupId(boardGroups.get(0).getId()); // json : 자식(item domain) -> 부모(group domain)
		
		//List<BoardItem> boardItems = boardGroupRepository.findAllByName(name).get(0).getBoardItems(); // json : 부모(group domain) -> 자식(item domain)
		return boardItems;
	}
}
