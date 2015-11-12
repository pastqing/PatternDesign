package Example;
/**
 * 使用Mybatis作为持久层技术
 * @author pastqing
 * @param <T>
 */
public class MybatisORM<T> implements GenerateInterface<T> {

	@Override
	public void save(T data) {
		//mybatis save
		System.out.println("mybaits save");
	}
	@Override
	public void update(T data) {
		//mybatis update
		System.out.println("mybatis update");
	}

}
