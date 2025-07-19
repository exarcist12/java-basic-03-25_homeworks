package ru.otus.java.basic.homeworks;

public class User {
    private String secondName;
    private String name;
    private String patronymic;
    private int yearOfBirth;
    private String email;

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public User(String secondName, String name, String patronymic, int yearOfBirth, String email) {
        this.secondName = secondName;
        this.name = name;
        this.patronymic = patronymic;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
    }

    public void getInfo(User user) {
        System.out.println();
        System.out.println("ФИО: " + user.secondName + " " + user.name + " " + user.patronymic);
        System.out.println("Год рождения: " + user.yearOfBirth);
        System.out.println("Email: " + user.email);
    }

}
