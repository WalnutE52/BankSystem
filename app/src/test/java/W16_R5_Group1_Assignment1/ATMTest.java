package W16_R5_Group1_Assignment1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

public class ATMTest {
    ATM atm;
    Card c1;
    Card c2;
    Card c3;
    Card c4;
    Card c5;
    Calendar start;
    Calendar end;
    Map<Cash, Integer> cashList;
    Cash dollor100;
    Cash dollor50;
    Cash dollor20;
    Cash dollor10;
    Cash dollor5;
    Cash dollor2;
    Cash dollor1;
    Cash dollor0_5;
    Cash dollor0_2;
    Cash dollor0_1;
    Cash dollor0_0_5;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        ArrayList<Card> validCardList = new ArrayList<>();
        validCardList.add(c5);

        Bank b = new Bank(validCardList);

        start = Calendar.getInstance();
        start.set(Calendar.YEAR, 2021);
        start.set(Calendar.MONTH, 9);
        start.set(Calendar.DATE, 15);
        end = Calendar.getInstance();
        end.set(Calendar.YEAR, 2031);
        end.set(Calendar.MONTH, 9);
        end.set(Calendar.DATE, 15);
        cashList = new HashMap<Cash, Integer>();
        dollor100 = new Note(100);
        dollor2 = new Coin(2);
        dollor5 = new Note(5);
        dollor10 = new Note(10);
        dollor20 = new Note(20);
        dollor1 = new Coin(1);
        dollor0_5 = new Coin(0.5);
        dollor0_2 = new Coin(0.2);
        dollor0_1 = new Coin(0.1);
        dollor0_0_5 = new Coin(0.05);
        dollor50 = new Note(50);
        cashList.put(dollor100, 200);
        cashList.put(dollor50, 0);
        cashList.put(dollor20, 0);
        cashList.put(dollor10, 0);
        cashList.put(dollor5, 0);
        cashList.put(dollor2, 0);
        cashList.put(dollor1, 0);
        cashList.put(dollor0_0_5, 0);
        cashList.put(dollor0_1, 0);
        cashList.put(dollor0_2, 0);
        cashList.put(dollor0_5, 0);
        c1 = new Card(10086, 1111, start, end, 100.0, State.Block);
        c2 = new Card(12345, 2222, start, end, 10000.0, State.Process);
        c3 = new Card(22222, 333, start, end, 10000.0, State.Stolen);
        c4 = new Card(33333, 44, start, end, 10000.0, State.Confiscate);
        c5 = new Card(69696, 5, start, end, 1000.0, State.Free);

        // // atm = new ATM(20000, cashList, 0,b);

        // atm = new ATM(20000, cashList, 0,1);

        atm = new ATM(20000, cashList, 0, 1);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void constructorTest() {


        // // assertNotNull(new ATM(1, null, 2,null));

        // assertNotNull(new ATM(1, null, 2,2));

        // positive test
        assertNotNull(new ATM(1, null, 2, 2));
    }

    @Test
    public void getATMidTest() {
        // positive test
        assertEquals(1, atm.getATMid());
    }

    @Test
    public void setBalanceTest() {
        // positive test
        atm.setBalance(30000);
        assertEquals(30000, atm.getBalance());
    }

    @Test
    public void setCashListTest() {
        // positive test
        Map<Cash, Integer> c_list = new HashMap<>();
        Cash d = new Note(50);
        c_list.put(d, 50);
        atm.setCashList(c_list);
        assertEquals(50, atm.getCashList().get(d));
    }

    @Test
    public void getTransactionTest() {
        // positive test

        assertEquals(0, atm.getTransaction());
    }

    @Test
    public void setTransactionTest() {
        // positive test
        atm.setTransaction(5);
        assertEquals(5, atm.getTransaction());
    }

    @Test
    public void checkDateTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        // positive test
        Class<ATM> c = ATM.class;
        Method method = c.getDeclaredMethod("checkDate", Card.class);
        method.setAccessible(true);
        assertTrue((boolean) method.invoke(atm, c5));

        // negative test
        Calendar below_start = Calendar.getInstance();
        below_start.set(Calendar.YEAR, 2028);
        below_start.set(Calendar.MONTH, 5);
        below_start.set(Calendar.DATE, 2);
        Card c20 = new Card(77777, 1314, below_start, end, 1000.0, State.Free);
        assertFalse((boolean) method.invoke(atm, c20));

