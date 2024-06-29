package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:./studentsDB;AUTO_SERVER=TRUE");
             Statement statement = connection.createStatement()) {

            String sql = """
                    CREATE TABLE IF NOT EXISTS students (
                        ID IDENTITY NOT NULL PRIMARY KEY,
                        name VARCHAR(50),
                        age INT
                    )
                    """;

            statement.execute(sql);
            System.out.println("Table 'students' created successfully.");

        } catch (SQLException e) {
            throw new RuntimeException("Error creating table 'students'", e);
        }
    }
}
