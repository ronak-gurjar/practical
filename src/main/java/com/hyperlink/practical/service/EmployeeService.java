package com.hyperlink.practical.service;

import com.hyperlink.practical.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;

public interface EmployeeService {

    void createEmployee(EmployeeDto employeeDto, HttpServletRequest request);

    void updateEmployee(Long employeeId, EmployeeDto employeeDto,HttpServletRequest request);

    void deleteEmployee(Long employeeId, HttpServletRequest request);
}
