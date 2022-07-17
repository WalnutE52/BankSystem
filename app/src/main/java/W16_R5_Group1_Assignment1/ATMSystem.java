package W16_R5_Group1_Assignment1;

import java.util.Scanner;
import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Calendar;

public class ATMSystem {
    private Bank XYZbank;
    private ATM atm;
    private static Cash five_cents = new Coin(0.05);
    private static Cash ten_cents = new Coin(0.1);
    private static Cash twenty_cents = new Coin(0.2);
    private static Cash fifty_cents = new Coin(0.5);
    private static Cash one_dollar = new Coin(1);
    private static Cash two_dollar = new Coin(2);
    private static Cash five_dollar = new Note(5);
    private static Cash ten_dollar = new Note(10);
    private static Cash twenty_dollar = new Note(20);
    private static Cash fifty_dollar = new Note(50);
    private static Cash one_hundred_dollar = new Note(100);

    public void run(String bank_cardlist_name, String atm_name) {
        Scanner reader = new Scanner(System.in);
        atm = null;

        String atmfilename = atm_name;
        File f = new File(atmfilename);
        // create atm
        try {
            Scanner freader = new Scanner(f);
            String str;
            String[] buffer;
            if (freader.hasNextLine()) {
                str = freader.nextLine();
                buffer = str.split(" ");
                atm = creatAtm(buffer);
                if (atm == null) {
                    System.err.println("File attribute wrong!");
                    freader.close();
                    return;
                }
                freader.close();
            } else {
                System.out.println("File is empty");
                freader.close();
                return;
            }
        } catch (FileNotFoundException e) {
            System.err.println("No such Atm");
            reader.close();
            return;
        }
        // creat card list
        String cardlist_name = bank_cardlist_name;
        File f2 = new File(cardlist_name);
        ArrayList<Card> cardlist = new ArrayList<Card>();
        try {
            Scanner freader = new Scanner(f2);
            while (freader.hasNextLine()) {
                String buff[] = freader.nextLine().split(" ");
                Card tempCard = creatCard(buff);
                if (tempCard != null) {
                    cardlist.add(tempCard);
                }
            }
            freader.close();

        } catch (FileNotFoundException e) {
            System.err.println("Bank cardlist file not found");
            reader.close();
            return;
        }

        this.XYZbank = new Bank(cardlist);
        while (true) {
            System.out.println("What's your card number");
            String temp = reader.nextLine();
            Card user_card = null;

            if (temp.equals("exit")) {
                saveInfo(this.XYZbank.getValidCardList(), atm, bank_cardlist_name, atm_name);
                reader.close();
                return;
            }
            if (isCorrectNum(temp)) {
                int temp_cardnum = Integer.valueOf(temp).intValue();
                Boolean vaild = true;
                Boolean manager = false;
                for (Card card : cardlist) {
                    if (card.getCardNum() == 0 && temp_cardnum == 0) {
                        manager = true;
                        System.out.println("Please enter your manager pin.");
                        String manager_pin;
                        int manager_pin_int;
                        try {
                            manager_pin = reader.nextLine();
                            manager_pin_int = Integer.valueOf(manager_pin).intValue();
                        } catch (Exception e) {
                            System.out.println("invaild input");
                            continue;
                        }
                        if (manager_pin_int == card.getPin()) {
                            System.out.println("Please add Cash!");
                            String cash_str = reader.nextLine();
                            String[] buffer = cash_str.split(" ");

                            if (buffer.length != 11) {
                                System.out.println("wrong input!");
                                continue;
                            }
                            Map<Cash, Integer> inputList = new HashMap<Cash, Integer>();

                            try {
                                int dollar0_0_5 = Integer.valueOf(buffer[0]).intValue();
                                int dollar0_10 = Integer.valueOf(buffer[1]).intValue();
                                int dollar0_20 = Integer.valueOf(buffer[2]).intValue();
                                int dollar0_50 = Integer.valueOf(buffer[3]).intValue();
                                int dollar1 = Integer.valueOf(buffer[4]).intValue();
                                int dollar2 = Integer.valueOf(buffer[5]).intValue();
                                int dollar5 = Integer.valueOf(buffer[6]).intValue();
                                int dollar10 = Integer.valueOf(buffer[7]).intValue();
                                int dollar20 = Integer.valueOf(buffer[8]).intValue();
                                int dollar50 = Integer.valueOf(buffer[9]).intValue();
                                int dollar100 = Integer.valueOf(buffer[10]).intValue();

                                inputList.put(five_cents, dollar0_0_5);
                                inputList.put(ten_cents, dollar0_10);
                                inputList.put(twenty_cents, dollar0_20);
                                inputList.put(fifty_cents, dollar0_50);
                                inputList.put(one_dollar, dollar1);
                                inputList.put(two_dollar, dollar2);
                                inputList.put(five_dollar, dollar5);
                                inputList.put(ten_dollar, dollar10);
                                inputList.put(twenty_dollar, dollar20);
                                inputList.put(fifty_dollar, dollar50);
                                inputList.put(one_hundred_dollar, dollar100);

                            } catch (Exception e) {
                                System.out.println("wrong add cash!");
                                continue;
                            }
                            atm.addCash(inputList);

                        } else {
                            System.out.println("Wrong manager pin!");
                        }

                    } else if (card.getCardNum() == temp_cardnum) {
                        if (!atm.cardValid(card)) {
                            vaild = false;
                            break;
                        }

                        int counter = 0;
                        while (counter < 3) {
                            System.out.println("Please enter your pin.");
                            String pin;
                            int int_pin;
                            try {
                                pin = reader.nextLine();
                                int_pin = Integer.valueOf(pin).intValue();
                            } catch (Exception e) {
                                System.out.println("invaild input");
                                counter += 1;
                                continue;
                            }
                            if (!atm.inputValid(int_pin, card)) {
                                System.out.println("invaild input");
                                counter += 1;
                            } else {
                                break;
                            }

                        }
                        if (counter == 3) {
                            card.setState(State.Block);
                            System.out.println("Card was blocked!");
                            vaild = false;
                            continue;
                        } else {
                            user_card = card;
                            break;
                        }
                    }
                }
                if (user_card == null) {
                    if (vaild == true && manager == false) {
                        System.out.println("No such card!");
                    }
                    continue;
                }

            } else {
                System.out.println("Wrong card number type!");
                continue;
            }
            System.out.println("Choose the service you need, 1 for withdrawal, 2 for deposit, 3 for balance check.");
            String temp2 = reader.nextLine();
            if (temp2.equals("1")) {
                System.out.println("How much do you want to withdrawal? (c for cancel)");
                temp2 = reader.nextLine();
                if (temp2.equals("c")) {
                    continue;
                }
                if (isCorrectNum(temp2)) {
                    double value = Double.valueOf(temp2.toString());
                    atm.withdrawal(user_card, value);
                } else {
                    System.out.println("Wrong input!");
                }
            } else if (temp2.equals("2")) {
                System.out.println(
                        "How much do you want to deposit? (enter number for each denomination of note and c for cancel )format: <num of 5 dollar> <num of 10 dollar> <num of 20 dollar> <num of 50 dollar> <num of 100 dollar>");
                temp2 = reader.nextLine();
                if (temp2.equals("c")) {
                    continue;
                }
                String[] buffer = temp2.split(" ");
                if (buffer.length != 5) {
                    System.out.println("wrong input!");
                    continue;
                }
                Map<Note, Integer> noteList = new HashMap<Note, Integer>();

                try {
                    int dollar5 = Integer.valueOf(buffer[0]).intValue();
                    int dollar10 = Integer.valueOf(buffer[1]).intValue();
                    int dollar20 = Integer.valueOf(buffer[2]).intValue();
                    int dollar50 = Integer.valueOf(buffer[3]).intValue();
                    int dollar100 = Integer.valueOf(buffer[4]).intValue();
                    noteList.put((Note) five_dollar, dollar5);
                    noteList.put((Note) ten_dollar, dollar10);
                    noteList.put((Note) twenty_dollar, dollar20);
                    noteList.put((Note) fifty_dollar, dollar50);
                    noteList.put((Note) one_hundred_dollar, dollar100);

                } catch (Exception e) {
                    System.out.println("wrong input!");
                    continue;
                }
                atm.deposit(user_card, noteList);
            } else if (temp2.equals("3")) {
                atm.balance(user_card);
            } else {
                System.out.println("Wrong input.");
            }
        }
    }

