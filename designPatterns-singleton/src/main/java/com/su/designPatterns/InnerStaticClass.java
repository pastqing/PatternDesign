package com.su.designPatterns;

public class InnerStaticClass {
	private InnerStaticClass() {}
	
	public static Singleton getInstance() {
		return Singleton.instance;
	}
	private static class Singleton {
		protected static Singleton instance = new Singleton();
	}
	
}
