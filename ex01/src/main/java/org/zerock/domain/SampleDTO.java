package org.zerock.domain;

import lombok.Data;

@Data // getter/setter + equals(), toString 자동 생성
public class SampleDTO {

	private String name;
	private int age;
}
