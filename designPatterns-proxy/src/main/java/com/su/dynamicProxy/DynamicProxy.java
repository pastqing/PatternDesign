package com.su.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler{

	private Subject subject = null;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println(proxy.getClass());
		System.out.println(method);
		System.out.println(args);
		System.out.println(proxy instanceof ISubject);
		System.out.println(method.getName());
		if(subject == null) {
			subject = new Subject();
		}
		method.invoke(subject, args);
		return proxy;
	}

}
