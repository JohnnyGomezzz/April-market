package ru.johnnygomezzz.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Aspect
@Component
public class AppServicesAspect {
    private HashMap<String, Long> classDuration = new HashMap<>();

    @Around("execution(public * ru.johnnygomezzz.services.*.*(..))")
    public Object serviceMethodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        if (classDuration.containsKey(className)) {
            duration += classDuration.get(className);
        }
        classDuration.put(className, duration);
        return out;
    }

    public HashMap<String, Long> getClassDuration() {
        return classDuration;
    }
}
