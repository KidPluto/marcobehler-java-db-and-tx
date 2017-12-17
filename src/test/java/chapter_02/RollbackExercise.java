package chapter_02;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class RollbackExercise {

    @Before
    public void setUp() {
        try (Connection connection = getConnection()) {
            createTables(connection);
        } catch (SQLException  e) {e.printStackTrace();}
    }
    @Test
    public void rollback_exercise() throws SQLException {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            connection.createStatement().execute("insert into items (name) values ('Windows 10 Premium Edition')");
            connection.createStatement().execute("insert into bids (user, time, amount, currency) values ('Hans', now(), 1, 'EUR')");
            connection.createStatement().execute("insert into bids (user, time, amount, currency) values ('Franz', now(), 2, 'EUR')");
            // Never mind, lets undo that.
            connection.rollback();
            System.out.println("Ok, did a rollback");
            //
            assertThat(getItemsCount(connection), equalTo(0));
        }
    }
    private int getItemsCount (Connection connection) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("select count(*) as count from items");
        resultSet.next();
        int count = resultSet.getInt("count");
        System.out.println("Number of items in the items table: " + count);
        resultSet.close();
        return count;
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
