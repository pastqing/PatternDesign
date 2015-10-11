package com.su.designPatterns;

public class LazySafeBad {
	private static LazySafeBad singleton = null;
	private LazySafeBad() {}
	public static synchronized LazySafeBad getInstance() {
		if(singleton == null) {
			singleton = new LazySafeBad();
		}
		return singleton;
	}
}