        Calendar expired = Calendar.getInstance();
        expired.set(Calendar.YEAR, 2015);
        expired.set(Calendar.MONTH, 5);
        expired.set(Calendar.DATE, 2);
        Card c21 = new Card(77777, 1314, start, expired, 1000.0, State.Free);
        assertFalse((boolean) method.invoke(atm, c21));

    }

    @Test
    public void checkStateTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        // positive test
        Class<ATM> c = ATM.class;
        Method method = c.getDeclaredMethod("checkState", Card.class);
        method.setAccessible(true);
        assertTrue((boolean) method.invoke(atm, c5));

        // negative test
        assertFalse((boolean) method.invoke(atm, c3));
    }

    @Test
    public void checkNumberTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        // positive test

        Class<ATM> c = ATM.class;
        Method method = c.getDeclaredMethod("checkNumber", Card.class);
        method.setAccessible(true);
        assertTrue((boolean) method.invoke(atm, c5));

        // negative test
        Card c30 = new Card(00001, 5, start, end, 1000.0, State.Free);
        assertFalse((boolean) method.invoke(atm, c30));
    }

    @Test
    public void balanceTest() {
        atm.balance(c5);
        assertEquals(1000.0, c5.getBalance());
    }

    @Test
    public void inputCardnumTest() {
        Card c6 = new Card(10, 1111, start, end, 10000, State.Free);
        Card c7 = new Card(19827343, 1111, start, end, 10000, State.Free);
        Card c8 = new Card(null, 1111, start, end, 10000, State.Free);
        //positive test
        assertTrue(atm.inputCardnum(c5.getCardNum()));
        assertFalse(atm.inputCardnum(c6.getCardNum()));
        assertFalse(atm.inputCardnum(c7.getCardNum()));
        assertNull(c8.getCardNum());
    }

    @Test
    public void cardValidTest() {
        Card c13 = new Card(88565, 1111, start, end, 10000, State.Free);
        //positive test
        assertTrue(atm.cardValid(c5));
        assertFalse(atm.cardValid(c13));
        assertFalse(atm.cardValid(c1));
        assertFalse(atm.cardValid(c2));
        assertFalse(atm.cardValid(c3));
        assertFalse(atm.cardValid(c4));
    }

    @Test
    public void inputValidTest() {
        Card c10 = new Card(10025, 54321, start, end, 10000, State.Free);
        assertTrue(atm.inputValid(1111, c1));
        assertFalse(atm.inputValid(3333, c10));
        assertFalse(atm.inputValid(1111, c10));
        assertFalse(atm.inputValid(2222, c10));
        assertEquals(State.Free, c10.getState());
    }

    @Test
    public void confiscateTest() {
        assertEquals(State.Stolen, c3.getState());
        atm.confiscateCard(c3);
        assertEquals(State.Confiscate, c3.getState());
        assertEquals(State.Free, c5.getState());
        atm.confiscateCard(c5);
        assertEquals(State.Confiscate, c5.getState());
    }

    @Test
    public void ejectTest() {
        assertEquals(State.Process, c2.getState());

        atm.eject(c2);
        assertEquals(State.Free, c2.getState());
        assertEquals(State.Free, c5.getState());
        atm.eject(c5);
        assertEquals(State.Free, c5.getState());

    }

    @Test
    public void addCashTest() {
        //positive case
        assertEquals(20000, atm.getBalance());

        Map<Cash, Integer> addMore = new HashMap<Cash, Integer>();
        addMore.put(dollor50, 2);
        addMore.put(dollor20, 2);
        addMore.put(dollor10, 2);
        addMore.put(dollor5, 2);
        atm.addCash(addMore);
        assertEquals(20170, atm.getBalance());
        addMore.put(dollor0_5, 2);
        addMore.put(dollor0_2, 5);
        addMore.put(dollor0_1, 10);
        addMore.put(dollor0_0_5, 20);
        atm.addCash(addMore);
        //edge case
        assertEquals(20344, atm.getBalance());
        addMore.put(dollor50, 500);
        addMore.put(dollor20, 2);
        addMore.put(dollor10, 2);
        addMore.put(dollor5, 2);
        atm.addCash(addMore);
        assertEquals(20418, atm.getBalance());
        addMore.put(dollor0_5, 2);
        addMore.put(dollor0_2, 5);
        addMore.put(dollor0_1, 10);
        addMore.put(dollor0_0_5, 20);
        atm.addCash(addMore);
        assertEquals(20492, atm.getBalance());
        Map<Cash, Integer> littleadd = new HashMap<Cash, Integer>();
        littleadd.put(dollor100, 1);
        littleadd.put(dollor1, 1);
        littleadd.put(dollor2, 1);

        atm.addCash(littleadd);
        assertEquals(20595, atm.getBalance());
        //negatice case
        Map<Cash, Integer> overMore = new HashMap<Cash, Integer>();
        overMore.put(dollor50, 1);
        assertEquals(20595, atm.getBalance());
        Map<Cash, Integer> tooMore = new HashMap<Cash, Integer>();
        tooMore.put(dollor20, 500);
        tooMore.put(dollor100, 500);
        tooMore.put(dollor10, 500);
        tooMore.put(dollor5, 500);
        tooMore.put(dollor2, 500);
        tooMore.put(dollor1, 500);
        tooMore.put(dollor0_5, 500);
        tooMore.put(dollor0_2, 500);
        tooMore.put(dollor0_1, 500);
        tooMore.put(dollor0_0_5, 500);
        assertEquals(20595, atm.getBalance());
    }

    @Test
    public void getCashListTest() {
        assertEquals(200, atm.getCashList().get(dollor100));
        assertEquals(11, atm.getCashList().size());
        cashList.put(dollor50, 2);
        cashList.put(dollor20, 2);
        cashList.put(dollor10, 2);
        cashList.put(dollor5, 2);
        cashList.put(dollor0_5, 2);
        cashList.put(dollor0_2, 5);
        cashList.put(dollor0_1, 10);
        cashList.put(dollor0_0_5, 20);
        assertEquals(2, atm.getCashList().get(dollor50));
        assertEquals(2, atm.getCashList().get(dollor20));
        assertEquals(2, atm.getCashList().get(dollor10));
        assertEquals(2, atm.getCashList().get(dollor5));
        assertEquals(2, atm.getCashList().get(dollor0_5));
        assertEquals(5, atm.getCashList().get(dollor0_2));
        assertEquals(10, atm.getCashList().get(dollor0_1));
        assertEquals(20, atm.getCashList().get(dollor0_0_5));
        assertEquals(11, atm.getCashList().size());
    }

    @Test
    public void oprWithdrawNumTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        // positive test
        Class<ATM> c = ATM.class;
        Method method = c.getDeclaredMethod("oprWithdrawNum", double.class);
        method.setAccessible(true);
        cashList.put(dollor50, 2);
        cashList.put(dollor20, 2);
        cashList.put(dollor10, 2);
        cashList.put(dollor5, 2);
        cashList.put(dollor2, 2);
        cashList.put(dollor1, 2);
        cashList.put(dollor0_5, 2);
        cashList.put(dollor0_2, 2);
        cashList.put(dollor0_1, 2);
        cashList.put(dollor0_0_5, 2);
        assertTrue((boolean) method.invoke(atm, 1000));
        assertTrue((boolean) method.invoke(atm, 50));
        assertTrue((boolean) method.invoke(atm, 20));
        assertTrue((boolean) method.invoke(atm, 10));
        assertTrue((boolean) method.invoke(atm, 5));
        assertTrue((boolean) method.invoke(atm, 2));
        assertTrue((boolean) method.invoke(atm, 1));
        assertTrue((boolean) method.invoke(atm, 0.5));
        assertTrue((boolean) method.invoke(atm, 0.2));
        assertTrue((boolean) method.invoke(atm, 0.1));
        assertTrue((boolean) method.invoke(atm, 0.05));

        // negative test
        Map<Cash, Integer> cl = new HashMap<>();
        cl.put(dollor100, 1);
        ATM atm_3 = new ATM(100, cl, 0, 1);
        assertFalse((boolean) method.invoke(atm_3, 1000));
        assertFalse((boolean) method.invoke(atm_3, 50));
        assertFalse((boolean) method.invoke(atm_3, 20));
        assertFalse((boolean) method.invoke(atm_3, 10));
        assertFalse((boolean) method.invoke(atm_3, 5));
        assertFalse((boolean) method.invoke(atm_3, 2));
        assertFalse((boolean) method.invoke(atm_3, 1));
        assertFalse((boolean) method.invoke(atm_3, 0.5));
        assertFalse((boolean) method.invoke(atm_3, 0.2));
        assertFalse((boolean) method.invoke(atm_3, 0.1));
        assertFalse((boolean) method.invoke(atm_3, 0.05));
    }

    @Test
    public void subTest() {
        //positive case
        assertEquals(2, atm.sub(4, 2));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}