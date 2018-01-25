package shuffler;

public class ThreadRunner {

    private static final SQLConnector SQL_CONNECTOR = new SQLConnector();

    public static void main(String[] args) {
        SQL_CONNECTOR.createConnection();
        SQL_CONNECTOR.createTable();

        Thread shuffle1 = new CardShuffler(SQL_CONNECTOR);
        Thread shuffle2 = new CardShuffler(SQL_CONNECTOR);

        shuffle1.start();
        shuffle2.start();
    }
}
