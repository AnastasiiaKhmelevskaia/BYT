package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;

	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals(Integer.valueOf(10000), SEK100.getAmount());
		assertEquals(Integer.valueOf(1000), EUR10.getAmount());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(EUR, EUR10.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("100.00 SEK", SEK100.toString());
		assertEquals("10.00 EUR", EUR10.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals(Integer.valueOf(1500), SEK100.universalValue());
		assertEquals(Integer.valueOf(1500), EUR10.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		assertTrue(SEK100.equals(new Money(10000, SEK)));
		assertFalse(SEK100.equals(SEK200));
		Money equivalentInEUR = new Money((int) (10000 * SEK.getRate() / EUR.getRate()), EUR);
		assertTrue(SEK100.equals(equivalentInEUR));
		assertTrue(SEK100.equals(EUR10));
	}

	@Test
	public void testAdd() {
		Money sum = SEK100.add(SEK100);
		Money expectedMoney = new Money(20000, SEK);
		assertEquals(expectedMoney.getAmount(), sum.getAmount());

		Money sum2 = SEK100.add(EUR10);
		Money expectedMoney2 = new Money(20000, SEK);
		assertEquals(expectedMoney2.getAmount(), sum2.getAmount());
	}

	@Test
	public void testSub() {
		Money dif = SEK100.sub(SEK200);
		Money expectedMoney = new Money(-10000, SEK);
		assertEquals(expectedMoney.getAmount(), dif.getAmount());

		Money dif2 = SEK100.sub(EUR10);
		Money expectedMoney2 = new Money(000, SEK);
		assertEquals(expectedMoney2.getAmount(), dif2.getAmount());
	}

	@Test
	public void testIsZero() {
		assertTrue(SEK0.isZero());
		assertFalse(SEK100.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals((new Money(-10000, SEK)).getAmount(), SEK100.negate().getAmount());
	}

	@Test
	public void testCompareTo() {
		assertTrue(SEK100.compareTo(EUR10) == 0);
		assertTrue(SEK200.compareTo(EUR10) > 0);
		assertTrue(SEKn100.compareTo(SEK100) < 0);
	}
}
