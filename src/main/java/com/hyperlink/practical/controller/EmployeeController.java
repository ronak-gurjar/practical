package com.hyperlink.practical.controller;

import com.hyperlink.practical.config.AuthTokenFilter;
import com.hyperlink.practical.dto.AuthDetails;
import com.hyperlink.practical.dto.EmployeeDto;
import com.hyperlink.practical.dto.ResponseDto;
import com.hyperlink.practical.service.EmployeeService;
import com.hyperlink.practical.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AuthTokenFilter authTokenFilter;

    @PostMapping("/employee")
    public ResponseDto createEmployee(@RequestBody @Validated EmployeeDto employeeDto, HttpServletRequest request) {
        employeeService.createEmployee(employeeDto,request);
        return ResponseDto.success(Constants.SUCCESS);
    }

    @PutMapping("/employee/{id}")
    public ResponseDto updateEmployeeDetail(@RequestBody EmployeeDto employeeDto, @PathVariable("id") Long employeeId,HttpServletRequest request) {
        employeeService.updateEmployee(employeeId, employeeDto,request);
        return ResponseDto.success(Constants.UPDATED);
    }
    @DeleteMapping("/employee/{id}")
    public ResponseDto updateEmployeeDetail(@PathVariable("id") Long employeeId,HttpServletRequest request) {
        employeeService.deleteEmployee(employeeId,request);
        return ResponseDto.success(Constants.DELETED);
    }
}
