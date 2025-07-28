package org.example;

public class Main {
    public static void main(String[] args) throws AppArrayDataException, AppArraySizeException {

        String[][] arr = {{"1a", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        String[][] arr2 = {{"1", "2", "3", "4", "5"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        String[][] arr3 = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};

        try {
            System.out.println(sumElementArray(arr));
        } catch (AppArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (AppArrayDataException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                System.out.println(sumElementArray(arr2));
            } catch (AppArraySizeException e) {
                System.out.println(e.getMessage());
            } catch (AppArrayDataException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    System.out.println(sumElementArray(arr3));
                } catch (AppArraySizeException e) {
                    System.out.println(e.getMessage());
                } catch (AppArrayDataException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }


    public static int sumElementArray(String[][] arr) throws AppArrayDataException, AppArraySizeException {

        if (arr.length != 4 || arr[0].length != 4) {
            throw new AppArraySizeException("Некорректный размер массива");
        }
        int sum = 0;
        int intNumber;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    intNumber = Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new AppArrayDataException("Некорректный тип данных");
                }
                sum += intNumber;
            }
        }
        return sum;
    }
}