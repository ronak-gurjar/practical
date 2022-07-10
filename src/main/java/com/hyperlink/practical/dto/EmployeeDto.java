package com.hyperlink.practical.dto;

import com.hyperlink.practical.enums.EmpStatus;
import com.hyperlink.practical.enums.WorkingType;


public class EmployeeDto {

    private Long id;

    private String name;

    private String photo;

    private Long mobile;

    private String email;

    private String password;

    private WorkingType workingType;

    private EmpStatus status;

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
