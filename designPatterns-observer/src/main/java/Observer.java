/**
 * 观察者接口
 * @author su
 */
public interface Observer {
	//
	public void update();
	
	public void setSubject(Subject subject);
}
