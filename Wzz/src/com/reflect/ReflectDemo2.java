package com.reflect;

import java.lang.reflect.Field;

import com.bean.Person;
/**
 * ����   �����--��Ա����
 * @author wlhl
 */
public class ReflectDemo2 {


	public static void main(String[] args) throws Exception {

		System.out.println("-------------------------��ȡ��Ա����-------------------------------");
		//��ȡPerson��Class����--
		//getFieldsֻ�ܻ�ȡ���η�ΪPublic�ĳ�Ա����
		Class  c1 = Person.class;
		Field[] f1 = c1.getFields();
		for(Field f11: f1) {
			System.out.println(f11);
		}
		//getDeclaredFields�ȿ��Է��ʹ��е�Ҳ���Է���˽�е�
		Field[] f2 = c1.getDeclaredFields();
		for(Field f21: f2) {
			System.out.println(f21);
		}

		System.out.println("------------------------��ȡ��Ա����a��ֵ-------------------------------");
		Field a = c1.getField( "a");
		Person p1 = new Person();
		Object o1 = a.get(p1);
		System.out.println(o1);

		System.out.println("------------------------���ó�Ա����a��ֵ-------------------------------");

		a.set(p1, "����");
		System.out.println(p1);

	}

}
