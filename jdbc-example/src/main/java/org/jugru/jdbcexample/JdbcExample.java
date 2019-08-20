package org.jugru.jdbcexample;

import java.sql.*;

public class JdbcExample {
    private final String driverClassName = "org.h2.Driver";
    private final String url = "jdbc:hsqldb:mem:app";
    private final String username = "sa";
    private final String password = "sa";

    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void createTable() throws SQLException {
        String query = "CREATE TABLE USERS ( \n" +
                "   id INT NOT NULL, \n" +
                "   name VARCHAR(100) NOT NULL, \n" +
                "   email VARCHAR(100) NOT NULL, \n" +
                ");";
        Connection connection = createConnection();
        Statement statement = connection.createStatement();
        statement.execute(query);


        statement.close();
        connection.close();
    }

    public void insertData() throws SQLException {
        String query1 = "INSERT INTO USERS VALUES (1,'john', 'john@somemail.com');";
        String query2 = "INSERT INTO USERS VALUES (2,'michael', 'michael@somemail.com');";
        Connection connection = createConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();

        statement.execute(query1);
        statement.execute(query2);

        connection.commit();

        statement.close();
        connection.close();
    }

    public String readNameById(long id) throws Exception{
        String query = "SELECT * FROM USERS WHERE id = ?";
        Connection connection = createConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        String answer = null;
        if(resultSet.next()){
            answer = resultSet.getString("name");
        }

        statement.close();
        connection.close();
        return answer;
    }
}
