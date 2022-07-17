package W16_R5_Group1_Assignment1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class withdrawalTest {
    ATM atm;
    Card card;
    Cash five_cents;
    Cash ten_cents;
    Cash twenty_cents;
    Cash fifty_cents;
    Cash one_dollar;
    Cash two_dollar;
    Cash five_dollar;
    Cash ten_dollar;
    Cash twenty_dollar;
    Cash fifty_dollar;
    Cash one_hundred_dollar;

    @BeforeEach
    public void setUp() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2020, 1, 1);
        Calendar c2 = Calendar.getInstance();
        c2.set(2022, 1, 1);
        card = new Card(12345, 12345, c1, c2, 10000, State.Free);
        five_cents = new Coin(0.05);
        ten_cents = new Coin(0.1);
        twenty_cents = new Coin(0.2);
        fifty_cents = new Coin(0.5);
        one_dollar = new Coin(1);
        two_dollar = new Coin(2);
        five_dollar = new Note(5);
        ten_dollar = new Note(10);
        twenty_dollar = new Note(20);
        fifty_dollar = new Note(50);
        one_hundred_dollar = new Note(100);
        Map<Cash, Integer> cashList = new HashMap<Cash, Integer>();
        cashList.put(five_cents, 0);
        cashList.put(ten_cents, 20);
        cashList.put(twenty_cents, 20);
        cashList.put(fifty_cents, 20);
        cashList.put(one_dollar, 20);
        cashList.put(two_dollar, 20);
        cashList.put(five_dollar, 20);
        cashList.put(ten_dollar, 20);
        cashList.put(twenty_dollar, 20);
        cashList.put(fifty_dollar, 20);
        cashList.put(one_hundred_dollar, 20);
        double balance = 20 * (0.1 + 0.2 + 0.5 + 1 + 2 + 5 + 10 + 20 + 50 + 100);
        atm = new ATM(balance, cashList, 0, 5);
    }

    // Positive Test
    @Test
    public void withdraw_1000() {
        atm.withdrawal(card, 1000);
        assertEquals(10, atm.getCashList().get(one_hundred_dollar));
        assertEquals(2776, atm.getBalance());
        assertEquals(9000, card.getBalance());
    }

    @Test
    public void withdraw_500() {
        atm.withdrawal(card, 500);
        assertEquals(15, atm.getCashList().get(one_hundred_dollar));
        assertEquals(3276, atm.getBalance());
        assertEquals(9500, card.getBalance());
    }

    @Test
    public void withdraw_501() {
        atm.withdrawal(card, 501);
        assertEquals(15, atm.getCashList().get(one_hundred_dollar));
        assertEquals(19, atm.getCashList().get(one_dollar));
        assertEquals(3275, atm.getBalance());
    }

    @Test
    public void withdraw_501_5() {
        atm.withdrawal(card, 501.5);
        assertEquals(15, atm.getCashList().get(one_hundred_dollar));
        assertEquals(19, atm.getCashList().get(one_dollar));
        assertEquals(19, atm.getCashList().get(fifty_cents));
        assertEquals(3274.5, atm.getBalance());
    }

    @Test
    public void withdraw_195_8() {
        atm.withdrawal(card, 195.8);
        assertEquals(19, atm.getCashList().get(one_hundred_dollar));
        assertEquals(19, atm.getCashList().get(fifty_cents));
        assertEquals(18, atm.getCashList().get(twenty_dollar));
        assertEquals(19, atm.getCashList().get(five_dollar));
        assertEquals(19, atm.getCashList().get(fifty_cents));
        assertEquals(19, atm.getCashList().get(twenty_cents));
        assertEquals(19, atm.getCashList().get(ten_cents));
        assertEquals(3580.2, atm.getBalance());
        assertEquals(9804.2, card.getBalance());
    }

    // Negative Test

    @Test
    public void withdraw_lot_of_money() {
        boolean result = atm.withdrawal(card, 1000000);
        assertFalse(result);
    }

    @Test
    public void withdraw_atm_donot_have() {
        boolean result = atm.withdrawal(card, 0.05);
        assertFalse(result);
    }

    @Test
    public void Card_no_enough_money() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2020, 1, 1);
        Calendar c2 = Calendar.getInstance();
        c1.set(2022, 1, 1);
        Card poor_card = new Card(12345, 12345, c1, c2, 100, State.Free);
        boolean result = atm.withdrawal(poor_card, 200);
        assertFalse(result);
    }

    // Edge Test

    @Test
    public void withdraw_impossible_cash() {
        boolean result = atm.withdrawal(card, 200.0001);
        assertFalse(result);
        assertEquals(3776, atm.getBalance());
    }

    @Test
    public void withdraw_negative() {
        boolean result = atm.withdrawal(card, -200);
        assertFalse(result);
        assertEquals(3776, atm.getBalance());
    }

    @Test
    public void withdraw_negative_impossible_cash() {
        boolean result = atm.withdrawal(card, -200.001);
        assertFalse(result);
        assertEquals(3776, atm.getBalance());
    }

    @Test
    public void withdraw_null_card() {
        boolean result = atm.withdrawal(null, 200);
        assertFalse(result);
    }
}
