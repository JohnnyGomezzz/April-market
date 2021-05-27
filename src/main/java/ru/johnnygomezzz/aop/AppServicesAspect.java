package ru.johnnygomezzz.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppServicesAspect {
    private long categoryServiceTotalDuration = 0;
    private long productServiceTotalDuration = 0;

    @Around("execution(public * ru.johnnygomezzz.services.CategoryService.*(..))")
    public Object categoryServiceMethodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        categoryServiceTotalDuration += duration;
        return out;
    }

    @Around("execution(public * ru.johnnygomezzz.services.ProductService.*(..))")
    public Object productServiceMethodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        productServiceTotalDuration += duration;
        return out;
    }

    public long getProductServiceTotalDuration() {
        return productServiceTotalDuration;
    }

    public long getCategoryServiceTotalDuration() {
        return categoryServiceTotalDuration;
    }
}
