package ru.otus.java.basic;


public class Homework7 {
    public static void main(String[] args) {
        createMatrix(3, 3);
        System.out.println(sumOfPositiveElements(createMatrix(3, 3)));
        printSquare(5);
        printMatrix(printMatrixWithDiagonalsZero(createMatrix(3, 3)));
        maxValue(createMatrix(3, 3));
        System.out.println(sumStrings(createMatrix(3, 3)));
        System.out.println(sumStrings(createMatrix(1, 3)));

    }

    public static int[][] createMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = (int) (Math.random() * 18) - 9;
                System.out.printf("%3d", matrix[i][j]);
            }
            System.out.println();
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        System.out.println("Матрица выглядит следующим образом:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%3d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static int sumOfPositiveElements(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0) {
                    sum += matrix[i][j];
                }
            }
        }
        return sum;
    }

    public static void printSquare(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static int[][] printMatrixWithDiagonalsZero(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][i] = 0;
        }
        return matrix;
    }

    public static void maxValue(int[][] matrix) {
        int max = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }
        System.out.println("Максимальное значение: " + max);
    }

    public static int sumStrings(int[][] matrix) {
        int sum = 0;
        if (matrix.length > 1){
            for (int j = 0; j < matrix[1].length; j++) {
                sum += matrix[1][j];
            }
        } else return -1;

        return sum;
    }
}
