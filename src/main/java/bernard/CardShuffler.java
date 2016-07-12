package bernard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardShuffler {
	
	private static final String[] CARDS = {"K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A"};
	private static final char[] SUITS = {'S', 'C', 'D', 'H'};

	public static final List<String> setupCards() {
		List<String> cards = new ArrayList<String>();
		for (char c : SUITS) {
			for (String s : CARDS) {
				cards.add(s + c);
			}
		}
		return cards;
	}
	
	public static final String[] shuffle(List<String> cards) {
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
	
	public static void main(String[] args) {
		List<String> cards = setupCards();
		String s = "";
		for (String card : shuffle(cards)) {
			s += card + " | ";
		}
		System.out.println(s);
	}

}
