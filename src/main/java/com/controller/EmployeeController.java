package com.controller;

import com.entity.Employee;
import com.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Controller
//@ResponseBody
@RestController
@RequestMapping(value = "employees")
@Api(description = "All about Employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find all Employees",
                    notes = "Returns a list of employees from the database")
 //   @ResponseBody
    public List<Employee> findAll(){

        return service.findAll();

    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
 //   @ResponseBody
    public Employee find(@PathVariable("id") String empId){

        return service.find(empId);
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  //  @ResponseBody
    public Employee create(@RequestBody Employee employee){

        return service.create(employee);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
 //   @ResponseBody
    public Employee update(@PathVariable("id") String empId, @RequestBody Employee employee){

        return service.update(empId,employee);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
  //  @ResponseBody
    public void delete(@PathVariable("id") String empId){

        service.delete(empId);

    }
}
