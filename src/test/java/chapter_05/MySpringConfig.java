package chapter_05;

import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.dialect.H2Dialect;
import org.omg.CORBA.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class MySpringConfig {

    @Bean
    public DataSource dataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:exercise_db;DB_CLOSE_DELAY=-1");
        ds.setUser("sa");
        ds.setPassword("sa");
        return ds;
    }
    // There are different LocalSessionFactoryBeans, depending on which Hibernate version you are using.
    // This will let Hibernate auto-create our database here
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean result = new LocalSessionFactoryBean();
        // Set database
        result.setDataSource(dataSource());
        // All the mappings we want Hibernate to know
        result.setAnnotatedClasses(Event.class);
        // Properties: dialect, auto-creation of database, etc
        Properties hibernateProperties = new Properties();
        // Code I found on the internets
        hibernateProperties.setProperty("hibernate.dialect", H2Dialect.class.getName());
        // Original code
        // hibernateProperties.setProperty(Environment.DIALECT, H2Dialect.class.getName());

        // hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "create-drop");
        // hibernateProperties.setProperty(Environment.SHOW_SQL, "true");
        // hibernateProperties.setProperty(Environment.FORMAT_SQL, "true");
        result.setHibernateProperties(hibernateProperties);
        return result;
    }
    @Bean
    public PlatformTransactionManager txManager() {
        return new HibernateTransactionManager( sessionFactory().getObject());
    }
}
