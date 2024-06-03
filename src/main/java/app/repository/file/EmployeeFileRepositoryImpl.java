package app.repository.file;

import app.domain.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeFileRepositoryImpl implements EmployeeFileRepository {
    private final String filePath;

    public EmployeeFileRepositoryImpl(@Value("${employee.file.path}") String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(Employee employee) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(employee.toJSON());
            bufferedWriter.newLine();
        }
    }

    @Override
    public void update(Employee employee) throws IOException {
        List<Employee> employees = findAll();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == employee.getId()) {
                employees.set(i, employee);
                saveAll(employees);
                return;
            }
        }
    }

    @Override
    public void deleteById(int id) throws IOException {
        List<Employee> employees = findAll();
        employees.removeIf(employee -> employee.getId() == id);
        saveAll(employees);
    }

    @Override
    public Optional<Employee> findById(int id) throws IOException {
        List<Employee> employees = findAll();
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }

    @Override
    public List<Employee> findAll() throws IOException {
        List<Employee> employees = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Employee employee = parseEmployee(line);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        }
        return employees;
    }

    private Employee parseEmployee(String line) {
        // Пытаемся распарсить JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(line, Employee.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveAll(List<Employee> employees) throws IOException {
        try (FileWriter writer = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            for (Employee employee : employees) {
                bufferedWriter.write(employee.toJSON());
                bufferedWriter.newLine();
            }
        }
    }
}
