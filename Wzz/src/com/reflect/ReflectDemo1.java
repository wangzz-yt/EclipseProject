package com.reflect;

import com.bean.Person;
/**
 * 类的反射机制
 * 结论：
 * 类对象通过这三种方式获取的Class对象是同一个
 * @author wlhl
 */
public class ReflectDemo1 {

	public static void main(String[] args) throws Exception {

		//1.Class.forName("全类名")-----多用于配置文件，读取字符串
		Class c1 = Class.forName("com.bean.Person");
		System.out.println(c1);
		//类名.Class----多用于参数传递
		Class c2 = Person.class;
		System.out.println(c2);
		//对象.getClass---多用于对象获取字节码
		Person p1 = new Person();
		Class c3 = p1.getClass();
		System.out.println(c3);

	}

}
