package com.su.staticProxy;

public class Client {
	
	public static void main(String[] args) {
		StaticProxy sp = new StaticProxy();
		sp.sayHello();
		sp.sayGoodBye();
	}
}
