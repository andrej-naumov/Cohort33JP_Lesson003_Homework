package app.repository.list;

import java.util.List;
import java.util.Optional;
import app.domain.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeListRepository {
    // Метод для создания нового сотрудника
    Employee save(Employee employee);

    // Метод для обновления существующего сотрудника
    Employee update(Employee employee);

    // Метод для удаления сотрудника по его id
    Optional<Employee> deleteById(int id);

    // Метод для получения сотрудника по его id
    Optional<Employee> findById(int id);

    // Метод для получения всех сотрудников
    List<Employee> findAll();
}
