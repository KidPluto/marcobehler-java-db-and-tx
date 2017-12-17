import org.junit.Test;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OpenConnectionExerciseJava7 {

    private static final Integer NO_TIMEOUT= 0;

    // Note
    // Java7 has automatic resource closing, so don't need "finally"
    //
    @Test
    public void open_jdbc_connection_java_7() {
        // Connection closed automatically
        try (Connection conn = DriverManager.getConnection(
                "jdbc:h2:mem:exercise_db;DB_CLOSE_DELAY=-1")) {
            System.out.println("Are we connected to the database? : " + conn.isValid(NO_TIMEOUT));

        }
        catch (SQLException e) {e.printStackTrace();}
    }
}
