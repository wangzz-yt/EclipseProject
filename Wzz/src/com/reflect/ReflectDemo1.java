package com.reflect;

import com.bean.Person;
/**
 * ��ķ������
 * ���ۣ�
 * �����ͨ�������ַ�ʽ��ȡ��Class������ͬһ��
 * @author wlhl
 */
public class ReflectDemo1 {

	public static void main(String[] args) throws Exception {

		//1.Class.forName("ȫ����")-----�����������ļ�����ȡ�ַ���
		Class c1 = Class.forName("com.bean.Person");
		System.out.println(c1);
		//����.Class----�����ڲ�������
		Class c2 = Person.class;
		System.out.println(c2);
		//����.getClass---�����ڶ����ȡ�ֽ���
		Person p1 = new Person();
		Class c3 = p1.getClass();
		System.out.println(c3);

	}

}
