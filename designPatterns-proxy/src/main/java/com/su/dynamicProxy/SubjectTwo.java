package com.su.dynamicProxy;

public class SubjectTwo implements ISubject{

	@Override
	public void sayHello() {
		System.out.println("Hello subjet two");
	}
	
	@Override
	public void sayGoodBye() {
		System.out.println("GoodBye subject two");
	}
}
