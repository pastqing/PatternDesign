package basic;

public class ConcreteDecoratorA extends Decorator{

	public ConcreteDecoratorA(Component component) {
		
		super(component);
	}
	
	public void methodA() {
		System.out.println("method A");
	}
	public void method() {
		super.method();
		System.out.println("DecoratorA add sth");
	}
	
}
