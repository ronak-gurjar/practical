package com.hyperlink.practical.serviceImpl;

import com.hyperlink.practical.config.AuthTokenFilter;
import com.hyperlink.practical.dto.EmployeeDto;
import com.hyperlink.practical.entity.Complaint;
import com.hyperlink.practical.entity.Employee;
import com.hyperlink.practical.enums.ERole;
import com.hyperlink.practical.exception.AuthorizationException;
import com.hyperlink.practical.exception.ConflictException;
import com.hyperlink.practical.exception.NotFoundException;
import com.hyperlink.practical.repository.ComplaintRepository;
import com.hyperlink.practical.repository.EmployeeRepository;
import com.hyperlink.practical.service.EmployeeService;
import com.hyperlink.practical.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Override
    public void createEmployee(EmployeeDto employeeDto, HttpServletRequest request) {
        String role = authTokenFilter.getRole(request);
        if (role.equalsIgnoreCase(String.valueOf(ERole.ROLE_ADMIN))) {
            Employee employee = new Employee();
            checkIfEmployeeAlreadyPresent(employeeDto.getEmail());
            employee.setName(employeeDto.getName());
            employee.setPhoto(employeeDto.getPhoto());
            employee.setMobile(employeeDto.getMobile());
            employee.setEmail(employeeDto.getEmail());
            employee.setPassword(employeeDto.getPassword());
            employee.setWorkingType(employeeDto.getWorkingType());
            employee.setStatus(employeeDto.getStatus());
            employeeRepository.save(employee);
        }
        else {
            throw new AuthorizationException("only admin can create employee");
        }
    }

    @Override
    public void updateEmployee(Long employeeId, EmployeeDto employeeDto,HttpServletRequest request) {
        String role = authTokenFilter.getRole(request);
        if (role.equalsIgnoreCase(String.valueOf(ERole.ROLE_ADMIN))) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                employee.setName(employeeDto.getName());
                employee.setPhoto(employeeDto.getPhoto());
                employee.setPassword(employeeDto.getPassword());
                employee.setMobile(employeeDto.getMobile());
                employeeRepository.save(employee);
            } else {
                throw new NotFoundException("Employee Not Found");
            }
        }else {
            throw new AuthorizationException("only admin can update employee");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteEmployee(Long employeeId, HttpServletRequest request) {
        String role = authTokenFilter.getRole(request);
        if (role.equalsIgnoreCase(String.valueOf(ERole.ROLE_ADMIN))) {
            Optional<Employee> employee1 = employeeRepository.findById(employeeId);
            if(employee1.isPresent()){
                Employee employee = employee1.get();
                employeeRepository.deleteEmployee(employee.getId());
                complaintRepository.deleteComplaint(employee.getId());
            }else{
                throw new NotFoundException("employee not found");
            }
        }
        else {
            throw new AuthorizationException("only admin can delete employee");
        }
    }

    public boolean checkIfEmployeeAlreadyPresent(String email) {
        Long count = employeeRepository.findEmployeeByEmail(email);
        if (count > 0) {
            throw new ConflictException(Constants.EMPLOYEE_ALREADY_PRESENT_WITH_SAME_EMAIL);
        }
        return true;
    }
}
