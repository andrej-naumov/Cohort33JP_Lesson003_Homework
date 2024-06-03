package client;

import app.AppConfig;
import app.controller.list.EmployeeListController;
import app.controller.file.EmployeeFileController;
import app.domain.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        EmployeeListController employeeListController = context.getBean(EmployeeListController.class);
        EmployeeFileController employeeFileController = context.getBean(EmployeeFileController.class);

        // Создание новых сотрудников
        Employee emp1 = new Employee(1, "John Doe", "Developer", 60000, "IT");
        Employee emp2 = new Employee(2, "Jane Smith", "Manager", 80000, "HR");

        employeeListController.createEmployee(emp1);
        employeeListController.createEmployee(emp2);

        try {
            employeeFileController.createEmployee(emp1);
            employeeFileController.createEmployee(emp2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Получение всех сотрудников
        List<Employee> employees = employeeListController.getAllEmployees();
        System.out.println("All Employees: " + employees);

        try {
            employees = employeeFileController.getAllEmployees();
            System.out.println("All Employees from file: " + employees);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Обновление сотрудника
        emp1.setSalary(65000);
        employeeListController.updateEmployee(emp1);
        emp2.setSalary(12000);
        try {
            employeeFileController.updateEmployee(emp2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Получение сотрудника по id
        Optional<Employee> emp_1 = employeeListController.getEmployeeById(1);
        emp_1.ifPresent(System.out::println);
        Optional<Employee> emp_2;
        try {
            emp_2 = employeeFileController.getEmployeeById(2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        emp_2.ifPresent(System.out::println);

        // Удаление сотрудника в памяти
        employeeListController.deleteEmployeeById(2);
        // Удаление сотрудника на диске
        try {
            employeeFileController.deleteEmployeeById(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Получение всех сотрудников после удаления
        employees = employeeListController.getAllEmployees();
        System.out.println("All Employees in memory after deletion: " + employees);

        try {
            employees = employeeFileController.getAllEmployees();
            System.out.println("All Employees in file after deletion: " + employees);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
