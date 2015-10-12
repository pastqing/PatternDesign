import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ObserverTest {

	private static MySubject writer;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		writer = new MySubject();
		SubjectManagement.getInstance().addSubject("Linus",writer);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		MyObserver reader1 = new MyObserver();
		MyObserver reader2 = new MyObserver();
		MyObserver reader3 = new MyObserver();
		
		reader1.setSubject(writer);
		reader2.setSubject(writer);
		reader3.setSubject(writer);
		reader1.subscribe("Linus");
		reader2.subscribe("Linus");
		reader3.subscribe("Linus");
		writer.makeChanged("I have a new Changed");
		
		reader1.update();
		
		
	}

}
