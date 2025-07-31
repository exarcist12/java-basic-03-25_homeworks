package ru.otus.java.basic.homeworks.practice35;

public class FunctionUtilsShiftZeroes {
    public static int shiftZeroes(int input) {
        String binary = Integer.toBinaryString(input);
        binary = binary.replace("0", "1");
        int result = Integer.parseInt(binary, 2);
        return result;
    }
    private FunctionUtilsShiftZeroes() {

    }

    public static void main(String[] args) {
        System.out.println(
                "При вводе числа 3435, метод должен вернуть 4095 - '"
                        + FunctionUtilsShiftZeroes.shiftZeroes(3435)
                        + "'"
        );
    }
}