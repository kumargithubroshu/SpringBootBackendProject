package com.example.BlogMode.controller;

import com.example.BlogMode.payload.ApiResponse;
import com.example.BlogMode.payload.CategoryDto;
import com.example.BlogMode.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createData(@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateData(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId)
    {
        CategoryDto categoryDto1 = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(categoryDto1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteData(@PathVariable Integer categoryId)
    {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("data deleted successfully" +categoryId, true), HttpStatus.OK);
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity<CategoryDto> getDataById(@PathVariable Integer categoryId)
    {
        CategoryDto categoryById = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryById,HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<CategoryDto>> getData()
    {
        List<CategoryDto> category = categoryService.getCategory();
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
}
