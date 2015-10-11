package com.su.designPatterns;

public class DoubleLockSync {
	private volatile static DoubleLockSync singleton = null;
	private DoubleLockSync() {}
	public static DoubleLockSync getInstance() {
		if(singleton == null) {
			synchronized(DoubleLockSync.class) {
				if(singleton == null) {
					singleton = new DoubleLockSync();
				}
			}
		}
		return singleton;
		
	}
}
