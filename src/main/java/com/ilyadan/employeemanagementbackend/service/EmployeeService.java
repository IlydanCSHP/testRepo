package com.ilyadan.employeemanagementbackend.service;

import com.ilyadan.employeemanagementbackend.exception.ResourceNotFoundException;
import com.ilyadan.employeemanagementbackend.model.Employee;
import com.ilyadan.employeemanagementbackend.repository.EmployeeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Employee getEmployeeById(Long id) throws ResourceNotFoundException {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find this employee"));
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee, Long id) {
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find this employee"));

        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        getEmployeeById(id);

        employeeRepository.deleteById(id);
    }
}
