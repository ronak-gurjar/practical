package com.hyperlink.practical.entity;

import com.hyperlink.practical.enums.EmpStatus;
import com.hyperlink.practical.enums.WorkingType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "photo")
    private String photo;

    @Column(name = "mobile")
    private Long mobile;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "working_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkingType workingType;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private EmpStatus status =EmpStatus.ACTIVE;

    @Column(name = "delete_status")
    private String delete;

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
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

    public WorkingType getWorkingType() {
        return workingType;
    }

    public void setWorkingType(WorkingType workingType) {
        this.workingType = workingType;
    }

    public EmpStatus getStatus() {
        return status;
    }

    public void setStatus(EmpStatus status) {
        this.status = status;
    }

}
