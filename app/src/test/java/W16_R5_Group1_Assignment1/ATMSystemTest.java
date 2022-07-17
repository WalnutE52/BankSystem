package W16_R5_Group1_Assignment1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ATMSystemTest {
    PrintStream console = null;
    ByteArrayOutputStream bytes = null;

    @BeforeEach
    public void Before() throws Exception {
        bytes = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(bytes));

    }

    @AfterEach
    public void after() throws Exception {
        System.setOut(console);
    }

    @Test
    void test_exit_system1() {
        String s = "exit";
        ByteArrayInputStream strIn = new ByteArrayInputStream(s.getBytes());
        System.setIn(strIn);
        ATMSystem sys = new ATMSystem();
        sys.run("src/main/resources/test_cardlist.txt", "src/main/resources/test_atm.txt");
        String result = "What's your card number";
        assertEquals(result, bytes.toString().trim());
    }

    @Test
    void test_exit_system2() {
        String s = "12345\r\nexit";
        ByteArrayInputStream strIn = new ByteArrayInputStream(s.getBytes());
        System.setIn(strIn);
        ATMSystem sys = new ATMSystem();
        sys.run("src/main/resources/test_cardlist.txt", "src/main/resources/test_atm.txt");
        String result = "What's your card numberNo such card!What's your card number";
        String value = bytes.toString().trim();
        value = value.replaceAll("\r|\n", "");
        assertEquals(result, value);
    }

    @Test
    void test_input_wrong_pin() {
        String s = "11111\r\n852\r\nfdsafds\r\n1234\r\nfdsafd\r\nexit";
        ByteArrayInputStream strIn = new ByteArrayInputStream(s.getBytes());
        System.setIn(strIn);
        ATMSystem sys = new ATMSystem();
        sys.run("src/main/resources/test_cardlist.txt", "src/main/resources/test_atm.txt");
        String result = "What's your card numberPlease enter your pin.invaild inputPlease enter your pin.invaild inputPlease enter your pin.Choose the service you need, 1 for withdrawal, 2 for deposit, 3 for balance check.Wrong input.What's your card number";
        reset();
        String value = bytes.toString().trim();
        value = value.replaceAll("\r|\n", "");
        assertEquals(result, value);

    }

    @Test
    void test_card_with_diffirent_state() {
        String s = "22222\r\n33333\r\n44444\r\n55555\r\n66666\r\nexit";
        ByteArrayInputStream strIn = new ByteArrayInputStream(s.getBytes());
        System.setIn(strIn);
        ATMSystem sys = new ATMSystem();
        sys.run("src/main/resources/test_cardlist.txt", "src/main/resources/test_atm.txt");
        String result = "What's your card numberThis card is already confiscatedWhat's your card numberSorry, this card has been reported as lostWhat's your card numberThis card is blockedWhat's your card numberThis card is in processWhat's your card numberDate of this card is invalid.What's your card number";
        reset();
        String value = bytes.toString().trim();
        value = value.replaceAll("\r|\n", "");
        assertEquals(result, value);

    }

    @Test
    void test_tree_time_wronginput() {
        String s = "11111\r\n123\r\n1ff\r\n100\r\nexit";
        ByteArrayInputStream strIn = new ByteArrayInputStream(s.getBytes());
        System.setIn(strIn);
        ATMSystem sys = new ATMSystem();
        sys.run("src/main/resources/test_cardlist.txt", "src/main/resources/test_atm.txt");
        String result = "What's your card numberPlease enter your pin.invaild inputPlease enter your pin.invaild inputPlease enter your pin.invaild inputCard was blocked!What's your card number";
        reset();
        String value = bytes.toString().trim();
        value = value.replaceAll("\r|\n", "");
        assertEquals(result, value);
    }

    @Test
    void test_withdraw_card_balance_not_enough() {
        String s = "77777\r\n1234\r\n1\r\n100\r\nexit";
        ByteArrayInputStream strIn = new ByteArrayInputStream(s.getBytes());
        System.setIn(strIn);
        ATMSystem sys = new ATMSystem();
        sys.run("src/main/resources/test_cardlist.txt", "src/main/resources/test_atm.txt");
        String result = "What's your card numberPlease enter your pin.Choose the service you need, 1 for withdrawal, 2 for deposit, 3 for balance check.How much do you want to withdrawal? (c for cancel)Insufficient balance of bank card.What's your card number";
        reset();
        String value = bytes.toString().trim();
        value = value.replaceAll("\r|\n", "");
        assertEquals(result, value);
    }

    @Test
    void test_withdraw_bank_balance_not_enough() {
        String s = "11111\r\n1234\r\n1\r\n1000\r\nexit";
        ByteArrayInputStream strIn = new ByteArrayInputStream(s.getBytes());
        System.setIn(strIn);
        ATMSystem sys = new ATMSystem();
        sys.run("src/main/resources/test_cardlist.txt", "src/main/resources/test_atm.txt");
        String result = "What's your card numberPlease enter your pin.Choose the service you need, 1 for withdrawal, 2 for deposit, 3 for balance check.How much do you want to withdrawal? (c for cancel)Insufficient balance of ATM machine.What's your card number";
        reset();
        String value = bytes.toString().trim();
        value = value.replaceAll("\r|\n", "");
        assertEquals(result, value);
    }

    @Test
    void test_withdraw_bank_some_cash_not_enough() {
        String s = "11111\r\n1234\r\n1\r\n50\r\nexit";
        ByteArrayInputStream strIn = new ByteArrayInputStream(s.getBytes());
        System.setIn(strIn);
        ATMSystem sys = new ATMSystem();
        sys.run("src/main/resources/test_cardlist.txt", "src/main/resources/test_atm.txt");
        String result = "What's your card numberPlease enter your pin.Choose the service you need, 1 for withdrawal, 2 for deposit, 3 for balance check.How much do you want to withdrawal? (c for cancel)The denomination and quantity of the money currently stored in the ATM does not meet the denomination of your withdrawal this time.What's your card number";
        reset();
        String value = bytes.toString().trim();
        value = value.replaceAll("\r|\n", "");
        assertEquals(result, value);
    }

    @Test
    void test_deposit() {
        String s = "11111\r\n1234\r\n2\r\n0 0 0 0 1\r\nexit";
        ByteArrayInputStream strIn = new ByteArrayInputStream(s.getBytes());
        System.setIn(strIn);
        ATMSystem sys = new ATMSystem();
        sys.run("src/main/resources/test_cardlist.txt", "src/main/resources/test_atm.txt");
        String result = "What's your card numberPlease enter your pin.Choose the service you need, 1 for withdrawal, 2 for deposit, 3 for balance check.How much do you want to deposit? (enter number for each denomination of note and c for cancel )format: <num of 5 dollar> <num of 10 dollar> <num of 20 dollar> <num of 50 dollar> <num of 100 dollar>transaction number: 01transaction type: deposit transactionamount deposited: 100.0account balance: 10100.0What's your card number";
        reset();
        String value = bytes.toString().trim();
        value = value.replaceAll("\r|\n", "");
        assertEquals(result, value);
    }

    @Test
    void test_deposit_too_much() {
        String s = "11111\r\n1234\r\n2\r\n0 0 0 0 502\r\nexit";
        ByteArrayInputStream strIn = new ByteArrayInputStream(s.getBytes());
        System.setIn(strIn);
        ATMSystem sys = new ATMSystem();
        sys.run("src/main/resources/test_cardlist.txt", "src/main/resources/test_atm.txt");
        String result = "What's your card numberPlease enter your pin.Choose the service you need, 1 for withdrawal, 2 for deposit, 3 for balance check.How much do you want to deposit? (enter number for each denomination of note and c for cancel )format: <num of 5 dollar> <num of 10 dollar> <num of 20 dollar> <num of 50 dollar> <num of 100 dollar>The currency value and quantity you deposit in the ATM must meet the specifications.What's your card number";
        reset();
        String value = bytes.toString().trim();
        value = value.replaceAll("\r|\n", "");
        assertEquals(result, value);
    }

    @Test
    void test_check_balance() {
        String s = "11111\r\n1234\r\n3\r\nexit";
        ByteArrayInputStream strIn = new ByteArrayInputStream(s.getBytes());
        System.setIn(strIn);
        ATMSystem sys = new ATMSystem();
        sys.run("src/main/resources/test_cardlist.txt", "src/main/resources/test_atm.txt");
        String result = "What's your card numberPlease enter your pin.Choose the service you need, 1 for withdrawal, 2 for deposit, 3 for balance check.The balance of is this card is 10000.0.What's your card number";
        reset();
        String value = bytes.toString().trim();
        value = value.replaceAll("\r|\n", "");
        assertEquals(result, value);
    }

    @Test
    void test_add_cash() {
        String s = "0\r\n4567\r\n0 0 0 0 0 0 0 0 0 1 1\r\nexit";
        ByteArrayInputStream strIn = new ByteArrayInputStream(s.getBytes());
        System.setIn(strIn);
        ATMSystem sys = new ATMSystem();
        sys.run("src/main/resources/test_cardlist.txt", "src/main/resources/test_atm.txt");
        String result = "What's your card numberPlease enter your manager pin.Please add Cash!What's your card number";
        reset();
        String value = bytes.toString().trim();
        value = value.replaceAll("\r|\n", "");
        assertEquals(result, value);
    }
