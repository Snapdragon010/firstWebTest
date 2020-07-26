package com.github.drogon.model;

public class User {
    private static  int id;
    private static  String name;
    private static  String password;
    private static  String age;


    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        User.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static String getAge() {
        return age;
    }

    public static void setAge(String age) {
        User.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
