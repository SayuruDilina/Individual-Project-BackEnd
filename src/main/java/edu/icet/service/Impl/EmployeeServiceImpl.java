package edu.icet.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.Employee;
import edu.icet.entity.EmployeeEntity;
import edu.icet.repository.EmployeeRepository;
import edu.icet.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

private  final EmployeeRepository repository;
private  final ObjectMapper mapper;

    @Override
    public void registerEmployee(Employee employee) {
        repository.save(mapper.convertValue(employee, EmployeeEntity.class));
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees=new ArrayList<>();
        List<EmployeeEntity> all = repository.findAll();
        all.forEach(employeeEntity -> {
            employees.add(mapper.convertValue(employeeEntity, Employee.class));
        });
        return employees;
    }

    @Override
    public void updateEmployee(Employee employee) {
        repository.save(mapper.convertValue(employee, EmployeeEntity.class));
    }

    @Override
    public void deleteEmployee(Integer id) {
        repository.deleteById(id);
    }
}
