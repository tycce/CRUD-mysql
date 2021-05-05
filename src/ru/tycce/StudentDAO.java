package ru.tycce;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/crud_mysql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ASwm?_26";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер JDBC не найден");
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к базе данных");
        }
    }

    public boolean create(Student newStudent) {
        try {
            String sql = "INSERT INTO students VALUES(null, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newStudent.getName());
            preparedStatement.setString(2, newStudent.getSurname());
            preparedStatement.setString(3, newStudent.getPatronymic());
            preparedStatement.setDate(4, newStudent.getBirthday());
            preparedStatement.setString(5, newStudent.getStudentGroup());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) { return false; }
    }

    public Student getStudent(int id) {

        Student student = null;

        try {
            String sql = "SELECT * FROM students WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            student = new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("patronymic"),
                    resultSet.getDate("birthday"),
                    resultSet.getString("student_group"));
        } catch (SQLException e) {}

        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = null;

        try {
            String sql = "SELECT * FROM students";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            studentList = new ArrayList<>();

            while(resultSet.next()) {
                studentList.add(new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("patronymic"),
                        resultSet.getDate("birthday"),
                        resultSet.getString("student_group")));
            }

        } catch (SQLException e) {}

        return studentList;
    }

    public void deleteStudent(int id) {
        try {
            String sql = "DELETE FROM students where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении студента");
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Не удалось закрыть подключение к базе данных");
        }
    }

}
