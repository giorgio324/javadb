package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDatabase {
    private static final String jdbcURL = "jdbc:h2:./studentsDB;AUTO_SERVER=TRUE";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(jdbcURL)) {
            System.out.println("Database 'studentsDB' created successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("error creating db", e);
        }
    }
}
