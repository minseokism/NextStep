package test.main.calculator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.calculator.StringCaculator;

public class StringCaculatorTest {
	private StringCaculator cal;
	
	@Before
	public void setUp() throws Exception {
		cal = new StringCaculator();
	}
	
	@Test
	public void add_null_또는_빈문자() throws Exception {
		assertEquals(0,cal.add(""));
		assertEquals(0,cal.add(null));
	}
	
	@Test
	public void add_숫자하나() throws Exception {
		assertEquals(5,cal.add("5"));		
	}
	
	@Test
	public void add_쉼표구분자() throws Exception {
		assertEquals(4,cal.add("2,2"));
	}
	
	@Test
	public void add_쉼표_또는_콜론_구분자() throws Exception {
		assertEquals(6,cal.add("1:5"));	
		assertEquals(8,cal.add("1,2:5"));	
	}
	
	@Test
	public void add_custom_구분자() throws Exception {
		assertEquals(8,cal.add("//;\n1;2;5"));	
	}

	@Test(expected = RuntimeException.class)
	public void add_negative() throws Exception {
		cal.add("-1,2;5");	
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
