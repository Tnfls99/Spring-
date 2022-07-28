package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// Component로 해도 좋지만 이 로직은 특별한 로직이므로 Bean으로 config에 직접 둥록하는 것이 좋다.
@Aspect
@Component
public class TimeTraceApp {

    @Around("execution(* hello.hellospring..*(..))") // 이 패키지 하위에는 다 적용하라는 의미
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
