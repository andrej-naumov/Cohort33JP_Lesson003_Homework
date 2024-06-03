package app.repository.file;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import app.domain.Employee;

public interface EmployeeFileRepository {
    void save(Employee employee) throws IOException;
    void update(Employee employee) throws IOException;
    void deleteById(int id) throws IOException;
    Optional<Employee> findById(int id) throws IOException;
    List<Employee> findAll() throws IOException;
}
