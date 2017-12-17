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
* Connections are by default in auto-commit mode.  