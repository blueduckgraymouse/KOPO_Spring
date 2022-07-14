package kr.kopo.ctc.spring.board2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kopo.ctc.spring.board2.domain.BoardItem;
import kr.kopo.ctc.spring.board2.repository.BoardItemRepository;

@Service
public class BoardItemServiceImpl implements BoardItemService {

	/* AOP(aspect) test */
	
	@Override
	public void test() {
		System.out.println("BoardItemServiceImpl.test() 메서드 호출");
	}

	@Override
	public void testAopBefore() {
		System.out.println("BoardItemServiceImpl.testAopBefore() 메서드 호출");
		
	}

	@Override
	public void testAopAfter() {
		System.out.println("BoardItemServiceImpl.testAopAfter() 메서드 호출");
		
	}

	@Override
	public String testAopAfterReturning() {
		System.out.println("BoardItemServiceImpl.testAopAfterReturning() 메서드 호출");
		return "Success";
	}

	@Override
	public void testAopAfterThrowing() {
		System.out.println("BoardItemServiceImpl.testAopAfterThrowing() 메서드 호출");
		throw new RuntimeException("runtime excepteion 발생");
	}

	@Override
	public void testAopAtround() {
		System.out.println("BoardItemServiceImpl.testAopAtround() 메서드 호출");		
	}

	@Override
	public void testPointcutBefore() {
		System.out.println("BoardItemServiceImpl.testPointcutBefore() 메서드 호출");		
	}

	@Override
	public void testPointcutAfter() {
		System.out.println("BoardItemServiceImpl.testPointcutAfter() 메서드 호출");		
	}

	@Override
	public String testPointcutAfterReturning() {
		System.out.println("BoardItemServiceImpl.testPointcutAfterReturning() 메서드 호출");
		return "Success";
	}

	@Override
	public void testPointcutAfterThrowing() {
		System.out.println("BoardItemServiceImpl.testPointcutAfterThrowing() 메서드 호출");
		throw new RuntimeException("runtime excepteion 발생");
	}

	@Override
	public void testPointcutAround() {
		System.out.println("BoardItemServiceImpl.testPointcutAround() 메서드 호출");		
	}

	
	
	/* transactional test */
	
	@Autowired
	BoardItemRepository boardItemRepository;
	
	@Override
	public String testNoTransactional() {
		BoardItem boardItem = boardItemRepository.findById(13).get();
		boardItem.setTitle("수정 - no 트렌젝션");
		boardItemRepository.save(boardItem);
		
		throw new RuntimeException("Spring Boot No Transactional Test");
	}

	@Override
	@Transactional
	public String testTransactional() {
		BoardItem boardItem = boardItemRepository.findById(14).get();
		boardItem.setTitle("수정 - 트렌젝션");
		boardItemRepository.save(boardItem);
		
		throw new RuntimeException("Spring Boot Transactional Test");
	}
	
	
	
	/* cache test */

	@Override
	public String testNoCache(Long id) {
		String msg = "Hello, Spring Boot No cache(key:" + id +")";
		System.out.println(msg);
		sleep(3);
		return msg;
	}

	@Override
	@Cacheable(value="sampleCacheValue", key="#id")
	public String testCache(Long id) {
		String msg = "Hello, Spring Boot cache(key:" + id +")";
		System.out.println(msg);
		sleep(3);
		return msg;
	}

	@Override
	@CacheEvict(value="sampleCacheValue", key="#id")
	public void testCacheClear(Long id) {
		System.out.println("cache claer =>" + id);
		
	}
	
	private void sleep(int second) {
		try {
			Thread.sleep(second * 1000L);
		} catch (InterruptedException e) {
			throw new IllegalStateException();
		}
	}

}
