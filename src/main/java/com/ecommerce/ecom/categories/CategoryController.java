package com.ecommerce.ecom.categories;

import com.ecommerce.ecom.categories.dtos.CategoryResponse;
import com.ecommerce.ecom.categories.dtos.CreateCategoryRequest;
import com.ecommerce.ecom.categories.dtos.UpdateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping("/list")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> createCategory(
            @Validated @RequestBody CreateCategoryRequest request
            ){
        return ResponseEntity.ok(service.createCategory(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Long id, @Validated @RequestBody UpdateCategoryRequest request
            ){
        return ResponseEntity.ok(service.updateCategory(request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        service.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
