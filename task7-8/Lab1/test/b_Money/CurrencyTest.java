package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;

	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}

	@Test
	public void testGetRate() {
		assertEquals(0.15, SEK.getRate(), 0.001);
		assertEquals(0.20, DKK.getRate(), 0.001);
		assertEquals(1.5, EUR.getRate(), 0.001);
	}

	@Test
	public void testSetRate() {
		SEK.setRate(0.12);
		assertEquals(0.12, SEK.getRate(), 0.001);

		DKK.setRate(0.14);
		assertEquals(0.14, DKK.getRate(), 0.001);
	}

	@Test
	public void testGlobalValue() {
		assertEquals(Integer.valueOf(150), SEK.universalValue(1000));
		assertEquals(Integer.valueOf(200), DKK.universalValue(1000));

	}

	@Test
	public void testValueInThisCurrency() {
		assertEquals(Integer.valueOf(10), SEK.valueInThisCurrency(100, EUR));
		assertEquals(Integer.valueOf(100), EUR.valueInThisCurrency(10, SEK));
	}
}
