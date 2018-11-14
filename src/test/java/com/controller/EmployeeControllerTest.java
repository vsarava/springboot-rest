package com.controller;

import com.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.EmployeeRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    EmployeeRepository employeeRepository;


    @Before
    public void setup(){

        Employee emp = new Employee();
        emp.setId("vic-id");
        emp.setEmail("abc@gmail.com");
        emp.setFirstName("Vic");
        emp.setLastName("Sara");
        employeeRepository.save(emp);

        Employee emp1 = new Employee();
        emp1.setId("1vic-id");
        emp1.setEmail("1abc@gmail.com");
        emp1.setFirstName("1Vic");
        emp1.setLastName("1Sara");
        employeeRepository.save(emp1);
    }

    @After
    public void cleanup(){

        employeeRepository.deleteAll();

    }

    @Test
    public void findAll() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/employees")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0 ].email",Matchers.is("abc@gmail.com")));

    }

    @Test
    public void find() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/employees/vic-id")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is("abc@gmail.com")));
    }

    @Test
    public void find404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/employees/vic-iyvybhnjd")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void create() throws Exception{

        ObjectMapper mapper = new ObjectMapper();

        Employee emp = new Employee();
        emp.setEmail("2abc@gmail.com");
        emp.setFirstName("2Vic");
        emp.setLastName("2Sara");

        mvc.perform(MockMvcRequestBuilders.post("/employees")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsBytes(emp))
                    )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is("2abc@gmail.com")));
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}