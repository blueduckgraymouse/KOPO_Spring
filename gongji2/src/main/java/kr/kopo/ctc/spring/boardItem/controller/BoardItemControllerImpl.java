package kr.kopo.ctc.spring.boardItem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kopo.ctc.spring.boardItem.domain.ReplyBoardItem;
import kr.kopo.ctc.spring.boardItem.dto.PageDto;
import kr.kopo.ctc.spring.boardItem.service.BoardItemServiceImpl;


@Controller
@RequestMapping(value="/boardItem")
public class BoardItemControllerImpl {
	
	@Autowired
	BoardItemServiceImpl boardItemService;

	@RequestMapping(value="/list")
	public String showBoardList(Model model, @RequestParam(value = "cPage", required = false) String cPage) {
		PageDto pageDto = boardItemService.getList(cPage);
		model.addAttribute("PageDto", pageDto);
		return "boardItemList";
	}
//	@RequestMapping(value="/list")
//	public String showBoardList(Model model, @RequestParam(value = "cPage", required = false) Integer cPage) {
//		PageDto pageDto = gongjiService.getList(cPage);
//		model.addAttribute("PageDto", pageDto);
//		return "boardItemList";
//	}
	
	@RequestMapping(value="/view")
	public String showBoardItem(Model model, @RequestParam(value = "id") int id) {
		ReplyBoardItem boardItem = boardItemService.getView(id);
		model.addAttribute("boardItem", boardItem);
		return "boardItemView";
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.GET)
	public String showInsertForm(Model model) {
		return "boardItemInsertForm";
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public String doInsert(Model model, @ModelAttribute ReplyBoardItem boardItem) {
		ReplyBoardItem boardItemInserted = boardItemService.insertItem(boardItem);
		return "redirect:/boardItem/view?id="+boardItemInserted.getId();
	}
	
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String showUpdateForm(Model model, @RequestParam(value = "id") int id) {
		ReplyBoardItem boardItem = boardItemService.getView(id);
		model.addAttribute("boardItem", boardItem);
		return "boardItemUpdateForm";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String doUpdate(Model model, @ModelAttribute ReplyBoardItem boardItem) {
		ReplyBoardItem boardItemUpdated = boardItemService.updateItem(boardItem);
		return "redirect:/boardItem/view?id="+boardItemUpdated.getId();
	}
	
	@RequestMapping(value="/delete")
	public String doDelete(Model model, @RequestParam(value = "id") int id) {
		boardItemService.delete(id);
		return "redirect:/boardItem/list";
	}
	
	@RequestMapping(value="/insertReply", method = RequestMethod.GET)
	public String showInsertReplyForm(Model model, @RequestParam(value = "id") int id) {
		ReplyBoardItem boardItem = boardItemService.getView(id);
		model.addAttribute("boardItem", boardItem);
		return "boardItemInsertReplyForm";
	}
	
	@RequestMapping(value="/insertReply", method = RequestMethod.POST)
	public String insertReply(Model model,
			@RequestParam(value = "id") int id, 
			@RequestParam(value = "title") String title, 
			@RequestParam(value = "content") String content,
			@RequestParam(value = "rootid") int rootid) {
		ReplyBoardItem boardItemInserted = boardItemService.insertReply(id, title, content, rootid);
		return "redirect:/boardItem/view?id="+boardItemInserted.getId();
	}
}
