package com.hyperlink.practical.service;

import com.hyperlink.practical.dto.CategoryDto;
import com.hyperlink.practical.entity.Category;
import com.hyperlink.practical.enums.WorkingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CategoryService {

    List<CategoryDto> getCategoryList(WorkingType workingType);

    List<CategoryDto> getCategoryList(HttpServletRequest request);

    void createCategory(CategoryDto categoryDto, HttpServletRequest request);

    void deleteCategory(Long id,HttpServletRequest request);
}
