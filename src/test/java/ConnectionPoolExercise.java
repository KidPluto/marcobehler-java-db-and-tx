import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ConnectionPoolExercise {
    private static final Integer NO_TIMEOUT = 0;

    @Test
    public void exercise() {
        DataSource ds = getDataSource();
        try (Connection connection = ds.getConnection()) {
            System.out.println("Got it, we have connection pooling now.");
            assertTrue(connection.isValid(NO_TIMEOUT));
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            if (ds instanceof HikariDataSource) {
                ((HikariDataSource) ds).close();
            }
        }
    }
    private DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:exercise_db;DB_CLOSE_DELAY=-1");
        config.setUsername("sa");
        config.setPassword("sa");
        return new HikariDataSource(config);
    }
}
