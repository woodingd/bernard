package bernard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardShuffler {
	
	private static final String[] CARDS = {"K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A"};
	private static final char[] SUITS = {'S', 'C', 'D', 'H'};

	public static final String[] setupCards() {
		String[] cards = new String[52];
		int counter = 0;
		for (char c : SUITS) {
			for (String s : CARDS) {
				cards[counter] = s + c;
				counter++;
			}
		}
		return cards;
	}
	
	public static final String[] shuffle(String[] cards) {
		String result[] = new String[52];
		for (String card : cards) {
			Random random = new Random();
			int x = random.nextInt(52);
			tryInsert(card, result, x);
		}
		return result;
	}
	
	public static final void tryInsert(String s, String[] a, int x) {
		if (a[x] == null) {
			a[x] = s;
		} else {
			try {
				tryInsert(s, a, x+1);
			} catch(IndexOutOfBoundsException e1) {
				tryInsert(s, a, 0);
			}
		}
	}
	
	public static final String[] shuffleForN(String[] cards, int n) {
		String s = "| ";
		if (n > 0) {
			cards = shuffle(cards);
			for (String card : cards) {
				s += card + " | ";
			}
			System.out.println(s);
			return shuffleForN(cards, n-1);
		}
		return cards;
	}
	
	public static void main(String[] args) {
		String[] cards = setupCards();
		Random random = new Random();
		int number = 7 + random.nextInt(10);
		shuffleForN(cards, number);
	}

}
