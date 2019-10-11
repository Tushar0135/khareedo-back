package com.caseStudy.Khareedo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    private long mobno;
    private String email;
    private String role;
    private String password;

    public Users()
    {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false, columnDefinition = "int default '1'")
    private int active;


    public String getFirstname() {
        return firstname;
    }

    public Users(String firstname, String lastname, long mobno, String email, String role, String password, int active) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobno = mobno;
        this.email = email;
        this.role = role;
        this.password = password;
        this.active = active;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public long getMobno() {
        return mobno;
    }

    public void setMobno(long mobno) {
        this.mobno = mobno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

}
