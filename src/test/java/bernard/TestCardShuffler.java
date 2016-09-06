package bernard;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TestCardShuffler {
	
	String[] cards;

	@Before
	public void setUp() throws Exception {
		cards = CardShuffler.setupCards();
	}

	@Test
	public void testNoNullsInResult() {
		String[] result = CardShuffler.shuffle(cards);
		for (String card : result) {
			assertNotEquals(card, null);
		}
	}
	
	@Test
	public void testEveryCardInResult() {
		String[] result = CardShuffler.shuffle(cards);
		int x = 0;
		for (String card : cards) {
			for (String other : result) {
				if (card == other) {
					x++;
				}
			}
		}
		assertEquals(x, 52);
	}

}
