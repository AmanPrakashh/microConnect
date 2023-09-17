package com.microconnect.productservice.service;

import com.microconnect.productservice.dto.ProductRequest;
import com.microconnect.productservice.dto.ProductResponse;
import com.microconnect.productservice.model.Product;
import com.microconnect.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Here constructor injection is done by Lombok annotation.
     */
//    public ProductService(ProductRepostory productRepostory) {
//        this.productRepostory = productRepostory;
//    }


    public void createProduct (ProductRequest productRequest)
    {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .build();

        log.info("Product {} is saved", product.getId());
        productRepository.save(product);
    }

    public List<ProductResponse> getProduct() {
        List<Product> products= productRepository.findAll();
       return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

}
