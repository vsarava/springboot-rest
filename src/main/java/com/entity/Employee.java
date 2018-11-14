package com.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Employee {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    public Employee() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
