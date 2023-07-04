package com.example.BlogMode.service.Impl;

import com.example.BlogMode.exception.ResourceNotFoundException;
import com.example.BlogMode.model.Category;
import com.example.BlogMode.payload.CategoryDto;
import com.example.BlogMode.repository.CategoryRepo;
import com.example.BlogMode.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category map = modelMapper.map(categoryDto, Category.class);
        Category category = categoryRepo.save(map);
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category saved = categoryRepo.save(cat);
        return modelMapper.map(saved,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        categoryRepo.delete(cat);

    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        return modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategory() {
        List<Category> cat = categoryRepo.findAll();
        List<CategoryDto> collect = cat.stream().map((c) -> modelMapper.map(c, CategoryDto.class)).collect(Collectors.toList());
        return collect;
    }
}
