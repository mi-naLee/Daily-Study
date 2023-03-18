package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*") // 해당 URL의 처리는 이곳에서 실행
@AllArgsConstructor
public class BoardController {

	private BoardService service; // controller는 service에 의존적이므로 생성자 자동 주입
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("====list===");
		model.addAttribute("list",service.getList());
	}
	
	@GetMapping("/register")
	public void register() {
		// 입력 화면(board/register)으로 이동시키는 역할 --> register.jsp 후 post로 입력 이루어지고 list.jsp rtn
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		// RedirectAttributes: 작업 후 이동하기 위해 사용.
		log.info("===register: "+board);
		service.register(board);
		rttr.addFlashAttribute("result",board.getBno());
		return "redirect:/board/list"; // 스프링 MVC가 내부적으로 response.sendRedirect() 처리
	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("===/get or /modify ===");
		model.addAttribute("board", service.get(bno)); // 해당 게시물 전달
	}
	
	@PostMapping("/modify") // 수정은 get으로 접근하지만 실제로 post 처리
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("===modify: "+board);
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("===remove: "+bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
}
