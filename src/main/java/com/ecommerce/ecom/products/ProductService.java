package com.ecommerce.ecom.products;

import com.ecommerce.ecom.categories.Category;
import com.ecommerce.ecom.categories.CategoryRepository;
import com.ecommerce.ecom.categories.exceptions.InvalidCategoryException;
import com.ecommerce.ecom.products.dtos.*;
import com.ecommerce.ecom.products.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo repo;
    private final CategoryRepository categoryRepo;

    public ProductResponse create(CreateProductRequest productRequest) {
        Category cat = categoryRepo.findById(productRequest.categoryId()).orElseThrow(
                () -> new InvalidCategoryException(productRequest.categoryId())
        );
        Product product = ProductMapper.toProduct(productRequest);
        product.setCategory(cat);
        product = repo.save(product);
        return ProductMapper.toResponse(product);
    }

    public List<ProductResponse> getAll() {
        List<Product> allProducts = repo.findAll();
        return allProducts.stream().map(ProductMapper::toResponse).toList();
    }

    public ProductResponse getById(Long id) {
        Product getProduct = repo.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );
        return ProductMapper.toResponse(getProduct);
    }

    @Transactional
    public ProductResponse update(Long id, UpdateProductRequest newProduct) {
        Product getProduct = repo.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );
        if(newProduct.name() != null) {
            getProduct.setName(newProduct.name());
        }
        if(newProduct.description() != null) {
            getProduct.setDescription(newProduct.description());
        }
        if(newProduct.price() != null){
            getProduct.setPrice(newProduct.price());
        }
        if(newProduct.stock() != null) {
            getProduct.setStock(newProduct.stock());
        }
        if(newProduct.imgUrl() != null){
            getProduct.setImgUrl(newProduct.imgUrl());
        }
        if(newProduct.active() != null){
            getProduct.setActive(newProduct.active());
        }
        if(newProduct.categoryId() != null){
            Category cat = categoryRepo.findById(newProduct.categoryId()).orElseThrow(
                    () -> new InvalidCategoryException(newProduct.categoryId())
            );
            getProduct.setCategory(cat);
        }

        getProduct = repo.save(getProduct);

        return ProductMapper.toResponse(getProduct);
    }

    @Transactional
    public void delete(Long id){
        Product getProduct = repo.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );
        getProduct.setActive(false);
        repo.save(getProduct);
    }
}
