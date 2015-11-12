package Example;
/*
 * 数据接口
 */
public interface GenerateInterface<T> {
	//创建一条记录
	public void save(T data);
	//更新一条记录
	public void update(T data);
}
