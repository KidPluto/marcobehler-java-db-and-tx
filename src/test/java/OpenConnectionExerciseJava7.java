import org.junit.Test;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OpenConnectionExerciseJava7 {

    private static final Integer NO_TIMEOUT= 0;

    @Test
    public void open_jdbc_connection_java_7() {
        // Connection closed automatically
        try (Connection conn = DriverManager.getConnection(
                "jdbc:h2:mem:exercise_db;DB_CLOSE_DELAY=-1")) {
            System.out.println("Are we connected to the database? : " + conn.isValid(NO_TIMEOUT));
            // Create the base tables
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
        }
        catch (SQLException e) {e.printStackTrace();}
    }
}
