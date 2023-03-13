package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO_DateTimeFormat { // yyyy/MM/dd 형식 String 파라미터를 Date로 변경하기 위해 작성

	private String title;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date dueDate;
}
