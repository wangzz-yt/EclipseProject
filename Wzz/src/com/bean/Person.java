package com.bean;

public class Person {
	private String name;
	private int age;
	
	public String a;
	public String b; 
	public String c;
	public String d;
	
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Person() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + "]";
	}





	
	

}
