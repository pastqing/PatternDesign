package com.su.methodFactory;

import com.su.product.AnswerHead;

public class Main {
	public static void main(String[] args) {
		ProductFactory factory = new AnswerBody_Mobile_Factory();
		AnswerHead am =  factory.createProduct();
		am.toXML();
	}
}
