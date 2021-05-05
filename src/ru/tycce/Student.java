package ru.tycce;

import java.sql.Date;

public class Student {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthday;
    private String studentGroup;

    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Student(int id, String name, String surname,
                   String patronymic, Date birthday, String studentGroup) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.studentGroup = studentGroup;
    }

    @Override
    public String toString() {
        return "#" + id + " " + surname + " " + name + " " + patronymic + "\n"
                + "Дата рождения: " + birthday + "\n"
                + "Группа: " + studentGroup;
    }
}
