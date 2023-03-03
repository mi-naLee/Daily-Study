package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

	@Select("SELECT sysdate FROM dual")
	public String getTime();
	
	public String getTime2(); // method 선언 처리(SQL 처리는 mapper)
}
