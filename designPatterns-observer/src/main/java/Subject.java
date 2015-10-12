/**
 * 主题接口方法 
 * @author yadoao
 */
public interface Subject {
	
	public void register(Observer observer);
	
	public void remove(Observer observer);
	
	public void notifyObservers();
	
	public String getMessage();
}
