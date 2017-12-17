package chapter_02;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionExercise {
    @Before
    public void setUp() {
        try (Connection connection = getConnection()) {
            createTables(connection);
        } catch (SQLException  e) {e.printStackTrace();}
    }
    @Test
    public void transaction_exercise() {
        try (Connection connection=getConnection()) {
            System.out.println("Opening a jdbc transaction...");
            connection.setAutoCommit(false);
            connection.createStatement().execute("insert into items (name) values ('Windows 10 Premium Edition')");
            connection.createStatement().execute("insert into bids (user, time, amount, currency) values ('Hans', now(), 1, 'EUR')");
            connection.createStatement().execute("insert into bids (user, time, amount, currency) values ('Franz', now(), 2, 'EUR')");
            connection.commit();
            System.out.println("Commit worked!");
        } catch (SQLException e) {e.printStackTrace();}
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:exercise_db;DB_CLOSE_DELAY=-1");
    }
    public void createTables(Connection conn) {
        try {
            conn.createStatement().execute("create table bids (" +
                    "id IDENTITY, " +
                    "user VARCHAR, " +
                    "time TIMESTAMP, " +
                    "amount NUMBER, " +
                    "currency VARCHAR)");
            conn.createStatement().execute("create table items (" +
                    "id IDENTITY, " +
                    "name VARCHAR)");
            conn.createStatement().execute("create table winning_bids (" +
                    "bid_id IDENTITY, " +
                    "item_id NUMBER)");
            System.out.println("Tables created.");
        } catch (SQLException e) {e.printStackTrace();}
    }
}
