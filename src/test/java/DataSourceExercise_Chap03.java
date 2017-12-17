import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import javax.sql.DataSource;
import java.sql.Connection;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)

// I didn't know you could to this.  Have your Spring configs in another class.
@ContextConfiguration(classes = DataSourceExercise_Chap03_SpringConfig.class)
@SuppressWarnings("Duplicates") // for IntelliJ only
public class DataSourceExercise_Chap03 {
    private static final TransactionDefinition TX_DEFAULTS = null;

    @Autowired
    private DataSource ds;

    @Autowired
    private PlatformTransactionManager txManager;

    @Test
    public void exercise() {
        assertNotNull(ds);
        // Get connections
        try (Connection connection = ds.getConnection()) {
            System.out.println("Got the connection.");
            assertTrue(connection.isValid(1000));
        } catch (Exception e) {
            fail(e.getMessage());
        }
        // Check that txManager has been created
        assertNotNull(txManager);

        // Spring's low-level way of programmatically opening up transactions
        TransactionStatus transaction = txManager.getTransaction(TX_DEFAULTS);
        System.out.println("Have open transaction");

        // Commit, and close
        txManager.commit(transaction);
    }
}