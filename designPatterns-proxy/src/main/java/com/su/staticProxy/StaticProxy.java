package com.su.staticProxy;

public class StaticProxy implements IStaticProxy{

	private StaticProxyStudy sps = null;
	
	public StaticProxy() {
		
	}
	@Override
	public void sayHello() {
		System.out.println("I am a Static Proxy");
		if( sps == null ) {
			sps = new StaticProxyStudy();
		}
		sps.sayHello();
	}

	@Override
	public void sayGoodBye() {
		System.out.println("I am a Static Proxy");
		if(sps == null ) {
			sps = new StaticProxyStudy();
		}
		sps.sayGoodBye();
	}
}
