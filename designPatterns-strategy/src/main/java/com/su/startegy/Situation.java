package com.su.startegy;

public class Situation {
	
	Strategy strategy;
	
	public Situation(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void handleAlgorithm() {
		strategy.algorithmStartegy();
	}
}
