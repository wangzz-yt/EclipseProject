package com.reflect;

import java.lang.reflect.Field;

import com.bean.Person;
/**
 * 反射   类对象--成员变量
 * @author wlhl
 */
public class ReflectDemo2 {


	public static void main(String[] args) throws Exception {

		System.out.println("-------------------------获取成员变量-------------------------------");
		//获取Person的Class对象--
		//getFields只能获取修饰符为Public的成员变量
		Class  c1 = Person.class;
		Field[] f1 = c1.getFields();
		for(Field f11: f1) {
			System.out.println(f11);
		}
		//getDeclaredFields既可以访问共有的也可以访问私有的
		Field[] f2 = c1.getDeclaredFields();
		for(Field f21: f2) {
			System.out.println(f21);
		}

		System.out.println("------------------------获取成员变量a的值-------------------------------");
		Field a = c1.getField( "a");
		Person p1 = new Person();
		Object o1 = a.get(p1);
		System.out.println(o1);

		System.out.println("------------------------设置成员变量a的值-------------------------------");

		a.set(p1, "张三");
		System.out.println(p1);

	}

}
