package com.ecommerce.productservice.service.impl;

import com.ecommerce.productservice.model.AddProductsRequest;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.model.ProductAvailablity;
import com.ecommerce.productservice.payload.ProductRequest;
import com.ecommerce.productservice.payload.ProductResponce;
import com.ecommerce.productservice.payload.ProductResponsePagination;
import com.ecommerce.productservice.repository.ProductRepository;
import com.ecommerce.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;


    @Override
    public List<ProductResponce> getAllProducts() {
        List<Product> products= productRepository.findAll();
        return products.stream().map(this::fromProduct) .collect(Collectors.toList());
    }

    @Override
    public AddProductsRequest addProducts(AddProductsRequest productRequestList) {

        for (Product product : productRequestList.getProducts()) {
            // Set the user ID for each product
            product.setUserId(productRequestList.getUserId());
            product.setProductAvailablity(ProductAvailablity.COMING_SOON);
            // Save the product to the database
            productRepository.save(product);
        }
        return productRequestList;
    }



    @Override
    public Optional<ProductResponce> getProductById(int productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.map( this :: fromProduct );
    }

    @Override
    public ProductResponce createProduct(ProductRequest productRequest) {
        Product product =Product.builder()
                .productId(productRequest.getProductId())
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .userId(productRequest.getUserId())
                .productAvailablity(ProductAvailablity.COMING_SOON)
                .build();
        Product savedProduct = productRepository.save(product);
        return this.fromProduct(savedProduct);
    }

    @Override
    public ProductResponce UpdateProduct(int productId, ProductRequest updateProductRequest) {

        Optional<Product> existingProductOptional = productRepository.findById(productId);
              if(existingProductOptional.isPresent()){
                  Product existingProduct =existingProductOptional.get();
                  existingProduct.setProductName(updateProductRequest.getProductName());
                  existingProduct.setDescription(updateProductRequest.getDescription());
                  existingProduct.setPrice(updateProductRequest.getPrice());
                  existingProduct.setUserId(updateProductRequest.getUserId());
                  existingProduct.setProductAvailablity(updateProductRequest.getProductAvailablity());

                  Product updateProduct = productRepository.save(existingProduct);
                  return this.fromProduct(updateProduct);
              }
              else {
                  throw new RuntimeException("Product not found with ID:"+ productId);
              }

    }

    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);

    }

    @Override
    public  List<ProductResponsePagination> productPagination(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pages = PageRequest.of(pageNumber, pageSize,sort);
        Page<Product> productList = this.productRepository.findAll(pages);
        List<Product> userListContent = productList.getContent();
        List<ProductResponce> productResponseList = userListContent.stream().map((this::fromProduct)).toList();
        ProductResponsePagination productResponsePagination = ProductResponsePagination.builder()
                .productResponces(productResponseList)
                .pageNumber(productList.getTotalPages())
                .pageSize(productList.getSize())
                .totalElements(productList.getTotalElements())
                .totalPages(productList.getTotalPages())
                .lastPage(productList.isLast())
                .build();
        log.info("User list using pagination {} " ,userListContent);
        return Collections.singletonList(productResponsePagination);
    }

    @Override
    public List<ProductResponce> getProductByUserId(int userId) {
        List<Product> productList = this.productRepository.findByUserId(userId);
        return productList.stream().map(this::fromProduct).collect(Collectors.toList());
    }

    private ProductResponce fromProduct(Product product) {
        return ProductResponce.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .userId(product.getUserId())
                .productAvailablity(product.getProductAvailablity())
                .build();
    }
}
