package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data // getter/setter, toString() 자동 생성
public class BoardVO { // test 중
	
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
}
