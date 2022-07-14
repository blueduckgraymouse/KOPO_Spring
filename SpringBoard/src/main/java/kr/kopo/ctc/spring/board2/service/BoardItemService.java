package kr.kopo.ctc.spring.board2.service;

public interface BoardItemService {
	// aop test
	void test();
	void testAopBefore();
	void testAopAfter();
	String testAopAfterReturning();
	void testAopAfterThrowing();
	void testAopAtround();
	void testPointcutBefore();
	void testPointcutAfter();
	String testPointcutAfterReturning();
	void testPointcutAfterThrowing();
	void testPointcutAround();
	
	// transaction test
	String  testNoTransactional();
	String  testTransactional();
	
	// cache test
	String testNoCache(Long id);
	String testCache(Long id);
	void testCacheClear(Long id);
}
