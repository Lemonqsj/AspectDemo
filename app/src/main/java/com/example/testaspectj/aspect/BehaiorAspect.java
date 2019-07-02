package com.example.testaspectj.aspect;

import android.os.SystemClock;
import android.util.Log;

import com.example.testaspectj.annotion.BehaviorTrace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Random;

/**
 * @Auther: Lemon
 * @Date: 2019/7/2 10:52 42
 * @Describe: the infor of the class
 */
@Aspect
public class BehaiorAspect {


    private static final String TAG =BehaiorAspect.class.getSimpleName() ;

    @Pointcut("execution(@com.example.testaspectj.annotion.BehaviorTrace * *(..))")
    public void methodAnnotionWithBehaviorTrace(){}


    @Around("methodAnnotionWithBehaviorTrace()")
    public Object waveJoinPoint(ProceedingJoinPoint joinPoint)throws Throwable{

       MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
       String className = methodSignature.getDeclaringType().getSimpleName();


        String methodName = methodSignature.getName();
        String value = methodSignature.getMethod().getAnnotation(BehaviorTrace.class).value();


        long begin =System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        SystemClock.sleep(new Random().nextInt(2000));

        long duration = System.currentTimeMillis() - begin;

        Log.d(TAG,String.format("%s功能：%s类的%s的%s的方法耗时%d",value,className,methodName,value,duration));

        return proceed;
    }
}
