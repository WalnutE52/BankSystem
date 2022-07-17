package W16_R5_Group1_Assignment1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {
    private Card currentCard;
    Calendar startDate;
    Calendar expirationDate;

    @BeforeEach
    public void setUp() {
        startDate = new GregorianCalendar(2018, 8, 15);
        expirationDate = new GregorianCalendar(2022, 8, 15);
        currentCard = new Card(69696, 52564, startDate, expirationDate, 1000, State.Free);
    }

    // Positive Test
    @Test
    public void notExpiredCard() {
        assertTrue(currentCard.getExpiration());
    }

    @Test
    public void getCardNumTest() {
        assertEquals(69696, currentCard.getCardNum());
    }

    @Test
    public void getStartDateTest() {
        assertEquals(startDate, currentCard.getStartDate());
    }

    @Test
    public void getExpirationDateTest() {
        assertEquals(expirationDate, currentCard.getExpirationDate());
    }

    @Test
    public void getpinTest() {
        assertEquals(52564, currentCard.getPin());
    }

    @Test
    public void getBalanceTest() {
        assertEquals(1000, currentCard.getBalance());
    }

    @Test
    public void getStateTest() {
        assertEquals(State.Free, currentCard.getState());
    }

    @Test
    public void setStateTest() {
        Card newCard = new Card(69696, 52564, startDate, expirationDate, 1000, State.Free);
        newCard.setState(State.Block);
        assertEquals(State.Block, newCard.getState());
    }


    @Test
    public void setBalanceTest() {
        Card newCard = new Card(69696, 52564, startDate, expirationDate, 1000, State.Free);
        newCard.setBalance(500);
        assertEquals(500, newCard.getBalance());
    }

    // Negative Test
    @Test
    public void ExpiredCard() {
        Calendar expiredDate = new GregorianCalendar(2020, 8, 15);
        Card expiredCard = new Card(69696, 52564, startDate, expiredDate, 1000, State.Free);
        assertFalse(expiredCard.getExpiration());
    }

}
