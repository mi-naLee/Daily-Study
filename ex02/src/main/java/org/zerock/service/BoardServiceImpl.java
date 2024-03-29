package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service // 비즈니스 영역 객체 표시
@AllArgsConstructor // 모든 파라미터를 이용하는 생성자를 만듦
public class BoardServiceImpl implements BoardService {

	// 스프링 4.3 이상
	private BoardMapper mapper; // ServiceImpl가 정상적으로 동작하기 위해 필요
	/* --> Lombok을 이용해서 변경 가능
	  	@AllArgsConstructor 없이
	  	
	  	@Setter(onMethod_ + @Autowired)
	 	private BoardMapper mapper;
	*/

	@Override
	public void register(BoardVO board) { // int rtn으로 변경 가능
		log.info("register........."+board);
		
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) { // 조회
		log.info("get........"+bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify...."+board);
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove.........."+bno);
		return mapper.delete(bno) == 1;
	}

	/*@Override
	public List<BoardVO> getList() { // 전체 목록
		log.info("getList............");
		return mapper.getList();
	}*/
	
	// add Paging
	@Override
	public List<BoardVO> getList(Criteria cri) { // 전체 목록
		log.info("getList with criteria: "+cri);
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {

		log.info("=====get total count=====");
		return mapper.getTotalCount(cri);
	}
}
