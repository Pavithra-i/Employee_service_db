package com.example.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.client.DepartmentClient;
import com.example.entity.Employee;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentClient departmentClient;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentClient departmentClient) {
        this.employeeRepository = employeeRepository;
        this.departmentClient = departmentClient;
    }

    public Employee createEmployee(Employee employee) {
        departmentClient.getDepartmentById(employee.getDepartmentId());

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException(
                    "Email already exists: " + employee.getEmail());
        }

        employee.setId(null);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with ID: " + id));
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = getEmployeeById(id);

        departmentClient.getDepartmentById(updatedEmployee.getDepartmentId());

        employeeRepository.findByEmail(updatedEmployee.getEmail())
                .filter(employee -> !employee.getId().equals(id))
                .ifPresent(employee -> {
                    throw new IllegalArgumentException(
                            "Email already exists: " + updatedEmployee.getEmail());
                });

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setDesignation(updatedEmployee.getDesignation());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        existingEmployee.setDepartmentId(updatedEmployee.getDepartmentId());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }

    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findAll()
                .stream()
                .filter(employee -> employee.getDepartmentId().equals(departmentId))
                .toList();
    }
}
