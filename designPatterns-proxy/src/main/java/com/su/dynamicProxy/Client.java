package com.su.dynamicProxy;

import java.lang.reflect.Proxy;

public class Client {

	public static void main(String[] args) {
		DynamicProxy dp = new DynamicProxy();
		ISubject obj = (ISubject)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				Subject.class.getInterfaces(), dp);
		obj.sayHello();
		obj.sayGoodBye();
	}
}
