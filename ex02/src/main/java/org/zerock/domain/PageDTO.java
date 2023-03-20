package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total; // 전체 데이터(게시물)의 수
	private Criteria cri; // pageNum(페이지 번호) + amount(한 페이지당 게시물의 수)
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		// ceil: 반올림
		// endPage: 화면에서 보일 게시판 페이지의 끝 번호
		// ex) 현재 페이지 1이라면 0.1을 반올림 한 1*10 == 10이 화면에서 보이는 게시판 끝 번호
		//     현재 페이지 11이라면 1.1을 반올림 한 2*10 == 20이 화면에서 보이는 게시판 끝 번호
		// startPage: 항상 게시판 끝 번호에서 -9 한 수
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0)) * 10;
		this.startPage = this.endPage - 9;
		
		// realEnd: 실제 게시판 끝 번호
		// ex) 게시글의 개수가 80이라면 80/10(한 페이지당 게시글) == 8로 실제 끝 번호가 됨
		int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
		
		// 실제 끝 번호가 10,20,30보다 작다면
		// ex) realEnd == 8이고, endPage == 10일 때 실제 화면에 보일 게시판 페이지 끝 번호는 8이 되어야 함
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1; // 1보다 큰 페이지일 때 이전 존재
		this.next = this.endPage < realEnd; // 끝보다 작은 페이지일 때 다음 존재
	}
}
