package app.service.list;

import java.util.List;
import java.util.Optional;
import app.domain.Employee;
import app.repository.list.EmployeeListRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeListRepository employeeListRepository;


    private final double maxSalary;

    private final double different;

    // Конструктор для внедрения зависимости (Dependency Injection)
    public EmployeeServiceImpl(EmployeeListRepository employeeListRepository, @Value("${max.salary}") double maxSalary,@Value("${max.different}")  double different) {
        this.employeeListRepository = employeeListRepository;
        this.maxSalary = maxSalary;
        this.different = different;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employee.getSalary() > maxSalary ) { //  новым сотрудникам нельзя получать высокую зарплату :P
            throw new IllegalArgumentException("Salary exceeds maximum allowed salary");
        }
        return employeeListRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        return employeeListRepository.update(employee);
    }

    @Override
    public Optional<Employee> deleteEmployeeById(int id) {
        return employeeListRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeListRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeListRepository.findAll();
    }
}
