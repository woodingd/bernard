package shuffler;

import java.sql.Connection;
import java.util.Random;

public class CardShuffler extends Thread {
	
	private Connection connection = null;
	private SQLConnector sqlconnector = new SQLConnector();
	private final String[] CARDS = {"K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A"};
	private final char[] SUITS = {'S', 'C', 'D', 'H'};
	
	public CardShuffler() {}
	
	public CardShuffler(Connection connection) {
		this.connection = connection;
	}
	
	public void run() {
		System.out.println(this.getName() + ": Started.");
		while (true) {
			String[] cards = shuffle(setupCards());
			sqlconnector.insertPermutation(this.connection, cards);
		}
	}

	public final String[] setupCards() {
		String[] cards = new String[52];
		int index = 0;
		for (char c : SUITS) {
			for (String s : CARDS) {
				cards[index] = c + s;
				index++;
			}
		}
		return cards;
	}
	
	public final String[] shuffle(String[] cards) {
		String result[] = new String[52];
		Random random = new Random();
		for (String card : cards) {
			int x = random.nextInt(52);
			tryInsert(card, result, x);
		}
		return result;
	}
	
	public final void tryInsert(String card, String[] cards, int index) {
		if (cards[index] == null) {
			cards[index] = card;
		} else {
			try {
				tryInsert(card, cards, index+1);
			} catch(IndexOutOfBoundsException e) {
				tryInsert(card, cards, 0);
			}
		}
	}
}
