package com.ecommerce.ecom.products;

import com.ecommerce.ecom.products.dtos.ProductRequest;
import com.ecommerce.ecom.products.dtos.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Validated
            @RequestBody ProductRequest productRequest
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productRequest));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody ProductRequest productRequest
    ){
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ){
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
