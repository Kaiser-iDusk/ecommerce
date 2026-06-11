package com.ecommerce.ecom.products;

import com.ecommerce.ecom.common.PagedResponse;
import com.ecommerce.ecom.products.dtos.CreateProductRequest;
import com.ecommerce.ecom.products.dtos.ProductResponse;
import com.ecommerce.ecom.products.dtos.ProductSearchRequest;
import com.ecommerce.ecom.products.dtos.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> create(
            @Validated
            @RequestBody CreateProductRequest productRequest
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productRequest));
    }

    @GetMapping
    public PagedResponse<ProductResponse> getAll(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        ProductSearchRequest search = ProductSearchRequest.builder()
                .keyword(keyword)
                .categoryId(categoryId)
                .page(page)
                .size(size)
                .sortBy("name")
                .direction("asc")
                .build();
        return productService.getAll(search);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody UpdateProductRequest productRequest
    ){
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, productRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ){
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
