package shuffler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnector {

    Connection connection;

    public void createConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:cards.db");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Opened database successfully.");
    }

    public void createTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS CARDS (PERMUTATION TEXT PRIMARY KEY NOT NULL);";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Successfully created table.");
    }

    public void insertPermutation(String[] permutation) {
        String cards = "";
        for (String card : permutation) {
            cards += card;
        }

        try (Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO CARDS VALUES (\"" + cards + "\");";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(cards);
            System.exit(0);
        }
    }
}
