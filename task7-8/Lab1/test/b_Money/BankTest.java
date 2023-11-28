package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;

	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(SEK, Nordea.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		DanskeBank.openAccount("John Doe");
		SweBank.openAccount("Ulrika"); // error
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		Money money = new Money(1000, SEK);
		SweBank.deposit("Ulrika", money);
		assertEquals(Integer.valueOf(1000), SweBank.getBalance("Ulrika"));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		Money money = new Money(500, SEK);
		SweBank.deposit("Ulrika", money);
		SweBank.withdraw("Ulrika", money);
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Ulrika"));
	}

	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Ulrika"));
	}

	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(500, SEK));
		SweBank.transfer("Ulrika", "Bob", new Money(200, SEK));
		assertEquals(Integer.valueOf(300), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(200), SweBank.getBalance("Bob"));
	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(1000, SEK));
		SweBank.addTimedPayment("Ulrika", "1", 30, 0, new Money(100, SEK), Nordea, "Bob");
		SweBank.tick(); 
		assertEquals(Integer.valueOf(900), SweBank.getBalance("Ulrika"));
	}
}
