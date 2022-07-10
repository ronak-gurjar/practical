package com.hyperlink.practical.serviceImpl;

import com.hyperlink.practical.config.AuthTokenFilter;
import com.hyperlink.practical.dto.CategoryDto;
import com.hyperlink.practical.entity.Category;
import com.hyperlink.practical.enums.ERole;
import com.hyperlink.practical.enums.WorkingType;
import com.hyperlink.practical.exception.AuthorizationException;
import com.hyperlink.practical.repository.CategoryRepository;
import com.hyperlink.practical.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Override
    public List<CategoryDto> getCategoryList(WorkingType workingType) {
        return categoryRepository.findAllCategories(workingType);
    }

    @Override
    public List<CategoryDto> getCategoryList(HttpServletRequest request) {
        String role = authTokenFilter.getRole(request);
        if (role.equals(String.valueOf(ERole.ROLE_ADMIN))) {
            return categoryRepository.findAllComplaintCategory();
        }
        return new ArrayList<>();
    }

    @Override
    public void createCategory(CategoryDto categoryDto, HttpServletRequest request) {
        String role = authTokenFilter.getRole(request);
        if (role.equals(String.valueOf(ERole.ROLE_ADMIN))) {
            Category category = new Category();
            category.setCategoryId(categoryDto.getCategoryId());
            category.setCategoryType(categoryDto.getCategoryType());
            categoryRepository.save(category);
        } else {
            throw new AuthorizationException("only admin can create categories");
        }
    }

    @Override
    public void deleteCategory(Long id, HttpServletRequest request) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category category1 = category.get();
            String role = authTokenFilter.getRole(request);
            if (role.equals(String.valueOf(ERole.ROLE_ADMIN))) {
                categoryRepository.deleteCategory(category1.getCategoryId());
            }
        }
    }
}
