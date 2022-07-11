package kr.kopo.ctc.spring.board2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping(value="/hello")
	public String helloSpringBoot(Model model) {
		model.addAttribute("name", "임현덕");
		return "hello";
	}
}
