
package W16_R5_Group1_Assignment1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

//test
public class BankTest {
    private Bank bank;
    Card c1;
    Card c2;
    Card c3;
    Calendar startDate;
    Calendar expirationDate;
    ArrayList<Card> CardList;

    @BeforeEach
    public void setUp() {
        startDate = new GregorianCalendar(2018, 8, 15);
        expirationDate = new GregorianCalendar(2022, 8, 15);
        c1 = new Card(12345, 12345, startDate, expirationDate, 10000, State.Free);
        c2 = new Card(22345, 22345, startDate, expirationDate, 10000, State.Free);
        c3 = new Card(32345, 32345, startDate, expirationDate, 10000, State.Free);
        CardList = new ArrayList<Card>();
        CardList.add(c1);
        CardList.add(c2);
        CardList.add(c3);
        bank = new Bank(CardList);
        // bank.setCurrentTransactionID(0);
    }

    @Test
    public void getValidCardListTest() {
        assertEquals(CardList, bank.getValidCardList());
    }

    @Test
    public void getCardTest() {
        short c1Num = (short) 12345;
        assertEquals(c1, bank.getCard(c1Num));
    }

}