package com.microconnect.productservice.repository;

import com.microconnect.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepostory extends MongoRepository<Product, String> {

}
