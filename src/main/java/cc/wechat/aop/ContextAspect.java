package cc.wechat.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ContextAspect {
	
	private static final Logger log = LoggerFactory.getLogger(ContextAspect.class);

    @Around("execution(@cc.wechat.aop.Contextual * *.*(..)) && @annotation(ann)")
    public Object process(ProceedingJoinPoint joinPoint, Contextual ann) throws Throwable {
        

        // all method parameters
        final Object[] args = joinPoint.getArgs();
        // method information
        final Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        log.info("@Contextual Annotation method = {}", joinPoint.getSignature());
        
        // do stuff write context value
        
        // TODO ...
        
        // display result of a method processing
        final Object result = joinPoint.proceed(args);
        log.info("@Contextual Annotation method {} returned a result = {}", joinPoint.getSignature(), result);

        // do not override method return type
        return joinPoint.proceed(args);
    }
	
}
