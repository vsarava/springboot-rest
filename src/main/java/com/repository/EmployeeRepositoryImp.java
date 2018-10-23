package com.repository;

import com.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeRepositoryImp implements EmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findAll() {

        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findAll", Employee.class);
        return query.getResultList();

    }

    @Override
    public Employee find(String id) {
        return entityManager.find(Employee.class,id);
    }

    @Override
    public Employee findByEmail(String email) {
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByEmail",Employee.class);
        query.setParameter("paramEmail",email);

        if(query.getResultList() != null){
            return query.getResultList().get(0);
        }
        else
        {
            return null;
        }

    }

    @Override
    public Employee create(Employee employee) {
       entityManager.persist(employee);
       return employee;
    }

    @Override
    public Employee update(Employee employee) {
        entityManager.merge(employee);
        return employee;
    }

    @Override
    public void delete(Employee employee) {
        entityManager.remove(employee);

    }


}
