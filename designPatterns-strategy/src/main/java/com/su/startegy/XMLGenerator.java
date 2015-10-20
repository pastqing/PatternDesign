package com.su.startegy;

import com.su.annotation.XMLType;

/**
 * A XML Factory, AND A Annotation Handler
 * @author su
 *
 */
public class XMLGenerator {

	private IProduct product = new DefaultHead();
	//singleton
	private XMLGenerator() {
		
	}
	
	public static XMLGenerator getInstance() {
		return XMLGeneratorInstance.instance;
	}
	
	private static class XMLGeneratorInstance {
		static final XMLGenerator instance = new XMLGenerator();
	}
	
	public String generate(String type) {
		
		return product.generateXML();
	}
}


