package com.repository;

import com.entity.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAll();

    Employee find(String id);

    Employee findByEmail(String email);

    Employee create(Employee employee);

    Employee update(Employee employee);

    void delete(Employee employee);

}
