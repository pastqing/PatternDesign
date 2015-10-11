package com.su.abstractFactory;

import com.su.product.AnswerBody_Electric;
import com.su.product.AnswerBody_Electric1;
import com.su.product.AnswerHead;
import com.su.product.AnswerHead2;

public class ConcreteFactory1 implements AbstractFactory{

	@Override
	public AnswerHead createProductA() {
		return new AnswerBody_Electric();
	}

	@Override
	public AnswerHead2 createProductB() {
		return new AnswerBody_Electric1();
	}

}
