package com.ecommerce.ecom.products;

import com.ecommerce.ecom.products.dtos.ProductMapper;
import com.ecommerce.ecom.products.dtos.ProductRequest;
import com.ecommerce.ecom.products.dtos.ProductResponse;
import com.ecommerce.ecom.products.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo repo;

    public ProductResponse create(ProductRequest productRequest) {
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
    public ProductResponse update(Long id, ProductRequest newProduct) {
        Product getProduct = repo.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );
        getProduct.setName(newProduct.getName());
        getProduct.setDescription(newProduct.getDescription());
        getProduct.setPrice(newProduct.getPrice());
        getProduct.setStock(newProduct.getStock());

        getProduct = repo.save(getProduct);

        return ProductMapper.toResponse(getProduct);
    }

    @Transactional
    public void delete(Long id){
        Product getProduct = repo.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );
        repo.deleteById(id);
    }
}
