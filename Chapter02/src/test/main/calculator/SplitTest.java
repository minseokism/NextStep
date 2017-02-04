package test.main.calculator;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SplitTest {

	@Test
	public void test() {
		assertArrayEquals(new String[] {"1"}, "1".split(","));
		assertArrayEquals(new String[] {"1","2"}, "1,2".split(","));
	}

}
