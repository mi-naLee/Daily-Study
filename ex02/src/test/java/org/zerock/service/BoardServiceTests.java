package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	// register test
	/*@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("16일 일기");
		board.setContent("카드 잘못 구입함");
		board.setWriter("작성자");
		
		service.register(board);
		log.info("생성된 게시물 번호: "+board.getBno());
	}*/
	
	@Test
	public void testGetList() {
		service.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void testGet() {
		log.info(service.get(182L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = service.get(182L);
		
		if(board == null) {
			return;
		}
		
		board.setWriter("작성자");
		log.info("MODIFY RESULT: "+service.modify(board));
	}
	
	@Test
	public void testDelete() {
		log.info("REMOVE RESULT: "+service.remove(201L));
	}
}
