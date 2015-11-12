package basic;

public abstract class Decorator implements Component{
	
	protected Component component;
	
	public Decorator(Component component) {
		this.component = component;
	}
	
	public void method() {
		component.method();
	}
}
