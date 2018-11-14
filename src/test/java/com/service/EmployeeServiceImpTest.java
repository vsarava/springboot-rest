package com.service;

import com.entity.Employee;
import com.exception.ResourceNotFoundException;
import com.repository.EmployeeRepository;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class EmployeeServiceImpTest {

    @TestConfiguration
    static class EmployeeServiceImpTestConfiguration{

        @Bean
        public EmployeeService getService(){
            return new EmployeeServiceImp();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    private List<Employee> employees;

    @Before
    public void setup(){
        Employee emp = new Employee();
        emp.setEmail("abc@gmail.com");
        emp.setFirstName("Vic");
        emp.setLastName("Sara");

        employees = Collections.singletonList(emp);
        //when
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);

        Mockito.when(employeeRepository.findById(emp.getId())).thenReturn(Optional.of(emp));
    }

    @After
    public void clean(){
        System.out.println("after");
    }

    @Test
    public void findAll() {
        List<Employee> result = employeeService.findAll();
        Assert.assertEquals("employee list should match", employees,result);
    }

    @Test
    public void find() {
        Employee result = employeeService.find(employees.get(0).getId());
        Assert.assertEquals("employee list should match",employees.get(0),result);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void findNotFound() {
        Employee result = employeeService.find("wedwerwdd");
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}