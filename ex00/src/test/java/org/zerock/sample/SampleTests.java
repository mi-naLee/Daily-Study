package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) // 현재 테스트 코드가 스프링을 실행하는 역할
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// 지정된 클래스나 문자열을 이용해 필요한 객체들을 스프링 내에 객체로 등록
// root-context.xml의 경로 지정
@Log4j // Lombok을 이용해 로그를 기록하는 Logger 객체를 선언 없이 바로 사용
public class SampleTests {

	@Setter(onMethod_ = {@Autowired}) // note the underscore after onMethod(JDK8)
	private Restaurant restaurant;
	
	@Test // JUnit에서 테스트 대상 표시
	public void testExist() {
		assertNotNull(restaurant); // restaurant 변수가 null이 아니어야 테스트 성공
		
		log.info(restaurant);
		log.info("---------------------");
		log.info(restaurant.getChef());
	}
}
