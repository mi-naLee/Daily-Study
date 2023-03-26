package org.lim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice // for handle controller error
@Log4j // for log
public class HandleException {

	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		log.error("★★★★★Error★★★★★: "+ex.getMessage());
		model.addAttribute("exception",ex);
		log.error(model);
		return "exception/error_page";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		return "exception/error404";
	}
}