//reset test file
    static void reset() {
        try {
            String filename = "src/main/resources/test_atm.txt";
            File f = new File(filename);
            PrintWriter writer = new PrintWriter(f);
            writer.println("id: 0 cashlist: 0 0 0 0 0 0 0 0 0 0 2 transaction: 0\n");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String filename = "src/main/resources/test_cardlist.txt";
            File f = new File(filename);
            PrintWriter writer = new PrintWriter(f);
            writer.println(
                    "cardNum: 11111 pin: 1234 startDate: 1-1-2020 expirationDate: 1-1-2022 balance: 10000.0 state: Free\ncardNum: 22222 pin: 1234 startDate: 1-1-2020 expirationDate: 1-1-2022 balance: 10000.0 state: Confiscate\ncardNum: 33333 pin: 1234 startDate: 1-1-2020 expirationDate: 1-1-2022 balance: 10000.0 state: Stolen\ncardNum: 44444 pin: 1234 startDate: 1-1-2020 expirationDate: 1-1-2022 balance: 10000.0 state: Block\ncardNum: 55555 pin: 1234 startDate: 1-1-2020 expirationDate: 1-1-2022 balance: 10000.0 state: Process\ncardNum: 66666 pin: 1234 startDate: 1-1-2020 expirationDate: 1-1-2021 balance: 10000.0 state: Free\ncardNum: 77777 pin: 1234 startDate: 1-1-2020 expirationDate: 1-1-2023 balance: 1.0 state: Free\ncardNum: 0 pin: 4567 startDate: 1-1-2020 expirationDate: 1-1-2022 balance: 0.0 state: Free\n");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
