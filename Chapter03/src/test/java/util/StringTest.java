package util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StringTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void indexOfTest() {
		String str = "test?";
		assertEquals(4, str.indexOf("?"));
		assertEquals(-1, str.indexOf("!"));
	}
	
	@Test
	public void startWithTest() {
		String str = "test/index.com";
		assertEquals(true, str.startsWith("test"));
	}

}
