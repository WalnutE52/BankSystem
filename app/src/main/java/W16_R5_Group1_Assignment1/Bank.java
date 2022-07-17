package W16_R5_Group1_Assignment1;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Card> validCardList;

    public Bank(ArrayList<Card> validCardList) {
        this.validCardList = validCardList;

    }

    public ArrayList<Card> getValidCardList() {
        return this.validCardList;
    }

    public Card getCard(int cardNum) {
        for (Card c : this.validCardList) {
            int compareValue = Integer.compare(cardNum, c.getCardNum());
            if (compareValue == 0) {
                return c;
            }
        }
        return null;
    }
}
