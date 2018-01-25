package shuffler;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestCardShuffler {

    String[] cards;
    CardShuffler cardShuffler = new CardShuffler(new SQLConnector());

    @Before
    public void setUp() throws Exception {
        cards = cardShuffler.setupCards();
    }

    @Test
    public void testShuffle_NoNullsInResult() {
        String[] result = cardShuffler.shuffle(cards);
        for (String card : result) {
            assertNotNull(card);
        }
    }

    @Test
    public void testShuffle_EveryCardInResult() {
        String[] result = cardShuffler.shuffle(cards);
        int x = 0;
        for (String card : cards) {
            for (String other : result) {
                if (card.equals(other)) {
                    x++;
                }
            }
        }
        assertEquals(x, 52);
    }
}
