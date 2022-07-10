package com.hyperlink.practical.repository;

import com.hyperlink.practical.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("Select count(employee) from Employee employee where employee.email=:email")
    Long findEmployeeByEmail(@Param("email") String email);

    @Query("Update Employee e SET e.delete='DELETED' where e.id=:id")
    void deleteEmployee(@Param("id") Long id);
}
