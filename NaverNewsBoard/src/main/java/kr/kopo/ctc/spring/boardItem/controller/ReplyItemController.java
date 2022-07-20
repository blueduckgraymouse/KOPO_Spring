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
@RequestMapping(value = "/replyItem")
public class ReplyItemController {

	@Autowired
	ReplyItemService ReplyItemService;

	/* 댓글 */

	/* 입력 양식 호출 */
	@RequestMapping(value = "/root/insert", method = RequestMethod.GET)
	public String showRootInsertForm(Model model, @RequestParam(value = "id") int boardId) {
		model.addAttribute("boardId", boardId);
		return "replyItemInsertForm";
	}

	/* 입력 처리 */
	@RequestMapping(value = "/root/insert", method = RequestMethod.POST)
	public String insertRootItem(Model model, @ModelAttribute NewsReplyItem newsReplyItem,
			@RequestParam(value = "boardId") int boardId) {
		ReplyItemService.insertReply(newsReplyItem, boardId);
		return "redirect:/boardItem/view?id=" + boardId;
	}

	/* 수정 양식 호출 */
	@RequestMapping(value = "/root/update", method = RequestMethod.GET)
	public String showRootUpdateForm(Model model, @RequestParam(value = "id") int replyId) {
		NewsReplyItem newsReplyItem = ReplyItemService.getReply(replyId);
		model.addAttribute("newsReplyItem", newsReplyItem);
		return "replyItemUpdateForm";
	}

	/* 수정 처리 */
	@RequestMapping(value = "/root/update", method = RequestMethod.POST)
	public String updateRootItem(Model model, @ModelAttribute NewsReplyItem newsReplyItem) {
		int boardId = ReplyItemService.updateReply(newsReplyItem);
		return "redirect:/boardItem/view?id=" + boardId;
	}

	/* 삭제 처리 */
	@RequestMapping(value = "/root/delete")
	public String deleteRootItem(Model model, @RequestParam(value = "id") int replyId) {
		int boardId = ReplyItemService.deleteReply(replyId);
		return "redirect:/boardItem/view?id=" + boardId;
	}

	/* 답글 */

	/* 입력 양식 호출 */
	@RequestMapping(value = "/sub/insert", method = RequestMethod.GET)
	public String showSubInsertForm(Model model, @RequestParam(value = "id") int replyId) {
		model.addAttribute("replyId", replyId);
		return "subReplyItemInsertForm";
	}

	/* 입력 처리 */
	@RequestMapping(value = "/sub/insert", method = RequestMethod.POST)
	public String insertSubItem(Model model, @ModelAttribute NewsReplyItem newsReplyItem,
			@RequestParam(value = "rootReplyId") String rootReplyId) {
		int boardId = ReplyItemService.insertSubReply(newsReplyItem, Integer.parseInt(rootReplyId)); // 해당 답글의 게시물 번호
		return "redirect:/boardItem/view?id=" + boardId;
	}

	/* 수정 양식 호출 */
	@RequestMapping(value = "/sub/update", method = RequestMethod.GET)
	public String showSubUpdateForm(Model model, @RequestParam(value = "id") int replyId) {
		NewsReplyItem newsSubReplyItem = ReplyItemService.getReply(replyId);
		model.addAttribute("newsSubReplyItem", newsSubReplyItem);
		return "subReplyItemUpdateForm";
	}

	/* 수정 처리 */
	@RequestMapping(value = "/sub/update", method = RequestMethod.POST)
	public String updateSubItem(Model model, @ModelAttribute NewsReplyItem newsReplyItem) {
		int boardId = ReplyItemService.updateSubReply(newsReplyItem);
		return "redirect:/boardItem/view?id=" + boardId;
	}

	/* 삭제 처리 */
	@RequestMapping(value = "/sub/delete")
	public String deleteSubItem(Model model, @RequestParam(value = "id") int replyId) {
		int boardId = ReplyItemService.deleteSubReply(replyId);
		return "redirect:/boardItem/view?id=" + boardId;
	}
}
