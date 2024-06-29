package org.example;

import java.sql.*;

public class StudentDAO {
    public void insertStudent(Student student){
        try (
                Connection connection = DriverManager.getConnection("jdbc:h2:./studentsDB;AUTO_SERVER=TRUE");) {
            String query = "INSERT INTO students (name, age) VALUES (?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setString(1,student.getName());
            preparedStmt.setInt(2,student.getAge());
            preparedStmt.execute();
        } catch (
                SQLException e) {
            throw new RuntimeException("Error inserting student" + student, e);
        }
    }
    public void getAll(){
        try (Connection connection = DriverManager.getConnection("jdbc:h2:./studentsDB;AUTO_SERVER=TRUE");
             Statement statement = connection.createStatement()) {

            String sql = """
                    SELECT * FROM students
                    """;

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int id = resultSet.getInt("id");
                System.out.println("name: " +name + " age: " + age + " id: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading users", e);
        }
    }
    public void getById(Integer id){
        try (
                Connection connection = DriverManager.getConnection("jdbc:h2:./studentsDB;AUTO_SERVER=TRUE");) {
            String query = "SELECT * FROM students WHERE id=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setInt(1,id);
            ResultSet resultSet = preparedStmt.executeQuery();

            resultSet.next();

            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
        } catch (
                SQLException e) {
            throw new RuntimeException("User does not exist with id: " + id, e);
        }
    }
    public void updateById(Integer id,String newName,Integer newAge){
        try (
                Connection connection = DriverManager.getConnection("jdbc:h2:./studentsDB;AUTO_SERVER=TRUE");) {
                String updateQuery = "UPDATE students SET name=?, age=? WHERE id=?";
                PreparedStatement preparedStmt = connection.prepareStatement(updateQuery);

                preparedStmt.setString(1, newName);
                preparedStmt.setInt(2, newAge);
                preparedStmt.setInt(3, id);
            int rowsUpdated = preparedStmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("No user found with id: " + id);
            }


        } catch (
                SQLException e) {
            throw new RuntimeException("Error updating user with id " + id, e);
        }
    }
    public void deleteById(Integer id){
        try (
                Connection connection = DriverManager.getConnection("jdbc:h2:./studentsDB;AUTO_SERVER=TRUE");) {
            String updateQuery = "DELETE FROM students WHERE id=?";
            PreparedStatement preparedStmt = connection.prepareStatement(updateQuery);

            preparedStmt.setInt(1, id);
            int rowsDeleted = preparedStmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("No user found with id: " + id);
            }
        } catch (
                SQLException e) {
            throw new RuntimeException("Error updating user with id " + id, e);
        }
    }
}
