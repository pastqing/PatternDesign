package com.su.staticProxy;

//静态代理, 目标类 
public class StaticProxyStudy implements IStaticProxy{
	
	public void sayHello() {
		System.out.println("Hello World");
	}
	
	public void sayGoodBye() {
		System.out.println("GoodBye World");
	}
	
}
