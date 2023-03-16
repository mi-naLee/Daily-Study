package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

public interface BoardMapper {

	// @Select("select * from tbl_board where bno > 0")// where 조건을 줘서 PK를 이용하도록 유도 --> XML 사용
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno); // insert가 된 데이터를 조회하는 작업
	
	// 수정과 삭제는 몇 개의 데이터가 수정되었는 지를 나타내기 위해 int rtn
	public int delete(Long bno);
	
	public int update(BoardVO board);
}
