package Example;
/**
 * 一个装饰器,对接口中的操作进行日志记录
 * @author pastqing
 *
 * @param <T>
 */
public class LogDecoratorORM <T> implements GenerateInterface<T>{
	
	private GenerateInterface<T> generator;
	
	public LogDecoratorORM(GenerateInterface<T> generator) {
		this.generator = generator;
	}
	
	private void logSave(T data) {
		//to insert log table
		System.out.println("log save");
	}
	private void logUpdate(T data) {
		//to update log table
		System.out.println("log update");
	}
	@Override
	public void save(T data) {
		logSave(data);
		generator.save(data);
		
	}

	@Override
	public void update(T data) {
		logUpdate(data);
		generator.save(data);
	}

}
