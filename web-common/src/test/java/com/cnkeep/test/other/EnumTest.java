package com.cnkeep.test.other;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @description 枚举类型的测试，利用反射，class.getEnumContansts()获取枚举类型列表 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年9月27日
 */
public class EnumTest {
	public static void main(String[] args) {
		Sex[] enumConstants = Sex.class.getEnumConstants();
		for (Sex sex : enumConstants) {
			System.out.println(sex.toString());
		}
		Field[] fields = Sex.class.getFields();
		for (Field field : fields) {
			Autowired annotation = field.getAnnotation(Autowired.class);
			System.out.println(annotation.required());
			System.out.println(field);
		}
	}
}
enum Sex{
	@Autowired(required=false)
	MAN(0),
	WOMEN(1);
	private int index;
	private Sex(int index){
		this.index = index;
	}
	public int getIndex() {
		return index;
	}
	
	public Sex getType(int index){
		for(Sex type:Sex.values()){
			if(type.getIndex() == index){
				return type;
			}
		}
		return MAN;
	}
}