package com.cnkeep.test.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@Aspect
@Component
public class SysLogAspect {
	//配置切入点
	@Pointcut("@annotation(com.cnkeep.test.aop.SysLogAnnotation)")
	public void sysLogPoint(){};
	
	@Around("sysLogPoint()")
	public Object doSysLogAspect(ProceedingJoinPoint joinPoint) throws Throwable{
		//获取切入点参数列表
		Object[] args = joinPoint.getArgs(); 
		//获取切入类型，方法or类
		String kind = joinPoint.getKind(); 

		//获取签名，此处因为是方法签名，强制转换为方法签名，以便后面获取该方法上的注解
		MethodSignature  signature = (MethodSignature)joinPoint.getSignature();
		
		//获取方法签名的方法
		Method method = signature.getMethod();
		
		//获取方法签名上的自定义注解
		SysLogAnnotation annotation = method.getAnnotation(SysLogAnnotation.class);
		//获取方法上的自定义注解的属性值
		String option = annotation.option();
		
		SourceLocation sourceLocation = joinPoint.getSourceLocation();
		
		StaticPart staticPart = joinPoint.getStaticPart();
		
		//获取切入点的目标对象
		Object target = joinPoint.getTarget();
		
		for (Object object : args) {
			System.out.println(object);
		}
		return joinPoint.proceed(args);
	}
	
	@Before("sysLogPoint()")
	public void beforeSysLog(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		System.out.println(">>>>>>befor"+args);
	}
}
