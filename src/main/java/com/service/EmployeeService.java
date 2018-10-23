package com.service;

import com.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee find(String id);

    Employee create( Employee employee);

    Employee update(String id, Employee employee);

    void delete(String id);
}
