package com.cnkeep.test.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <ul>
 * 	<li>
	   1.@Target,指明注解所使用的范围
	   <table border="1" cellspacing="0" cellpadding="6">
	   <tr><td>1</td><td>ElementType.CONSTRUCTOR</td><td>用于：用户构造器</td></tr>
	   <tr><td>2</td><td>ElementType.FIELD</td><td>用于：字段</td></tr>
	   <tr><td>3</td><td>ElementType.LOCAL_VARIABLE</td><td>用于：局部变量</td></tr>
	   <tr><td>4</td><td>ElementType.METHOD</td><td>用于：方法</td></tr>
	   <tr><td>5</td><td>ElementType.PACKAGE</td><td>用于：包</td></tr>
	   <tr><td>6</td><td>ElementType.PARAMETER</td><td>用于：参数</td></tr>
	   <tr><td>7</td><td>ElementType.TYPE</td><td>用于：类、接口</td></tr>
	   </table>
	 </li>
	 <li>2.@Retention,定义该注解被保留的时间长短
	 <table border="1" cellspacing="0" cellpadding="6">
	 <tr><td>1</td><td>RetentionPolicy.SOURCE</td><td>用于：用户构造器</td></tr>
	   <tr><td>2</td><td>RetentionPolicy.CLASS</td><td>用于：字段</td></tr>
	   <tr><td>3</td><td>RetentionPolicy.LOCAL_RUNTIME</td><td>用于：局部变量</td></tr>
	 </table>
　　　</li>
	 <li>3.@Documented,
	 </li>
	 <li>4.@Inherited,标记该注解是否被自动继承
	</li></ul>
 * @description 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年9月27日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SysLogAnnotation {
	String option();
}
