package ru.tycce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;

public class Main {

    private static final StudentDAO STUDENT_DAO = new StudentDAO();
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        String command = "";
        System.out.println("Для подсказки введите \"?\"");
        while(!(command = READER.readLine()).toLowerCase().equals("quit")) {

            switch (command) {
                case "?":
                    allCommands();
                    break;
                case "add":
                    add();
                    break;
                case "delete":
                    delete();
                    break;
                case "show":
                    show();
                    break;
                default:
                    System.out.println("Неизвестная команда");
            }
        }

        READER.close();
        STUDENT_DAO.close();
    }

    private static void allCommands() {
        System.out.println("Доступные команды:\n" +
                "Добавить студента - add\n" +
                "Удалить студента - delete\n" +
                "Вывести всех студентов - show\n" +
                "Выйти - quit");
    }

    private static void add() throws IOException {
        Student newStudent = new Student();

        System.out.println("***Добавление студента***\nВведите фамилию: ");
        newStudent.setSurname(READER.readLine());
        System.out.println("Введите имя: ");
        newStudent.setName(READER.readLine());
        System.out.println("Введите отчество: ");
        newStudent.setPatronymic(READER.readLine());
        try {
            System.out.println("Введите дату рождения в формате гггг-мм-дд: ");
            newStudent.setBirthday(Date.valueOf(READER.readLine()));
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный формат даты");
        }
        System.out.println("Введите группу: ");
        newStudent.setStudentGroup(READER.readLine());

        if(STUDENT_DAO.create(newStudent)) {
            System.out.println("Студент добавлен");
        } else {
            System.out.println("Ошибка при добавлении студента");
        }
    }

    public static void delete() throws IOException {
        System.out.print("Введите id студента: ");

        try{
            int id = Integer.parseInt(READER.readLine());
            Student student = STUDENT_DAO.getStudent(id);
            if(student == null) {
                System.out.println("Студент не найден");
            } else {
                STUDENT_DAO.deleteStudent(id);

                System.out.println(
                        "***Удалено***\n" +
                        student.toString() + "\n" +
                        "***Удалено***\n");
            }
        }catch (NumberFormatException e) {
            System.out.println("Ошибка ввода id");
        }

    }

    public static void show() {
        List<Student> list = STUDENT_DAO.getAllStudents();

        if (list == null) {
            System.out.println("Не удалось вывести студентов");
            return;
        }

        for (Student student: STUDENT_DAO.getAllStudents()) {
            System.out.println(student.toString() + "\n");
        }
    }


}
