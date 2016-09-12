package bernard;

import java.util.Random;
import java.sql.*;

public class CardShuffler extends Thread {
	
	private static final String[] CARDS = {"K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A"};
	private static final char[] SUITS = {'S', 'C', 'D', 'H'};

	public static final String[] setupCards() {
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
	
	public static final String[] shuffle(String[] cards) {
		String result[] = new String[52];
		Random random = new Random();
		for (String card : cards) {
			int x = random.nextInt(52);
			tryInsert(card, result, x);
		}
		return result;
	}
	
	public static final void tryInsert(String card, String[] cards, int index) {
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
	
	public static void main(String[] args) {
		Thread shuffle1 = new CardShuffler();
		Thread shuffle2 = new CardShuffler();
		Thread shuffle3 = new CardShuffler();
		
		shuffle1.start();
		shuffle2.start();
		shuffle3.start();
	}
	
	public void run() {
		System.out.println(this.getName() + ": Started.");
		int count = 0;
		while (true) {
			count++;
			String[] cards = setupCards();
			String s = "| ";
			for (String card : shuffle(cards)) {
				s += card + " | ";
			}
			System.out.println(this.getName() + "-" + count + ": " + s);
		}
	}

}
