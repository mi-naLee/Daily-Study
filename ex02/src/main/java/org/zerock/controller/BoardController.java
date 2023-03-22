package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*") // 해당 URL의 처리는 이곳에서 실행
@AllArgsConstructor
public class BoardController { 

	private BoardService service; // controller는 service에 의존적이므로 생성자 자동 주입
	
	/*@GetMapping("/list")
	public void list(Model model) {
		log.info("====list===");
		model.addAttribute("list",service.getList());
	}*/
	
	// add Paging
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("====list: "+cri);
		model.addAttribute("list",service.getList(cri));
		//model.addAttribute("pageMaker", new PageDTO(cri, 123));
		
		int total = service.getTotal(cri);
		
		log.info("===total: "+total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
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
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri,
			Model model) {
		log.info("===/get or /modify ===");
		model.addAttribute("board", service.get(bno)); // 해당 게시물 전달
	}
	
	@PostMapping("/modify") // 수정은 get으로 접근하지만 실제로 post 처리
	/*public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri,
			RedirectAttributes rttr) {
		log.info("===modify: "+board);
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum()); // 현재 페이지(게시판 페이지) 
		rttr.addAttribute("amount", cri.getAmount()); // 현제 페이지의 데이터(게시글 수)
		rttr.addAttribute("type", cri.getType()); // 검색 조건의 타입(T/C/W)
		rttr.addAttribute("keyword", cri.getKeyword()); // 검색 조건의 키워드
		return "redirect:/board/list";
	}*/ //--> Criteria에서 UriComponentsBuiler를 사용하지 않았을 경우
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		log.info("===modify: "+board);
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list" + cri.getListLink(); // rttr.addAttribute를 작성하지 않아도 된다.
	}
	
	@PostMapping("/remove")
	/*public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri,
			RedirectAttributes rttr) {
		log.info("===remove: "+bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum()); // 현재 페이지(게시판 페이지) 
		rttr.addAttribute("amount", cri.getAmount()); // 현제 페이지의 데이터(게시글 수)
		rttr.addAttribute("type", cri.getType()); // 검색 조건의 타입(T/C/W)
		rttr.addAttribute("keyword", cri.getKeyword()); // 검색 조건의 키워드
		return "redirect:/board/list";
	}*/ //--> Criteria에서 UriComponentsBuiler를 사용하지 않았을 경우
	public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
		log.info("===remove: "+bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list" + cri.getListLink();
	}
}
