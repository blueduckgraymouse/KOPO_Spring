package kr.kopo.ctc.spring.board2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kopo.ctc.spring.board2.domain.BoardItem;
import kr.kopo.ctc.spring.board2.repository.BoardItemRepository;

//@Controller
public class BoardItemController {
	
//	@Autowired
//	private BoardItemRepository boardItemRepository;
//	
//	@RequestMapping(value = "/boardItem/list")
//	@ResponseBody
//	public List<BoardItem> list(Model model) {
//		List<BoardItem> list = boardItemRepository.findAll();
//		System.out.println(list.toString());
//		return boardItemRepository.findAll();
//	}
}
