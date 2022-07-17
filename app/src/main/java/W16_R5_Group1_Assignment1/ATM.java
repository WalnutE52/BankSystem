package W16_R5_Group1_Assignment1;

import java.math.BigDecimal;
import java.util.*;

public class ATM {
    private double balance;
    private Map<Cash, Integer> cashList;
    private int transaction;

//    private Bank bank;

//     public ATM(double blance, Map<Cash, Integer> cashList, int transaction,Bank bank) {
//         this.blance = blance;
//         this.cashList = cashList;
//         this.transaction = transaction;
//         this.bank = bank;
//     }

//    public Bank getBank() {
//        return this.bank;
//    }

    private int ATMid;

    public ATM(double balance, Map<Cash, Integer> cashList, int transaction, int ATMid) {
        this.balance = balance;
        this.cashList = cashList;
        this.transaction = transaction;
        this.ATMid = ATMid;
    }

    public int getATMid() {
        return ATMid;
    }


    public boolean withdrawal(Card card, double num) {
        if (card == null) {
            return false;
        }
        if (card != null && num >= 0 && card.getBalance() >= num && this.balance >= num) {
            card.setState(State.Process);
            boolean result = oprWithdrawNum(num);
            if (result) {
                card.setBalance(card.getBalance() - num);
                this.balance -= num;
                this.transaction++;
                System.out.println("transaction number: " + this.ATMid + this.transaction);
                System.out.println("transaction type: withdrawal transaction");
                System.out.println("amount withdrawn: " + num);
                System.out.println("account balance: " + card.getBalance() + "\n");
                this.eject(card);
                return true;
            }
        }
        this.eject(card);
        if (card.getBalance() < num) {
            System.out.println("Insufficient balance of bank card.");
        }
        if (this.balance < num) {
            System.out.println("Insufficient balance of ATM machine.");
        }
        if (num < 0) {
            System.out.println("The amount of money entered must be a positive value.");
        }
        return false;
    }

    public boolean deposit(Card card, Map<Note, Integer> depositCashList) {
        card.setState(State.Process);
        double balance = 0.0;
        for (Note key : depositCashList.keySet()) {
            if (depositCashList.get(key) < 0 || depositCashList.get(key) + this.cashList.get(key) > 500) {
                System.out.println(
                        "The currency value and quantity you deposit in the ATM must meet the specifications.");
                this.eject(card);
                return false;
            }
            balance += (key.getDenomination() * depositCashList.get(key));
        }
        for (Note key : depositCashList.keySet()) {
            this.cashList.put(key, depositCashList.get(key) + this.cashList.get(key));
        }
        card.setBalance(card.getBalance() + balance);
        this.balance += balance;
        this.transaction++;
        System.out.println("transaction number: " + this.ATMid + this.transaction);
        System.out.println("transaction type: deposit transaction");
        System.out.println("amount deposited: " + balance);
        System.out.println("account balance: " + card.getBalance() + "\n");
        this.eject(card);
        return true;
    }

    public void balance(Card card) {
        System.out.println("The balance of is this card is " + card.getBalance() + ".\n");
    }

    public boolean inputCardnum(long cardnum) {
        if (cardnum >= 10000 && cardnum <= 99999) {
            return true;
        }
        return false;
    }

    public boolean cardValid(Card card) {
        if (checkDate(card) && checkNumber(card) && checkState(card)) {

            return true;
        }
        return false;
    }

    public boolean inputValid(int pin, Card card) {
        if (card.getPin() == pin) {
            return true;
        }
        return false;
    }

    public void confiscateCard(Card card) {
        card.setState(State.Confiscate);
    }

    public void eject(Card card) {
        if (card.getState().equals(State.Process)) {
            card.setState(State.Free);
        }
    }

