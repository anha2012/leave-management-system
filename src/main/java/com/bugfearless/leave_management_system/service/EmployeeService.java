package com.bugfearless.leave_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugfearless.leave_management_system.model.Employee;
import com.bugfearless.leave_management_system.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setFullName(employeeDetails.getFullName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setHireDate(employeeDetails.getHireDate());
            employee.setManagerId(employeeDetails.getManagerId());
            employee.setUserId(employeeDetails.getUserId());
            return employeeRepository.save(employee);
        }
        return null;
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
