package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
	
	// =================== Model 객체 ====================
	// MVC Controller는 Java Beans 규칙에 맞는 객체는 View로 전달하지만
	// 기본 자료형의 경우 자동으로 전달하지 않는다 --> @ModelAttribute annotation 필요
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto: "+dto);
		log.info("page: "+page);
		return "/sample/ex04";
	}
	
	// =================== Rtn Type ====================
	@GetMapping("/ex05")
	public void ex05() { // void: 경로 자체가 URL --> servlet-context.xml suffix에 따라 ex05.jsp 이동
		log.info("/ex05...........");
	}
	
	// String
	
	@GetMapping("/ex06") // 객체 타입: json으로 출력
	public @ResponseBody SampleDTO ex06() {
		log.info("ex06..........");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		return dto;
	}
	
	@GetMapping("/ex07") // ResponseEntity type: 원하는 rtn + HttpHeaders 객체 + HTTP 헤더 msg
	public ResponseEntity<String> ex07(){
		log.info("/ex07.........");
		
		// {"name": "홍길동"}
		String msg = "{\"name\": \"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK); // json, HttpHeaders 객체, HTTP 200
	}

	// =================== Rtn Type ====================
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("exUpload..........");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			log.info("==========================");
			log.info("name:"+file.getOriginalFilename());
			log.info("size:"+file.getSize());
		});  
	}
}

