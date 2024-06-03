package app.controller.file;

import app.domain.Employee;
import app.service.file.EmployeeFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeFileController {
    private final EmployeeFileService employeeFileService;

    @Autowired
    public EmployeeFileController(EmployeeFileService employeeFileService) {
        this.employeeFileService = employeeFileService;
    }

    public void createEmployee(Employee employee) throws IOException {
        employeeFileService.createEmployee(employee);
    }

    public void updateEmployee(Employee employee) throws IOException {
        employeeFileService.updateEmployee(employee);
    }

    public void deleteEmployeeById(int id) throws IOException {
        employeeFileService.deleteEmployeeById(id);
    }

    public Optional<Employee> getEmployeeById(int id) throws IOException {
        return employeeFileService.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() throws IOException {
        return employeeFileService.getAllEmployees();
    }
}
