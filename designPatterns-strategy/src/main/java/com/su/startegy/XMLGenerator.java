package com.su.startegy;

import java.net.URISyntaxException;

import com.su.annotation.XMLType;

/**
 * A XML Factory, AND A Annotation Handler
 * 
 * @author su
 *
 */
public class XMLGenerator {

	private IProduct product = new DefaultHead();

	public String generate(String type) {
		try {
			product = ProductFactory.getInstance().createProduct(type);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return product.generateXML();
	}

	
}
