package edu.icet.service;

import edu.icet.dto.Employee;

import java.util.List;

public interface EmployeeService {
    void registerEmployee(Employee employee);

    List<Employee> getAllEmployees();

    void updateEmployee(Employee employee);

    void deleteEmployee(Integer id);
}
