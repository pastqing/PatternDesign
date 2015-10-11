package com.su.methodFactory;

import com.su.product.AnswerBody_Mobile;
import com.su.product.AnswerHead;

/**
 * 具体工厂类
 * @author su
 */
public class AnswerBody_Mobile_Factory implements ProductFactory {
	public AnswerHead createProduct() {
		return new AnswerBody_Mobile();
	}
}
