package shuffler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class SQLConnector {
	
	private Connection connection;

	public Connection createConnection() {
		
		try {
		      Class.forName("org.sqlite.JDBC");
		      this.connection = DriverManager.getConnection("jdbc:sqlite:cards.db");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    System.out.println("Opened database successfully.");
		    
		    return connection;
	}
	
	public void createTable(Connection connection) {
		
		try {
			Statement statement = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS CARDS "
					+ "(PERMUTATION TEXT PRIMARY KEY NOT NULL);";
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully created table.");
		
	}
	
	public void insertPermutation(Connection connection, String[] permutation) {
		String cards = "";
		for (String card : permutation) {
			cards += card;
		}
		
		try {
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO CARDS "
					+ "VALUES (\"" + cards + "\");";
			statement.executeUpdate(sql);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(cards);
			System.exit(0);
		}
	}
}