    public void addCash(Map<Cash, Integer> cashList) {
        for (Cash cash1 : cashList.keySet()) {
            if (cash1 instanceof Note) {
                if (cash1.getDenomination() == 100 && this.cashList.get(cash1) + cashList.get(cash1) <= 500) {
                    this.cashList.put(cash1, cashList.get(cash1));
                    this.balance += 100 * cashList.get(cash1);
                } else if (cash1.getDenomination() == 50 && this.cashList.get(cash1) + cashList.get(cash1) <= 500) {
                    this.cashList.put(cash1, cashList.get(cash1));
                    this.balance += 50 * cashList.get(cash1);
                } else if (cash1.getDenomination() == 20 && this.cashList.get(cash1) + cashList.get(cash1) <= 500) {
                    this.cashList.put(cash1, cashList.get(cash1));
                    this.balance += 20 * cashList.get(cash1);
                } else if (cash1.getDenomination() == 10 && this.cashList.get(cash1) + cashList.get(cash1) <= 500) {
                    this.cashList.put(cash1, cashList.get(cash1));
                    this.balance += 10 * cashList.get(cash1);
                } else if (cash1.getDenomination() == 5 && this.cashList.get(cash1) + cashList.get(cash1) <= 500) {
                    this.cashList.put(cash1, cashList.get(cash1));
                    this.balance += 5 * cashList.get(cash1);
                }
            }
            if (cash1 instanceof Coin) {
                if (cash1.getDenomination() == 0.5 && this.cashList.get(cash1) + cashList.get(cash1) <= 500) {
                    this.cashList.put(cash1, cashList.get(cash1));
                    this.balance += 0.5 * cashList.get(cash1);
                } else if (cash1.getDenomination() == 0.2 && this.cashList.get(cash1) + cashList.get(cash1) <= 500) {
                    this.cashList.put(cash1, cashList.get(cash1));
                    this.balance += 0.2 * cashList.get(cash1);
                } else if (cash1.getDenomination() == 0.1 && this.cashList.get(cash1) + cashList.get(cash1) <= 500) {
                    this.cashList.put(cash1, cashList.get(cash1));
                    this.balance += 0.1 * cashList.get(cash1);
                } else if (cash1.getDenomination() == 0.05 && this.cashList.get(cash1) + cashList.get(cash1) <= 500) {
                    this.cashList.put(cash1, cashList.get(cash1));
                    this.balance += 0.05 * cashList.get(cash1);
                } else if (cash1.getDenomination() == 2 && this.cashList.get(cash1) + cashList.get(cash1) <= 500) {
                    this.cashList.put(cash1, cashList.get(cash1));
                    this.balance += 2 * cashList.get(cash1);
                } else if (cash1.getDenomination() == 1 && this.cashList.get(cash1) + cashList.get(cash1) <= 500) {
                    this.cashList.put(cash1, cashList.get(cash1));
                    this.balance += cashList.get(cash1);
                }
            }
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Map<Cash, Integer> getCashList() {
        return cashList;
    }

    public void setCashList(Map<Cash, Integer> cashList) {
        this.cashList = cashList;
    }

    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    private boolean checkNumber(Card card) {
        int cardNum = card.getCardNum();
        if (cardNum >= 10000 && cardNum <= 99999) {
            return true;
        }
        System.out.println("This card id is invalid.");
        return false;
    }

    private boolean checkDate(Card card) {
        Calendar startDate = card.getStartDate();
        long startDateTime = startDate.getTime().getTime();
        Calendar expirationDate = card.getExpirationDate();
        long expirationDateTime = expirationDate.getTime().getTime();
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, 2021);
        date.set(Calendar.MONTH, 9);
        date.set(Calendar.DATE, 17);
        long currentTime = date.getTime().getTime();
        if (currentTime >= startDateTime && currentTime <= expirationDateTime) {
            return true;
        }
        System.out.println("Date of this card is invalid.");
        return false;
    }

    private boolean checkState(Card card) {
        String st = "Free";
        if (st.equals(card.getState().toString())) {

            return true;
        }
        st = "Stolen";
        if (st.equals(card.getState().toString())) {
            System.out.println("Sorry, this card has been reported as lost");
            this.confiscateCard(card);
            return false;

        }
        st = "Process";
        if (st.equals(card.getState().toString())) {
            System.out.println("This card is in process");
            return false;
        }
        st = "Confiscate";
        if (st.equals(card.getState().toString())) {
            System.out.println("This card is already confiscated");
            return false;
        }

        st = "Block";
        if (st.equals(card.getState().toString())) {
            System.out.println("This card is blocked");
            return false;
        }
        return false;

    }

    private boolean oprWithdrawNum(double num) {
        Map<Cash, Integer> copyMap = new HashMap<>();
        Set<Cash> cash = cashList.keySet();
        int num_100 = 0;
        int num_50 = 0;
        int num_20 = 0;
        int num_10 = 0;
        int num_5 = 0;
        int num_2 = 0;
        int num_1 = 0;
        int num_0_5 = 0;
        int num_0_2 = 0;
        int num_0_1 = 0;
        int num_0_0_5 = 0;
        for (Cash cash1 : cash) {
            if (cash1.getDenomination() == 100) {
                num_100 = cashList.get(cash1);
            } else if (cash1.getDenomination() == 50) {
                num_50 = cashList.get(cash1);
            } else if (cash1.getDenomination() == 20) {
                num_20 = cashList.get(cash1);
            } else if (cash1.getDenomination() == 10) {
                num_10 = cashList.get(cash1);
            } else if (cash1.getDenomination() == 5) {
                num_5 = cashList.get(cash1);
            } else if (cash1.getDenomination() == 2) {
                num_2 = cashList.get(cash1);
            } else if (cash1.getDenomination() == 1) {
                num_1 = cashList.get(cash1);
            } else if (cash1.getDenomination() == 0.5) {
                num_0_5 = cashList.get(cash1);
            } else if (cash1.getDenomination() == 0.2) {
                num_0_2 = cashList.get(cash1);
            } else if (cash1.getDenomination() == 0.1) {
                num_0_1 = cashList.get(cash1);
            } else if (cash1.getDenomination() == 0.05) {
                num_0_0_5 = cashList.get(cash1);
            }
            copyMap.put(cash1, cashList.get(cash1));
        }

        String s = String.valueOf((double) num);
        String[] split = s.split("\\.");
        int preNum = Integer.parseInt(split[0]);
        double lastNum = Double.parseDouble("0." + split[1]);

        while (preNum > 0) {
            Set<Cash> cashWhile = cashList.keySet();
            if (num_100 > 0 && preNum >= 100) {
                for (Cash cash1 : cashWhile) {
                    if (cash1.getDenomination() == 100) {
                        this.cashList.put(cash1, num_100 - 1);
                        break;
                    }
                }
                preNum = preNum - 100;
                num_100--;
            } else if (num_50 > 0 && preNum >= 50) {
                for (Cash cash1 : cashWhile) {
                    if (cash1.getDenomination() == 50) {
                        this.cashList.put(cash1, num_50 - 1);
                        break;
                    }
                }
                preNum = preNum - 50;
                num_50--;
            } else if (num_20 > 0 && preNum >= 20) {
                for (Cash cash1 : cashWhile) {
                    if (cash1.getDenomination() == 20) {
                        this.cashList.put(cash1, num_20 - 1);
                        break;
                    }
                }
                preNum = preNum - 20;
                num_20--;
            } else if (num_10 > 0 && preNum >= 10) {
                for (Cash cash1 : cashWhile) {
                    if (cash1.getDenomination() == 10) {
                        this.cashList.put(cash1, num_10 - 1);
                        break;
                    }
                }
                preNum = preNum - 10;
                num_10--;
            } else if (num_5 > 0 && preNum >= 5) {
                for (Cash cash1 : cashWhile) {
                    if (cash1.getDenomination() == 5) {
                        this.cashList.put(cash1, num_5 - 1);
                        break;
                    }
                }
                preNum = preNum - 5;
                num_5--;
            } else if (num_2 > 0 && preNum >= 2) {
                for (Cash cash1 : cashWhile) {
                    if (cash1.getDenomination() == 2) {
                        this.cashList.put(cash1, num_2 - 1);
                        break;
                    }
                }
                preNum = preNum - 2;
                num_2--;
            } else if (num_1 > 0 && preNum >= 1) {
                for (Cash cash1 : cashWhile) {
                    if (cash1.getDenomination() == 1) {
                        this.cashList.put(cash1, num_1 - 1);
                        break;
                    }
                }
                preNum = preNum - 1;
                num_1--;
            } else {
                break;
            }
        }

        if (preNum > 0) {
            this.cashList = copyMap;
            System.out.println("The denomination and quantity of the money currently "
                    + "stored in the ATM does not meet the denomination of your withdrawal this time.");
            return false;
        } else {
            while (lastNum > 0) {
                Set<Cash> cashWhile = cashList.keySet();
                if (num_0_5 > 0 && lastNum >= 0.5) {
                    for (Cash cash1 : cashWhile) {
                        if (cash1.getDenomination() == 0.5) {
                            this.cashList.put(cash1, num_0_5 - 1);
                            break;
                        }
                    }
                    lastNum = sub(lastNum, 0.5);
                    num_0_5--;
                } else if (num_0_2 > 0 && lastNum >= 0.2) {
                    for (Cash cash1 : cashWhile) {
                        if (cash1.getDenomination() == 0.2) {
                            this.cashList.put(cash1, num_0_2 - 1);
                            break;
                        }
                    }
                    lastNum = sub(lastNum, 0.2);
                    num_0_2--;
                } else if (num_0_1 > 0 && lastNum >= 0.1) {
                    for (Cash cash1 : cashWhile) {
                        if (cash1.getDenomination() == 0.1) {
                            this.cashList.put(cash1, num_0_1 - 1);
                            break;
                        }
                    }
                    lastNum = sub(lastNum, 0.1);
                    num_0_1--;
                } else if (num_0_0_5 > 0 && lastNum >= 0.05) {
                    for (Cash cash1 : cashWhile) {
                        if (cash1.getDenomination() == 0.05) {
                            this.cashList.put(cash1, num_0_0_5 - 1);
                            break;
                        }
                    }
                    lastNum = sub(lastNum, 0.05);
                    num_0_0_5--;
                } else {
                    break;
                }
            }
            if (lastNum > 0) {
                this.cashList = copyMap;
                System.out.println("The denomination and quantity of the money currently "
                        + "stored in the ATM does not meet the denomination of your withdrawal this time.");
                return false;
            }
        }
        return true;
    }

    public double sub(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.subtract(bd2).doubleValue();
    }
}
