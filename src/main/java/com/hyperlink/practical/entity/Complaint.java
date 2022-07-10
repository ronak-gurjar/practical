package com.hyperlink.practical.entity;

import com.hyperlink.practical.enums.ComplaintStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private Category category;

    @Column(name = "category_id",nullable = false)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
    @JoinColumn(name = "employee_id",insertable = false,updatable = false)
    private Employee employee;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "image")
    private String image;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "reply")
    private String reply;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private ComplaintStatus status =ComplaintStatus.PENDING;

    @Column(name = "replied_date")
    private Date repliedDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public Date getRepliedDate() {
        return repliedDate;
    }

    public void setRepliedDate(Date repliedDate) {
        this.repliedDate = repliedDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
