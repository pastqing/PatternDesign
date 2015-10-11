package com.su.designPatterns;

public class DoubleLock {
	
	private static DoubleLock singleton  = null;
	private DoubleLock() {}
	public static DoubleLock getInstance() {
		
		if(singleton == null) {
			synchronized(DoubleLock.class) {
				if(singleton == null) {
					singleton = new DoubleLock();
				}
			}
		}
		return singleton;
		
	}
	
	
}
