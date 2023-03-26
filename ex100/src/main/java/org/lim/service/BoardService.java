package org.lim.service;

import java.util.List;

import org.lim.domain.BoardVO;

public interface BoardService {

	// c
	public void register(BoardVO board);
	
	// r
	public BoardVO get(Long bno);
	
	public List<BoardVO> getList();
	
	// u
	public boolean modify(BoardVO board);
	
	// d
	public boolean remove(Long bno);
	
}
