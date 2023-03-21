package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data // getter/setter/toSting annotation을 사용할 수도 있다.
public class Criteria { // 페이징 처리를 위한 객체

	private int pageNum; // 페이지 번호
	private int amount; // 한 페이지 당 데이터의 개수
	
	private String type; // 검색 조건(T, W, C: Title, Writer, Content)
	private String keyword; 
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() { // 검색 조건을 배열로 만들어 한 번에 처리
		return type == null? new String[] {}:type.split("");
	}
	
	public String getListLink() {
		// UriComponentsBuilder: 다수의 파라미터를 연결해 URL 형태로 만듦.
		// --org.springframework.web.util.UriComponentsBuilder
		// --한글 처리에 신경쓰지 않아도 된다.
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
	}
}
