package com.su.abstractFactory;

public class Main {

	public static void main(String[] args) {
		AbstractFactory factory = new ConcreteFactory1();
		factory.createProductA().toXML();
	}

}
