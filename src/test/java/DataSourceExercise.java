import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DataSourceExercise {
    private static final Integer NO_TIMEOUT = 0;

    @Test
    public void exercise() {
        DataSource ds = getDataSource();
        try (Connection connection = ds.getConnection()) {
            System.out.println("Successful got DB connection, using datasource.");
        } catch (SQLException e) {fail(e.getMessage());}
    }
    private DataSource getDataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:exercise_db;DB_CLOSE_DELAY=-1");
        ds.setUser("sa");
        ds.setPassword("sa");
        // This will register with a JNDI service, ignore for now
        // Context ctx = new InitialContext();
        // ctx.bind("jdbc/datasource", ds);
        return ds;
    }
}
