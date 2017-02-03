package test.main.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.calculator.Caculator;

public class CaculatorTest {
	private Caculator calculator;
	
	@Before
	public void setUp() {
		calculator = new Caculator();
		System.out.println("before");
	}
	
	@Test
	public void add() {
		assertEquals(9 , calculator.add(3, 6));
	}
	
	@Test
	public void subtract() {
		assertEquals(5 , calculator.subtract(8, 3));
	}
	
	@After
	public void teardown() {
		System.out.println("teardown");
	}

}
