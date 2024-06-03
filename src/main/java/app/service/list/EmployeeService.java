package app.service.list;

import java.util.List;
import java.util.Optional;
import app.domain.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Optional<Employee> deleteEmployeeById(int id);
    Optional<Employee> getEmployeeById(int id);
    List<Employee> getAllEmployees();
}
