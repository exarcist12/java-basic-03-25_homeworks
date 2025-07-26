package org.example;

import ru.otus.java.basic.homeworks.Employee;
import ru.otus.java.basic.homeworks.Operations;
import ru.otus.java.basic.homeworks.OperationsWithEmployee;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> integers = Operations.fillingArray(3, 8);
        System.out.println(integers);
        System.out.println(Operations.sumMoreFive(integers));
        System.out.println(Operations.addMyNumber(3, integers));
        System.out.println(Operations.fillingArrayMyNumber(10, integers));

        List<Employee> employees = OperationsWithEmployee.getEmployees();
        System.out.println(OperationsWithEmployee.getNames(employees));
        System.out.println(OperationsWithEmployee.getNames(OperationsWithEmployee.getEmployeesWithAgeMoreThan(employees, 50)));
        System.out.println(OperationsWithEmployee.isAllEmployeesAvgAgeMoreThan(employees, 50));
        System.out.println(OperationsWithEmployee.isAllEmployeesAvgAgeMoreThan(employees, 70));
        System.out.println(OperationsWithEmployee.getEmployeeWithMinAge(employees).getName());


    }
}