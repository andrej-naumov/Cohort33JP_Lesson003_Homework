package app.service.file;

import app.domain.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeFileService {
    void createEmployee(Employee employee) throws IOException;
    void updateEmployee(Employee employee) throws IOException;
    void deleteEmployeeById(int id) throws IOException;
    Optional<Employee> getEmployeeById(int id) throws IOException;
    List<Employee> getAllEmployees() throws IOException;
}
