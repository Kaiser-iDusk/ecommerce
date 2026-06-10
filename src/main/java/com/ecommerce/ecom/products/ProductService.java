package com.ecommerce.ecom.products;

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

    public ProductResponse create(CreateProductRequest productRequest) {
        Product savedProduct = repo.save(ProductMapper.toProduct(productRequest));
        return ProductMapper.toResponse(savedProduct);
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
