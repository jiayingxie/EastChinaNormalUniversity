import static org.junit.Assert.*;

import org.junit.Test;

public class DateUtilTest {
	@Test
	public void test() {
		DateUtil du = new DateUtil();		
		// -100, 1000, 20000, 2020, 2019, 2000, 1900.
		assertEquals(false, du.isLeapYear(-100));
		assertEquals(false, du.isLeapYear(1000));
		assertEquals(false, du.isLeapYear(20000));
		assertEquals(true, du.isLeapYear(2020));
		assertEquals(false, du.isLeapYear(2019));
		assertEquals(true, du.isLeapYear(2000));
		assertEquals(false, du.isLeapYear(1900));
	}
}
