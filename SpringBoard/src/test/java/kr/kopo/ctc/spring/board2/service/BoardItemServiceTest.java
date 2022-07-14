package kr.kopo.ctc.spring.board2.service;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardItemServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardItemServiceTest.class);
	@Autowired
	private BoardItemService boardItemService;

	
	/* aspect test */
	
//	@Test
//	@Order(1)
//	void test() {
//		boardItemService.test();
//	}
//	
//	@Test
//	@Order(2)
//	void testAopBefore() {
//		boardItemService.testAopBefore();
//	}
//	
//	@Test
//	@Order(3)
//	void testAopAfter() {
//		boardItemService.testAopAfter();
//	}
//	
//	@Test
//	@Order(4)
//	void testAopAfterReturning() {
//		boardItemService.testAopAfterReturning();
//	}
//	
//	@Test
//	@Order(5)
//	void testAopAfterThrowing() {
//		boardItemService.testAopAfterThrowing();
//	}
//	
//	@Test
//	@Order(6)
//	void testAopAtround() {
//		boardItemService.testAopAtround();
//	}
//	
//	@Test
//	@Order(7)
//	void testPointcutBefore() {
//		boardItemService.testPointcutBefore();
//	}
//	
//	@Test
//	@Order(8)
//	void testPointcutAfter() {
//		boardItemService.testPointcutAfter();
//	}
//	
//	@Test
//	@Order(9)
//	void testPointcutAfterReturning() {
//		boardItemService.testPointcutAfterReturning();
//	}
//	
//	@Test
//	@Order(10)
//	void testPointcutAfterThrowing() {
//		boardItemService.testPointcutAfterThrowing();
//	}
//	
//	@Test
//	@Order(11)
//	void testPointcutAround() {
//		boardItemService.testPointcutAround();
//	}
	
	/* transactional test */
	
//	@Test
//	@Order(12)
//	void testNoTransactional() {
//		boardItemService.testNoTransactional();
//	}
	
//	@Test
//	@Order(13)
//	void testTransactional() {
//		boardItemService.testTransactional();
//	}
	
	/* cache test */
	
	long startTime;
	long endTime;
	
	@Before(value = "testNoCache()")
	//@Test
	//@Order(14)
	void onBefore() {
		startTime = System.currentTimeMillis();
		System.out.println("시작시간 : " + startTime + "ms");
		logger.info("시작시간 : {}ms", startTime);
	}
	
	@Test
	@Order(15)
	void testNoCache() {
		System.out.println("\ntestNoCache 시작");
		boardItemService.testNoCache(1L);
		System.out.println("testNoCache 끝\n");
	}
	
	@Test
	@Order(16)
	void testCache1() {
		System.out.println("\ntestCache1 시작");
		boardItemService.testCache(1L);
		System.out.println("testCache1 끝\n");
	}
	
	@Test
	@Order(17)
	void testCache2() {
		System.out.println("\ntestCache2 시작");
		boardItemService.testCache(1L);
		System.out.println("testCache2 끝\n");
	}
	
	@Test
	@Order(18)
	void testCache3() {
		System.out.println("\ntestCache3 시작");
		boardItemService.testCache(2L);
		System.out.println("testCache3 끝\n");
	}
	
	@Test
	@Order(19)
	void testCache4() {
		System.out.println("\ntestCache4 시작");
		boardItemService.testCache(1L);
		System.out.println("testCache4 끝\n");
	}
	
	@Test
	@Order(20)
	void testCache5() {
		System.out.println("\ntestCache5 시작");
		boardItemService.testCacheClear(1L);
		boardItemService.testCache(1L);
		System.out.println("testCache5 끝\n");
	}
	
	@After(value = "testCache5()")
	//@Test
	//@Order(21)
	void onAfter() {
		endTime = System.currentTimeMillis();
		System.out.println("종료시간 : " + endTime + "ms");
		logger.info("종료시간 : {}ms", endTime);
		Long time = endTime - startTime;
		System.out.println("소요시간 : " + time + "ms");
		logger.info("소요시간 : {}ms", time);
	}
}
