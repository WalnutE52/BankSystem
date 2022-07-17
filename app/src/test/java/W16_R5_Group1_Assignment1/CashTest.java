package W16_R5_Group1_Assignment1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//test 1
public class CashTest {
    @Test
    public void testCoin() {
        Cash twenty_cents = new Coin(0.2);
        assertEquals(0.2, twenty_cents.getDenomination());
    }

    @Test
    public void testNote() {
        Cash twenty_dollar = new Note(20);
        assertEquals(20, twenty_dollar.getDenomination());
    }
}