    public static boolean isCorrectNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static ATM creatAtm(String[] buff) {
        try {
            Map<Cash, Integer> cashList = new HashMap<Cash, Integer>();

            int[] numlist = new int[11];
            for (int i = 0; i < 11; i += 1) {
                numlist[i] = Integer.valueOf(buff[i + 3]).intValue();
                if (numlist[i] > 500) {
                    return null;
                }
            }
            cashList.put(five_cents, numlist[0]);
            cashList.put(ten_cents, numlist[1]);
            cashList.put(twenty_cents, numlist[2]);
            cashList.put(fifty_cents, numlist[3]);
            cashList.put(one_dollar, numlist[4]);
            cashList.put(two_dollar, numlist[5]);
            cashList.put(five_dollar, numlist[6]);
            cashList.put(ten_dollar, numlist[7]);
            cashList.put(twenty_dollar, numlist[8]);
            cashList.put(fifty_dollar, numlist[9]);
            cashList.put(one_hundred_dollar, numlist[10]);
            double balance = cashList.get(five_cents) * 0.05 + cashList.get(ten_cents) * 0.1
                    + cashList.get(twenty_cents) * 0.2 + cashList.get(fifty_cents) * 0.5 + cashList.get(one_dollar) * 1
                    + cashList.get(two_dollar) * 2 + cashList.get(five_dollar) * 5 + cashList.get(ten_dollar) * 10
                    + cashList.get(twenty_dollar) * 20 + cashList.get(fifty_dollar) * 50
                    + cashList.get(one_hundred_dollar) * 100;
            int transaction = Integer.valueOf(buff[15]).intValue();
            int id = Integer.valueOf(buff[1]).intValue();
            ATM atm = new ATM(balance, cashList, transaction, id);
            return atm;
        } catch (Exception e) {
            return null;
        }
    }

