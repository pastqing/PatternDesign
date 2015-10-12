/**
 * 具体主题类
 * @author su
 */
import java.util.*;

public class MySubject implements Subject {

	private List<Observer> observers;
	private boolean changed;
	private String message;
	private final Object mutex = new Object();

	public MySubject() {
		observers = new ArrayList<Observer>();
		changed = false;
	}

	@Override
	public void register(Observer observer) {
		if (observer == null)
			throw new NullPointerException();
		if (!observers.contains(observer))
			observers.add(observer);
	}

	@Override
	public void remove(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		// temp list
		List<Observer> tempObservers = null;
		synchronized (mutex) {
			if (!changed)
				return;
			tempObservers = new ArrayList<>(this.observers);
			this.changed = false;
		}
		for(Observer obj : tempObservers) {
			obj.update();
		}
	}

	
	public void makeChanged(String message) {
		System.out.println("The Subject make a change: " + message);
		this.message = message;
		this.changed = true;
		notifyObservers();
	}

	@Override
	public String getMessage() {
		return this.message;
		
	}

}
