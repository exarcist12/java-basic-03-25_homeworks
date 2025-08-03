package org.example;

import ru.otus.java.basic.homeworks.Server;

public class Main {
    public static void main(String[] args) {
        String x = Server.calculation("1+(2+(3+4)+5)+6");
        System.out.println(x);
        x = Server.calculation("1+(2+3)-(3+5)+7");
        System.out.println(x);
        x = Server.calculation("(55+66)-(9+5)");
        System.out.println(x);
    }
}