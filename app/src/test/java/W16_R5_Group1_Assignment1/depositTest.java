package W16_R5_Group1_Assignment1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class depositTest {

    private ATM atm;
    private Card card;
    Cash five_cents = new Coin(0.05);
    Cash ten_cents = new Coin(0.1);
    Cash twenty_cents = new Coin(0.2);
    Cash fifty_cents = new Coin(0.5);
    Cash one_dollar = new Coin(1);
    Cash two_dollar = new Coin(2);
    Cash five_dollar = new Note(5);
    Cash ten_dollar = new Note(10);
    Cash twenty_dollar = new Note(20);
    Cash fifty_dollar = new Note(50);
    Cash one_hundred_dollar = new Note(100);

    @BeforeEach
    public void setUp() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2020, 1, 1);
        Calendar c2 = Calendar.getInstance();
        c1.set(2022, 1, 1);
        card = new Card(12345, 12345, c1, c2, 10000, State.Free);
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
        atm = new ATM(balance, cashList, 0, 1);
    }

    // Positive Test
    @Test
    public void deposit_1000_dollar() {
        Map<Note, Integer> notes = new HashMap<Note, Integer>();
        Note note_one_hundred_dollar = (Note) one_hundred_dollar;
        notes.put(note_one_hundred_dollar, 10);
        atm.deposit(card, notes);
        assertEquals(4776, atm.getBalance());
        // assertEquals(11000,card.getBalance());
    }

    @Test
    public void deposit_100_dollar() {
        Map<Note, Integer> notes = new HashMap<Note, Integer>();
        Note note_ten_dollar = (Note) ten_dollar;
        notes.put(note_ten_dollar, 10);
        atm.deposit(card, notes);
        assertEquals(3876, atm.getBalance());
        assertEquals(10100, card.getBalance());
    }

    // Negative Test
    @Test
    public void deposite_negative() {
        Map<Note, Integer> notes = new HashMap<Note, Integer>();
        Note note_ten_dollar = (Note) ten_dollar;
        notes.put(note_ten_dollar, -10);
        boolean result = atm.deposit(card, notes);
        assertFalse(result);
    }

    // Edge test
    @Test
    public void more_than_limit() {
        Map<Note, Integer> notes = new HashMap<Note, Integer>();
        Note note_ten_dollar = (Note) ten_dollar;
        notes.put(note_ten_dollar, 501);
        boolean result = atm.deposit(card, notes);
        assertFalse(result);
    }
}