    public static Card creatCard(String[] buff) {
        try {
            Integer cardNum = Integer.valueOf(buff[1]).intValue();
            int pin = Integer.valueOf(buff[3]).intValue();
            Calendar startDate = Calendar.getInstance();
            String[] date1 = buff[5].split("-");
            int day1 = Integer.valueOf(date1[0]).intValue();
            int month1 = Integer.valueOf(date1[1]).intValue();
            int year1 = Integer.valueOf(date1[2]).intValue();
            startDate.set(year1, month1, day1);
            String[] date2 = buff[7].split("-");
            int day2 = Integer.valueOf(date2[0]).intValue();
            int month2 = Integer.valueOf(date2[1]).intValue();
            int year2 = Integer.valueOf(date2[2]).intValue();
            Calendar expirationDate = Calendar.getInstance();
            expirationDate.set(year2, month2, day2);
            double balance = Double.valueOf(buff[9]).doubleValue();
            State state;
            if (buff[11].equals("Block")) {
                state = State.Block;
            } else if (buff[11].equals("Process")) {
                state = State.Process;
            } else if (buff[11].equals("Stolen")) {
                state = State.Stolen;
            } else if (buff[11].equals("Confiscate")) {
                state = State.Confiscate;
            } else if (buff[11].equals("Free")) {
                state = State.Free;
            } else {
                return null;
            }
            Card card = new Card(cardNum, pin, startDate, expirationDate, balance, state);
            return card;

        } catch (Exception e) {
            return null;
        }

    }

    public void saveInfo(ArrayList<Card> cardlist, ATM atm, String bank_cardlist, String atmname) {

        try {
            String atmfilename = bank_cardlist;
            File f = new File(atmfilename);
            PrintWriter writer = new PrintWriter(f);

            for (Card card : cardlist) {
                String state = "Free";
                if (card.getState() == State.Block) {
                    state = "Block";
                } else if (card.getState() == State.Process) {
                    state = "Process";
                } else if (card.getState() == State.Stolen) {
                    state = "Stolen";
                } else if (card.getState() == State.Confiscate) {
                    state = "Confiscate";
                } else if (card.getState() == State.Free) {
                    state = "Free";
                }

                writer.println("cardNum: " + card.getCardNum() + " " + "pin: " + card.getPin() + " " + "startDate: "
                        + card.getStartDate().get(Calendar.DATE) + "-" + card.getStartDate().get(Calendar.MONTH) + "-"
                        + card.getStartDate().get(Calendar.YEAR) + " " + "expirationDate: "
                        + card.getExpirationDate().get(Calendar.DATE) + "-"
                        + card.getExpirationDate().get(Calendar.MONTH) + "-"
                        + card.getExpirationDate().get(Calendar.YEAR) + " " + "balance: " + card.getBalance() + " "
                        + "state: " + state);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {

            String filename = atmname;
            File f = new File(filename);
            PrintWriter writer = new PrintWriter(f);
            writer.println("id: " + atm.getATMid() + " " + "cashlist: " + atm.getCashList().get(five_cents) + " "
                    + atm.getCashList().get(ten_cents) + " " + atm.getCashList().get(twenty_cents) + " "
                    + atm.getCashList().get(fifty_cents) + " " + atm.getCashList().get(one_dollar) + " "
                    + atm.getCashList().get(two_dollar) + " " + atm.getCashList().get(five_dollar) + " "
                    + atm.getCashList().get(ten_dollar) + " " + atm.getCashList().get(twenty_dollar) + " "
                    + atm.getCashList().get(fifty_dollar) + " " + atm.getCashList().get(one_hundred_dollar) + " "
                    + "transaction: " + atm.getTransaction());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
