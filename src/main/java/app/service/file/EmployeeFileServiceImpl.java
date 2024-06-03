package app.service.file;

import app.domain.Employee;
import app.repository.file.EmployeeFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeFileServiceImpl implements EmployeeFileService {
    private final EmployeeFileRepository employeeRepository;

    @Autowired
    public EmployeeFileServiceImpl(EmployeeFileRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void createEmployee(Employee employee) throws IOException {
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) throws IOException {
        employeeRepository.update(employee);
    }

    @Override
    public void deleteEmployeeById(int id) throws IOException {
        employeeRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) throws IOException {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() throws IOException {
        return employeeRepository.findAll();
    }
}
