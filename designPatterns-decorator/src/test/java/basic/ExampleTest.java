package basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Example.LogDecoratorORM;
import Example.MybatisORM;
import Example.User;

public class ExampleTest {

	@Test
	public void test() {
		
		LogDecoratorORM<User> logOrm = new LogDecoratorORM<User>(
				new MybatisORM<User>());
		User user = new User();
		logOrm.save(user);
	}
}
