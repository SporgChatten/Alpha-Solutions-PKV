package com.alpha.solutionspkv.Service;

import com.alpha.solutionspkv.Model.Employee;
import com.alpha.solutionspkv.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    public void create(Employee employee) { employeeRepository.create(employee); }

    public void update(Employee employee) { employeeRepository.update(employee); }

    public void delete(int employeeId) { employeeRepository.delete(employeeId); }
}
