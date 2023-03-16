package org.zerock.mapper;

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
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
	// insert test: bno == null
	/*@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("16MAR23 작성 제목");
		board.setContent("16MAR23 작성 글");
		board.setWriter("16MAR23");
		
		mapper.insert(board);
		
		log.info(board);
	}*/
	
	// insert selectKey
	/*@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("16MAR23 select key 제목");
		board.setContent("16MAR23 select key 내용");
		board.setWriter("16MAR23 select key");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
	}*/
	
	@Test // 현재 존재하는 bno 값을 호출
	public void testRead() {
		BoardVO board = mapper.read(182L);
		
		log.info(board);
	}
	
	// delete: 몇 개가 수정되었는 지 int rtn
	/*@Test
	public void testDelete() {
		log.info("DELETE COUNT: "+mapper.delete(161L));
	}*/
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		
		board.setBno(182L);
		board.setTitle("3월 16일 수정 제목");
		board.setContent("3월 16일 수정 내용");
		board.setWriter("3월 16일");
		
		int count = mapper.update(board);
		log.info("UPDATE COUNT: "+count);
	}
	
}
