package com.project.emp.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.project.emp.other.annotation.Process;
import com.project.emp.other.annotation.Window;

@Component
@Aspect
public class ControllerLogAop {
    
    private Logger log = LoggerFactory.getLogger(ControllerLogAop.class);
    
    @Before("execution(public * com.project.emp.controller.*Controller.*(..))")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //Window어노테이션이 붙어있는 메소드를 찾는 식
        Window window;
        Process process;
        if((window = AnnotationUtils.findAnnotation(method, Window.class))!=null) {
            if(window.value().equals("")) {
                log.debug("========================"+method.getName()+" Window 창 오픈 준비"+"========================");
            } else {
                log.debug("========================"+window.value()+" Window 창 오픈 준비"+"========================");
            }
        }
        //Process어노테이션이 붙어있는 메소드를 찾는 식
        else if((process = AnnotationUtils.findAnnotation(method, Process.class))!=null) {
            if(process.value().equals("")) {
                log.debug("========================"+method.getName()+" Process 실행"+"========================");
            } else {
                log.debug("========================"+process.value()+" Process 실행"+"========================");
            }
        }
    }
    
    @After("execution(public * com.project.emp.controller.*Controller.*(..))")
    public void after(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //Window어노테이션이 붙어있는 메소드를 찾는 식
        Window window;
        Process process;
        if((window = AnnotationUtils.findAnnotation(method, Window.class))!=null) {
            if(window.value().equals("")) {
                log.debug("========================"+method.getName()+" Window 창 오픈 완료"+"========================");
            } else {
                log.debug("========================"+window.value()+" Window 창 오픈 완료"+"========================");
            }
        }
        //Process어노테이션이 붙어있는 메소드를 찾는 식
        else if((process = AnnotationUtils.findAnnotation(method, Process.class))!=null) {
            if(process.value().equals("")) {
                log.debug("========================"+method.getName()+" Process 실행 완료"+"========================");
            } else {
                log.debug("========================"+process.value()+" Process 실행 완료"+"========================");
            }
        }
    }
    
}
