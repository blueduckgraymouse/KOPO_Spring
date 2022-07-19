package kr.kopo.ctc.spring.boardItem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kopo.ctc.spring.boardItem.domain.NewsReplyItem;
import kr.kopo.ctc.spring.boardItem.service.ReplyItemService;

@Controller
@RequestMapping(value="/replyItem")
public class ReplyItemController {

	@Autowired
	ReplyItemService ReplyItemService;
	
	
	/* 입력 양식 호출 */
	@RequestMapping(value="/insert", method = RequestMethod.GET)
	public String showInsertForm(Model model, @RequestParam(value = "id") int boardId) {
		model.addAttribute("boardId", boardId);
		return "replyItemInsertForm";
	}
	
	
	/* 입력 처리 */
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public String doInsert(Model model, @ModelAttribute NewsReplyItem newsReplyItem, @RequestParam(value = "boardId") int boardId) {
		ReplyItemService.insertItem(newsReplyItem, boardId);
		return "redirect:/boardItem/view?id="+ boardId;
	}
	
	
	/* 수정 양식 호출 */
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String showUpdateForm(Model model, @RequestParam(value = "id") int replyId) {
		NewsReplyItem newsReplyItem = ReplyItemService.getReply(replyId);
		model.addAttribute("newsReplyItem", newsReplyItem);
		return "replyItemUpdateForm";
	}
	
	
	/* 수정 처리 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String doUpdate(Model model, @ModelAttribute NewsReplyItem newsReplyItem) {
		int boardId = ReplyItemService.updateItem(newsReplyItem);
		return "redirect:/boardItem/view?id="+ boardId;
	}
	
	
	/* 삭제 처리 */
	@RequestMapping(value="/delete")
	public String doDelete(Model model, @RequestParam(value = "id") int replyId) {
		int boardId = ReplyItemService.delete(replyId);
		return "redirect:/boardItem/view?id="+ boardId;
	}
}
