package org.example;

public class Main {
    public static void main(String[] args) {

        Object[][] arr = {{1,2,3,4},{1,2,3,4},{1,2,3,4},{1,2,3,4}};
        Object[][] arr2 = {{1,2,3},{1,2,3,4},{1,2,3,4},{1,2,3,4}};
        Object[][] arr3 = {{1,2,3,"4"},{1,2,3,4},{1,2,3,4},{1,2,3,4}};

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
            }
            finally {
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



    public static int  sumElementArray(Object[][] arr) throws AppArrayDataException, AppArraySizeException {

        if (arr.length != 4 || arr[0].length != 4) {
            throw new AppArraySizeException("Некорректный размер массива");
        } else {

            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] instanceof Integer) {
                        sum += (Integer) arr[i][j];
                    } else {
                        throw new AppArrayDataException("Некорректный тип данных");
                    }
                }
            }
            return sum;
        }

    }

}