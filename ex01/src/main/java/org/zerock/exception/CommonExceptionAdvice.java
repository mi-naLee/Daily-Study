package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice // 해당 객체가 스프링 controller에서 발생하는 예외를 처리함
@Log4j
public class CommonExceptionAdvice {

	@ExceptionHandler(Exception.class) // () 예외 타입을 처리
	public String except(Exception ex, Model model) {
		log.error("Exception........"+ex.getMessage());
		model.addAttribute("exception", ex);
		log.error(model);
		return "error_page";
	}
	
	// 존재하지 않는 URL을 호출했을 때 custom404(view) 호출
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		return "custom404";
	}
}
