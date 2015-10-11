package com.su.designPatterns;

public class LazyNotSafe {
	private static LazyNotSafe singleton;
	private LazyNotSafe() {}
	public static LazyNotSafe getInstance() {
		if(singleton == null) {
			singleton = new LazyNotSafe();
		}
		return singleton;
	}
}
