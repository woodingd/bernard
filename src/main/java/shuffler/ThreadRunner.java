package shuffler;

import java.sql.Connection;

public class ThreadRunner {
	
	private static SQLConnector sqlConnector = new SQLConnector();

	public static void main(String[] args) {
		
		Connection conn = sqlConnector.createConnection();
		
		Thread shuffle1 = new CardShuffler(conn);
		Thread shuffle2 = new CardShuffler(conn);
		
		shuffle1.start();
		shuffle2.start();

	}
}
