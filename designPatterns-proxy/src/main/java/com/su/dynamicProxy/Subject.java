package com.su.dynamicProxy;

public class Subject implements ISubject{

	@Override
	public void sayHello() {
		System.out.println("Hello World");
	}

	@Override
	public void sayGoodBye() {
		System.out.println("GoodBye World");
	}

}
