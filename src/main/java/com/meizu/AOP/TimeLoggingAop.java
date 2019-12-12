package com.meizu.AOP;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @author huangyongwen
 * @date 2019/12/12
 * @Description
 */
public class TimeLoggingAop implements MethodBeforeAdvice, AfterReturningAdvice {

    private long startTime = 0;

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        startTime=System.nanoTime();
    }

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        long spendTime=System.nanoTime()-startTime;
        String className = o1.getClass().getCanonicalName();
        String methodName = method.getName();
        System.out.println("执行"+className+"#"+methodName+"消耗"+new BigDecimal(spendTime).divide(new BigDecimal(1000000))+"毫秒");
    }

}
