package basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BasicDecoratorTest {

	@Test
	public void test() {
		System.out.println("Just component object");
		ConcreteComponent component = new ConcreteComponent();
		component.method();
		
		//decorate with a
		System.out.println("component object decorate with A");
		ConcreteDecoratorA decoratorA = new ConcreteDecoratorA(component);
		decoratorA.method();
		// decorate with b
		System.out.println("component object decorate with B");
		ConcreteDecoratorB decoratorB = new ConcreteDecoratorB(component);
		decoratorB.method();
		//first B, second A
		System.out.println("component object decorate with B and A");
		ConcreteDecoratorA decorator = new ConcreteDecoratorA(new ConcreteDecoratorB(component));
		decorator.method();
	}

}
