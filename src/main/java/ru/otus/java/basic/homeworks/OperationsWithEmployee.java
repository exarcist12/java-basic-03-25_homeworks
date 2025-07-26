package ru.otus.java.basic.homeworks;

import java.util.ArrayList;
import java.util.List;

public class OperationsWithEmployee {

    public static List<Employee> getEmployees() {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ivan", 25));
        employees.add(new Employee("Petr", 30));
        employees.add(new Employee("Vladimir", 35));
        employees.add(new Employee("Sergey", 40));
        employees.add(new Employee("Igor", 45));
        employees.add(new Employee("Vladislav", 50));
        employees.add(new Employee("Viktor", 55));
        employees.add(new Employee("Pavel", 60));
        employees.add(new Employee("Ilya", 65));
        employees.add(new Employee("Vladimir", 70));
        employees.add(new Employee("Vladimir", 75));
        return employees;

    }

    public static List<String> getNames(List<Employee> employees) {
        List<String> names = new ArrayList<>();
        for(Employee employee : employees) {
            names.add(employee.getName());
        }
        return names;
    }

    public static List<Employee> getEmployeesWithAgeMoreThan(List<Employee> employees, int age) {
        List<Employee> employeeFilter = new ArrayList<>();
        for(Employee employee : employees) {
            if(employee.getAge() >= age) {
                employeeFilter.add(employee);
            }
        }
        return employeeFilter;
    }

    public static boolean isAllEmployeesAvgAgeMoreThan(List<Employee> employees, int avgAge) {
        int sumAge = 0;
        for(Employee employee : employees) {
            sumAge += employee.getAge();
        }
        return sumAge / employees.size() >= avgAge;
    }

    public static Employee getEmployeeWithMinAge(List<Employee> employees) {
        Employee employeeMinAge = employees.get(0);
        for(Employee employee : employees) {
            if(employee.getAge() < employeeMinAge.getAge()) {
                employeeMinAge = employee;
            }
        }
        return employeeMinAge;
    }

}
