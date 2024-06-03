package app.repository.list;

import app.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeListRepositoryImpl implements EmployeeListRepository {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public Employee save(Employee employee) {
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        deleteById(employee.getId());
        employees.add(employee);
        return employee;
    }

    @Override
    public Optional<Employee> deleteById(int id) {
        Optional<Employee> employee = findById(id);
        employee.ifPresent(employees::remove);
        return employee;
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }
}
