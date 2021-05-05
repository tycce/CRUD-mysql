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
        System.out.println("��� ��������� ������� \"?\"");
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
                    System.out.println("����������� �������");
            }
        }

        READER.close();
        STUDENT_DAO.close();
    }

    private static void allCommands() {
        System.out.println("��������� �������:\n" +
                "�������� �������� - add\n" +
                "������� �������� - delete\n" +
                "������� ���� ��������� - show\n" +
                "����� - quit");
    }

    private static void add() throws IOException {
        Student newStudent = new Student();

        System.out.println("***���������� ��������***\n������� �������: ");
        newStudent.setSurname(READER.readLine());
        System.out.println("������� ���: ");
        newStudent.setName(READER.readLine());
        System.out.println("������� ��������: ");
        newStudent.setPatronymic(READER.readLine());
        try {
            System.out.println("������� ���� �������� � ������� ����-��-��: ");
            newStudent.setBirthday(Date.valueOf(READER.readLine()));
        } catch (IllegalArgumentException e) {
            System.out.println("�������� ������ ����");
        }
        System.out.println("������� ������: ");
        newStudent.setStudentGroup(READER.readLine());

        if(STUDENT_DAO.create(newStudent)) {
            System.out.println("������� ��������");
        } else {
            System.out.println("������ ��� ���������� ��������");
        }
    }

    public static void delete() throws IOException {
        System.out.print("������� id ��������: ");

        try{
            int id = Integer.parseInt(READER.readLine());
            Student student = STUDENT_DAO.getStudent(id);
            if(student == null) {
                System.out.println("������� �� ������");
            } else {
                STUDENT_DAO.deleteStudent(id);

                System.out.println(
                        "***�������***\n" +
                        student.toString() + "\n" +
                        "***�������***\n");
            }
        }catch (NumberFormatException e) {
            System.out.println("������ ����� id");
        }

    }

    public static void show() {
        List<Student> list = STUDENT_DAO.getAllStudents();

        if (list == null) {
            System.out.println("�� ������� ������� ���������");
            return;
        }

        for (Student student: STUDENT_DAO.getAllStudents()) {
            System.out.println(student.toString() + "\n");
        }
    }


}
