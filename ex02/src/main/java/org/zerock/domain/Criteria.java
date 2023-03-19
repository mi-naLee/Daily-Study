package org.zerock.domain;

import lombok.Data;

@Data // getter/setter/toSting annotation을 사용할 수도 있다.
public class Criteria { // 페이징 처리를 위한 객체

	private int pageNum; // 페이지 번호
	private int amount; // 한 페이지 당 데이터의 개수
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
