package com.hyperlink.practical.controller;

import com.hyperlink.practical.dto.CategoryDto;
import com.hyperlink.practical.dto.ResponseDto;
import com.hyperlink.practical.enums.WorkingType;
import com.hyperlink.practical.service.CategoryService;
import com.hyperlink.practical.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseDto createCategory(@RequestBody @Validated CategoryDto categoryDto, HttpServletRequest request) {
        categoryService.createCategory(categoryDto, request);
        return ResponseDto.success(Constants.SUCCESS);
    }

    @GetMapping("/{type}")
    public List<CategoryDto> getAllComplaintsCategory(@PathVariable("type") WorkingType workingType) {
        return categoryService.getCategoryList(workingType);
    }

    @GetMapping
    public List<CategoryDto> getAllComplaintsCategory(HttpServletRequest request) {
        return categoryService.getCategoryList(request);
    }

    @DeleteMapping("/{id}")
    public ResponseDto createCategory(@PathVariable("id") Long id, HttpServletRequest request) {
        categoryService.deleteCategory(id, request);
        return ResponseDto.success(Constants.DELETED);
    }
}
