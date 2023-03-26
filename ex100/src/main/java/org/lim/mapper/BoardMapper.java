package org.lim.mapper;

import java.util.List;

import org.lim.domain.BoardVO;

public interface BoardMapper {

	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long no);
	
	public List<BoardVO> getList();
	
	public int update(BoardVO board);
	
	public int delete(Long bno);
}
