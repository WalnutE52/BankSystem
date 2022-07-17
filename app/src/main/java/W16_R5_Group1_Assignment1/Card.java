package W16_R5_Group1_Assignment1;

import java.util.Calendar;

public class Card {
    private Integer cardNum;
    private int pin;
    private Calendar startDate;
    private Calendar expirationDate;
    private double balance;
    private State state;

    public Card(Integer cardNum, int pin, Calendar startDate, Calendar expirationDate, double balance, State state) {
        this.cardNum = cardNum;
        this.pin = pin;
        this.balance = balance;
        this.state = state;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

    public boolean getExpiration() {
        Calendar currentDate = Calendar.getInstance();
        int result = expirationDate.compareTo(currentDate);
        if (result >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public Integer getCardNum() {
        return this.cardNum;
    }

    public Calendar getStartDate() {
        return this.startDate;
    }

    public Calendar getExpirationDate() {
        return this.expirationDate;
    }

    public int getPin() {
        return this.pin;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
