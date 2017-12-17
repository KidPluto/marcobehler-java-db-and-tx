# Notes

* The h2 database used for all examples is an in memory database, resulting in no username and password being needed.
* The very first example shows that the automatic resource closing feature of Java 7 eliminates the need for the following code:
```java
} finally {
    if (conn != null) {
        try {
            conn.close();
        } catch (SQLExecption e) {log.error(e.printStackTrace()); }
    }
}
````
* H2 database DB_CLOSE_DELAY value of -1 means the database is never closed until the close delay is set to some other value or SHUTDOWN
* Connections are by default in auto-commit mode.  Meaning that each statement sent to the DB will be executed stand-alone.
* A database transaction is a collection of SQL queries which forms a logical task.
  * https://www.tutorialspoint.com/sql/sql-transactions.htm
  * Data must be kept consistent.  In the event of a failure the data must remain in the previous state, before the start of a transaction.
* Chapter 3
  * Up to this point we have been using DriverManager.  In most cases you would be using DataSource, which supports the following features:
    * JNDI
    * Connection pooling
    * Distributed transactions
  * datasource is an interface and can have many different implementations
    * One of the h2 implementations is JdbcDataSource (which doesn't support pooling or distri transactions)
* Chapter 4
  * Spring introduces the concept of PlatformTransactionManager

## Errors
* Error #1
````
org.h2.jdbc.JdbcSQLException: Unsupported connection setting " DB_CLOSE_DELAY" [90113-187]
````   
Caused by space in DB connection string
````java
return DriverManager.getConnection("jdbc:h2:mem:exercise_db; DB_CLOSE_DELAY=-1");
````
* Error #2
````
java.lang.AssertionError: No suitable driver found for jbdc:h2:mem:exercise_db;DB_CLOSE_DELAY=-1
````
Caused by typo
````java
ds.setURL("jbdc:h2:mem:exercise_db;DB_CLOSE_DELAY=-1");
````
