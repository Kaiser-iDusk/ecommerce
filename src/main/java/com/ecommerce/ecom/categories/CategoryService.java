package com.ecommerce.ecom.categories;

import com.ecommerce.ecom.categories.dtos.CategoryMapper;
import com.ecommerce.ecom.categories.dtos.CategoryResponse;
import com.ecommerce.ecom.categories.dtos.CreateCategoryRequest;
import com.ecommerce.ecom.categories.dtos.UpdateCategoryRequest;
import com.ecommerce.ecom.categories.exceptions.InvalidCategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repo;

    public List<CategoryResponse> getAll(){
        return repo.findAll().stream().map(CategoryMapper::toResponse).toList();
    }

    public CategoryResponse getById(Long id){
        Category category = repo.findById(id).orElseThrow(
                () -> new InvalidCategoryException(id)
        );
        return CategoryMapper.toResponse(category);
    }

    public CategoryResponse getByName(String name){
        Optional<Category> category = repo.findByName(name);
        if(category.isEmpty()){
            throw new InvalidCategoryException(name);
        }
        return CategoryMapper.toResponse(category.get());
    }

    public CategoryResponse createCategory(CreateCategoryRequest request){
        return CategoryMapper.toResponse(repo.save(CategoryMapper.toCategory(request)));
    }

    @Transactional
    public CategoryResponse updateCategory(UpdateCategoryRequest request){
        Category category = repo.findById(request.id()).orElseThrow(
                () -> new InvalidCategoryException(request.id())
        );
        if(request.name() != null){
            category.setName(request.name());
        }
        if(request.description() != null){
            category.setDescription(request.description());
        }
        category = repo.save(category);
        return CategoryMapper.toResponse(category);
    }

    @Transactional
    public void deleteCategory(Long id){
        Category category = repo.findById(id).orElseThrow(
                () -> new InvalidCategoryException(id)
        );
        repo.deleteById(category.getId());
    }
}
