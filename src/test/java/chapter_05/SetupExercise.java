package chapter_05;

import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.H2Dialect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

//import org.springframework.transaction.PlatformTransactionManager;
//import javax.persistence.*;
//import javax.sql.DataSource;
//import java.util.Date;
//import java.util.Properties;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MySpringConfig.class)
@SuppressWarnings("Duplicates") // For IntelliJ only
public class SetupExercise {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void exercise_setupSessionFactory() {
        assertNotNull(sessionFactory);
        System.out.println("We have a session factory.");
    }

}
