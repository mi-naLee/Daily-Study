package org.zerock.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration // test for controller: Servlet의 ServletContext 이용
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {

	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc; // 가짜 MVC: 가짜로 URL과 파라미터 등을 브라우저에서 사용하는 것처럼 만들어 Controller 실행ㅅ
	
	@Before // JUnit 이용
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception{
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
	
	@Test
	public void testListPaging() throws Exception{
		log.info(mockMvc.perform(
				MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "2")
				.param("amount", "10")
				).andReturn().getModelAndView().getModelMap());
	}
	
	// register test
	/*@Test
	public void testRegister() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "17MAR23 제목")
				.param("content", "17MAR23 내용")
				.param("writer", "17MAR23")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}*/
	
	@Test
	public void testGet() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
				.param("bno", "221")) // get은 특정 게시물 조회를 위해 bno가 필요ㅅ
				.andReturn()
				.getModelAndView().getModelMap());
	}
	
	// modify test
	/*@Test
	public void testModify() throws Exception{
		
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "141")
				.param("title", "141번 제목")
				.param("content", "141번 내용")
				.param("writer", "141번 작성자"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}*/
	
	// remove test
	/*@Test
	public void testRemove() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "141") // mockMvc 파라미터 전달은 문자열만 가능
				).andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}*/
}
