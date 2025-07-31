package ru.otus.java.basic.homeworks.practice36;

public class FunctionUtilsConvertNumToHex {
    public static String convertNumToHex(int input) {

        String hex = Integer.toHexString(input).toUpperCase();

        return hex;
    }

    private FunctionUtilsConvertNumToHex() {

    }

    public static void main(String[] args) {
        System.out.println(
                "При вводе числа 8191, метод должен вернуть 1FFF - '"
                        + FunctionUtilsConvertNumToHex.convertNumToHex(8191)
                        + "'"
        );
    }
}