package org.lim.service;

import java.util.List;

import org.lim.domain.BoardVO;
import org.lim.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import jdk.internal.org.jline.utils.Log;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;
	
	@Override
	public void register(BoardVO board) {
		Log.info("★★★★★Register...."+board);
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		Log.info("★★★★★Get...."+bno);
		return mapper.read(bno);
	}

	@Override
	public List<BoardVO> getList() {
		log.info("★★★★★Get List....");
		return mapper.getList();
	}

	@Override
	public boolean modify(BoardVO board) {
		Log.info("★★★★★Modify...."+board);
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		Log.info("★★★★★Remove...."+bno);
		return mapper.delete(bno) == 1;
	}


}
