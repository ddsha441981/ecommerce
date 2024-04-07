package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.config.AppConstant;
import com.ecommerce.productservice.model.AddProductsRequest;
import com.ecommerce.productservice.payload.ProductRequest;
import com.ecommerce.productservice.payload.ProductResponce;
import com.ecommerce.productservice.payload.ProductResponsePagination;
import com.ecommerce.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponce> createProduct (@RequestBody ProductRequest productRequest){
    ProductResponce createdProduct = productService.createProduct(productRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PostMapping("/listProducts")
    public ResponseEntity<AddProductsRequest> createProduct (@RequestBody AddProductsRequest list){
        AddProductsRequest products = productService.addProducts(list);
        return ResponseEntity.status(HttpStatus.CREATED).body(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<ProductResponce>> getProductById(@PathVariable int productId){
    Optional<ProductResponce> product = Optional.ofNullable(productService.getProductById(productId)
            .orElseThrow(() -> new RuntimeException("Product Not Found With Id " + productId)));
    return ResponseEntity.ok(product);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponce>> getAllProducts(){
        List<ProductResponce> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponce> updateProduct(@PathVariable int productId,
                                                         @RequestBody ProductRequest updateProductRequest){
        ProductResponce updateProduct = productService.UpdateProduct(productId, updateProductRequest);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductResponce> deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/pagination")
    public ResponseEntity<List<ProductResponsePagination>> getPaginationData(
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(defaultValue = AppConstant.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(defaultValue =AppConstant.DEFAULT_SORT_DIRECTION) String sortDir
    ) {
        this.productService.productPagination(pageNumber, pageSize, sortBy, sortDir);
        List<ProductResponsePagination> userResponses = this.productService.productPagination(pageNumber, pageSize, sortBy, sortDir);
        log.info("Product list found successfully");
        return new ResponseEntity<>(userResponses, HttpStatus.FOUND);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductResponce>> getProductsByUserId(@PathVariable int userId){
        List<ProductResponce> productResponceList = this.productService.getProductByUserId(userId);
        return new ResponseEntity<>(productResponceList, HttpStatus.FOUND);
    }


}
