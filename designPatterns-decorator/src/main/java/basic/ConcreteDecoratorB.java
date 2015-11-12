package basic;

public class ConcreteDecoratorB extends Decorator{
	
	public ConcreteDecoratorB(Component component) {
		super(component);
	}
	
	public void methodB() {
		System.out.println("");
	}
	
	public void method() {
		super.method();
		System.out.println("DecoratorB add sth");
	}
}
