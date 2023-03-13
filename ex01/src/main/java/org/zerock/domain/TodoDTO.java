package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class TodoDTO { // yyyy-MM-dd 형식 String 파라미터를 Date로 변경하기 위해 작성(initBinder 필요)

	private String title;
	private Date dueDate;
}
