package org.example;

public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("test");
        student.setAge(18);

        StudentDAO studentDAO = new StudentDAO();
        studentDAO.getAll();
    }
}