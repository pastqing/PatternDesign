package com.su.abstractFactory;

import com.su.product.AnswerHead;
import com.su.product.AnswerHead2;

public interface AbstractFactory {
	public AnswerHead createProductA();
	public AnswerHead2 createProductB();
}
