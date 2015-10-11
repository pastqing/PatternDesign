package com.su.designPatterns;

public class Hunger {
	private static final Hunger singleton = new Hunger();
	private Hunger() {}
	public static Hunger getInstance(){
		return singleton;
	}
	
	
}
