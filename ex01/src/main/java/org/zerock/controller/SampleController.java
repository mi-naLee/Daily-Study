package org.zerock.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;
import org.zerock.domain.TodoDTO_DateTimeFormat;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j // pom.xml <scope>runtime 주석 처리 필요
public class SampleController {
	
	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}*/

	@RequestMapping("")
	public void basic() {
		log.info("basic.............");
	}
	
	@RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get.............");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get.............");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(""+dto);
		return "ex01";
	}
	
	@GetMapping("/ex02") // @RequestParam: 파라미터 변수의 이름과 전달되는 파라미터의 이름이 다를 경우 사용
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name: "+name);
		log.info("age: "+age);
		return "ex02";
	}
	
	// =================== 파라미터 여러개 전달 ====================
	@GetMapping("/ex02List") // ArrayList<>
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
		log.info("ids: "+ids);
		return "ex02List";
	}
	
	@GetMapping("/ex02Array") // String 배열
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.info("array ids: "+Arrays.toString(ids));
		return "ex02Array";
	}
	
	@GetMapping("/ex02Bean") // 객체 리스트
	/* 
		URL 형식은 list[index].속성=???
		--tomcat version에 따라 []를 인식하지 못할 경우 [ == %5B, ] == %5D
		http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=aa&list%5B1%5D.name=bb&list%5B2%5D.name=cc
	*/
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: "+list);
		return "ex02Bean";
	}
	
	// =================== InitBinder ====================
	@GetMapping("/ex03") //http://localhost:8080/sample/ex03?title=test&dueDate=2023-03-13
	public String ex03(TodoDTO_DateTimeFormat todo) {
		log.info("todo: "+todo);
		return "ex03";
	}
	
	
}
