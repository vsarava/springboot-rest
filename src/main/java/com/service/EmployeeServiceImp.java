package com.service;

import com.entity.Employee;
import com.exception.BadRequestException;
import com.exception.ResourceNotFoundException;
import com.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {

    return (List<Employee>) repository.findAll();

    }

    @Override
    public Employee find(String id) {

        Optional<Employee> emp = repository.findById(id);
        if (!emp.isPresent()){
            throw  new ResourceNotFoundException("Employee with id = "+id+" NOT FOUND");
        }
        else{
            return emp.get();
        }


    }

    @Override
    @Transactional
    public Employee create(Employee employee) {
        Optional<Employee> existing = repository.findByEmail(employee.getEmail());
        if(!existing.isPresent()){
            throw new BadRequestException("Employee with email = "+employee.getEmail()+" already exists.");
        }
        return repository.save(employee);
    }

    @Override
    @Transactional
    public Employee update(String id, Employee employee) {
        Optional<Employee> existing = repository.findById(id);
        if(!existing.isPresent()){

            throw new ResourceNotFoundException("Employee with id =" + id +" does not exist.");

        }
        return repository.save(employee);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Optional<Employee> existing = repository.findById(id);
        if(!existing.isPresent()){
            throw new ResourceNotFoundException("Employee with id =" + id +" does not exist.");
        }
        repository.delete(existing.get());

    }
}
