package com.service;

import com.entity.Employee;
import com.exception.BadRequestException;
import com.exception.ResourceNotFoundException;
import com.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {

    return repository.findAll();

    }

    @Override
    public Employee find(String id) {

        Employee emp = repository.find(id);
        if (emp == null){
            throw  new ResourceNotFoundException("Employee with id = "+id+" NOT FOUND");
        }
        else{
            return emp;
        }


    }

    @Override
    @Transactional
    public Employee create(Employee employee) {
        Employee existing = repository.findByEmail(employee.getEmail());
        if(existing != null){
            throw new BadRequestException("Employee with email = "+employee.getEmail()+" already exists.");
        }
        return repository.create(employee);
    }

    @Override
    @Transactional
    public Employee update(String id, Employee employee) {
        Employee existing = repository.find(id);
        if(existing == null){

            throw new ResourceNotFoundException("Employee with id =" + id +" does not exist.");

        }
        return repository.update(employee);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Employee existing = repository.find(id);
        if(existing == null){
            throw new ResourceNotFoundException("Employee with id =" + id +" does not exist.");
        }
        repository.delete(existing);

    }
}
