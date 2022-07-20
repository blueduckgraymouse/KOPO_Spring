package kr.kopo.ctc.spring.boardItem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kopo.ctc.spring.boardItem.domain.NewsBoardItem;
import kr.kopo.ctc.spring.boardItem.dto.PageDto;
import kr.kopo.ctc.spring.boardItem.service.BoardItemService;

@Controller
@RequestMapping(value = "/boardItem")
public class BoardItemController {

	@Autowired
	BoardItemService boardItemService;

	/* 목록 조회 */
	@RequestMapping(value = "/list")
	public String showBoardList(Model model, 
			@RequestParam(value = "cPage", required = false, defaultValue = "0") Integer cPage,
			@RequestParam(value = "keyword", required = false) String keyword) {
		PageDto pageDto = boardItemService.getList(cPage, keyword);
		model.addAttribute("PageDto", pageDto);
		model.addAttribute("keyword", keyword);
		return "boardItemList";
	}

	/* 단일 조회 */
	@RequestMapping(value = "/view")
	public String showBoardItem(Model model, @RequestParam(value = "id") int id) {
		NewsBoardItem boardItem = boardItemService.getView(id);
		model.addAttribute("boardItem", boardItem);
		return "boardItemView";
	}

	/* 입력 양식 호출 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String showInsertForm(Model model) {
		return "boardItemInsertForm";
	}

	/* 입력 처리 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String doInsert(Model model, @ModelAttribute NewsBoardItem boardItem) {
		NewsBoardItem boardItemInserted = boardItemService.insertItem(boardItem);
		return "redirect:/boardItem/view?id=" + boardItemInserted.getId();
	}

	/* 수정 양식 호출 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String showUpdateForm(Model model, @RequestParam(value = "id") int id) {
		NewsBoardItem boardItem = boardItemService.getView(id);
		model.addAttribute("boardItem", boardItem);
		return "boardItemUpdateForm";
	}

	/* 수정 처리 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String doUpdate(Model model, @ModelAttribute NewsBoardItem boardItem) {
		NewsBoardItem boardItemUpdated = boardItemService.updateItem(boardItem);
		return "redirect:/boardItem/view?id=" + boardItemUpdated.getId();
	}

	/* 삭제 처리 */
	@RequestMapping(value = "/delete")
	public String doDelete(Model model, @RequestParam(value = "id") int id) {
		boardItemService.delete(id);
		return "redirect:/boardItem/list";
	}
}
