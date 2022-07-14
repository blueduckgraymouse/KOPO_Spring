package kr.kopo.ctc.spring.board2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
	// 시작 전
	@Before("execution(* kr.kopo.ctc.spring.board2.service.*.*AopBefore(..))")
	public void onBeforeHander() {
		System.out.println("LogAspect.onBeforeHandler() 핸들러 호출");
	}
	
	// 종료 후
	@After("execution(* kr.kopo.ctc.spring.board2.service.*.*AopAfter(..))")
	public void onAfterHander() {
		System.out.println("LogAspect.onAfterHander() 핸들러 호출");
	}
	
	// 정상 실행 후
	@AfterReturning(value="execution(* kr.kopo.ctc.spring.board2.service.*.*AopAfterReturning(..))", returning="returnValue")
	public void onAfterReturningHander(Object returnValue) {
		System.out.println("LogAspect.onAfterReturningHander() 핸들러 호출");
		System.out.println("ReturnValue: " + returnValue);
	}
	
	// 예외 발생 후
	@AfterThrowing(value="execution(* kr.kopo.ctc.spring.board2.service.*.*AopAfterThrowing(..))", throwing="exception")
	public void onAfterThrowingHander(Exception exception) {
		System.out.println("LogAspect.onAfterThrowingHander() 핸들러 호출");
		System.out.println("Exception: " + exception.getMessage());
	}
	
	// 이전과 이후 모두 처리가 필요할 때
	@Around("execution(* kr.kopo.ctc.spring.board2.service.*.*AopAround(..))")
	public void onAroundHander(ProceedingJoinPoint pjp) {
		System.out.println("LogAspect.onAroundHander() 핸들러 호출, 시작점");
		try {
			pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("LogAspect.onAroundHander() 핸들러 호출, 끝점");
	}
}
