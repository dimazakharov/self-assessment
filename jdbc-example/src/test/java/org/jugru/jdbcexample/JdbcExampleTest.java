package org.jugru.jdbcexample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcExampleTest {


    @AfterEach
    public void tearDown() {
        clearDatabase();
    }

    @BeforeEach
    public void tearDown1() {
        clearDatabase();
    }

    @Test
    public void createConnectionTest() throws Exception {
        try (Connection connection = new JdbcExample().createConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }

    @Test
    public void createTableTest() throws Exception {
        new JdbcExample().createTable();
        try (Connection connection = new JdbcExample().createConnection();
             ResultSet res =
                     connection.getMetaData().getTables(null, null, "USERS", new String[]{"TABLE"})) {
            assertTrue(res.next());
        }
    }

    @Test
    public void readTest() throws Exception{
        JdbcExample jdbcExample = new JdbcExample();
        jdbcExample.createTable();
        jdbcExample.insertData();

        assertEquals("michael", jdbcExample.readNameById(2L));
    }


    public void clearDatabase() {
        Connection connection = null;
        try {
            connection = new JdbcExample().createConnection();
            try {
                try (Statement stmt = connection.createStatement()) {
                    stmt.execute("DROP SCHEMA PUBLIC CASCADE");
                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
