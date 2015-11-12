package Example;
/**
 * 使用JDBC
 * @author pastqing
 *
 * @param <T>
 */
public class JdbcORM<T> implements GenerateInterface<T> {

	@Override
	public void save(T data) {
		//insert data into table with jdbc
		System.out.println("jdbc save");
	}

	@Override
	public void update(T data) {
		//update data to table with jdbc
		System.out.println("jdbc update");
	}

}
