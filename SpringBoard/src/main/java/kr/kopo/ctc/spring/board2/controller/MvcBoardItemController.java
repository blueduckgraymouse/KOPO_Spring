package kr.kopo.ctc.spring.board2.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kopo.ctc.spring.board2.domain.BoardItem;

@Controller
@RequestMapping(value="/boardItem")
public class MvcBoardItemController {
	
	@RequestMapping(value="/")
	public String BoardItem(Model model) {
		model.addAttribute("author", "임현덕");
		return "boardItem";
	}
	
	// 옛 방식
	@RequestMapping(value="/getParameter")
	public String getParameter(Model model, HttpServletRequest req) {
		String author = req.getParameter("author");
		model.addAttribute("author", author);
		return "boardItem";
	}
	
	// 밑에 4가지가 주로 사용된다.
	@RequestMapping(value="/requestParam")
	public String requestParam(Model model, @RequestParam(value="author") String author) {
		model.addAttribute("author", author);
		return "boardItem";
	}
	
	@RequestMapping(value="/pathVariable/{author}", produces="application/text;charset=utf-8")
	public String pathVariable(Model model, @PathVariable("author") String author) {
		model.addAttribute("author", author);
		return "boardItem";
	}
	
	@RequestMapping(value="/modelAttribute")
	public String modelAttribute(Model model, @ModelAttribute BoardItem boardItem) {
		model.addAttribute("author", boardItem.getAuthor());
		return "boardItem";
	}
	
	@RequestMapping(value="/requestBody1")
	public String requestBody1(Model model, @RequestBody Map<String, Object> obj) {
		model.addAttribute("author", obj.get("author"));
		return "boardItem";
	}
	
	
	
	@RequestMapping(value="/requestBody2")
	public String requestBody2(Model model, @RequestBody BoardItem boardItem) {
		model.addAttribute("author",boardItem.getAuthor());
		return "boardItem";
	}
}
