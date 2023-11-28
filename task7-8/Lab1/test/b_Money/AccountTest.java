package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;

	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}

	@Test
	public void testAddRemoveTimedPayment() {
		String paymentId = "payment1";
		testAccount.addTimedPayment(paymentId, 10, 5, new Money(500, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists(paymentId));

		testAccount.removeTimedPayment(paymentId);
		assertFalse(testAccount.timedPaymentExists(paymentId));
	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		String paymentId = "payment2";
		Money initialBalance = testAccount.getBalance();
		testAccount.addTimedPayment(paymentId, 1, 0, new Money(1000, SEK), SweBank, "Alice");

		testAccount.tick(); // Should trigger the payment
		assertEquals((initialBalance.sub(new Money(1000, SEK))).getAmount(), testAccount.getBalance().getAmount());
	}

	@Test
	public void testAddWithdraw() {
		Money amountToAdd = new Money(5000, SEK);
		Money initialBalance = testAccount.getBalance();

		testAccount.deposit(amountToAdd);
		assertEquals((initialBalance.add(amountToAdd)).getAmount(), testAccount.getBalance().getAmount());

		testAccount.withdraw(amountToAdd);
		assertEquals(initialBalance.getAmount(), testAccount.getBalance().getAmount());
	}

	@Test
	public void testGetBalance() {
		assertEquals("100000.00 SEK", testAccount.getBalance().toString());
	}
}
