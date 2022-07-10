package com.hyperlink.practical.repository;

import com.hyperlink.practical.dto.CategoryDto;
import com.hyperlink.practical.entity.Category;
import com.hyperlink.practical.enums.WorkingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("select new com.hyperlink.practical.dto.CategoryDto(c.categoryId,c.categoryType) from Category c " +
            " inner join Complaint p on p.categoryId= c.categoryId" +
            " inner join Employee e on p.employeeId= e.id" +
            " where e.workingType=:workingType")
    List<CategoryDto> findAllCategories(@Param("workingType") WorkingType workingType);


    @Query("select new com.hyperlink.practical.dto.CategoryDto(c.categoryId,c.categoryType) from Category c " +
            " inner join Complaint p on p.categoryId= c.categoryId" +
            " inner join Employee e on p.employeeId= e.id")
    List<CategoryDto> findAllComplaintCategory();


    @Query("Update Category c SET c.delete='DELETED' where c.categoryId=:categoryId")
    void deleteCategory(@Param("categoryId") Long categoryId);
}

