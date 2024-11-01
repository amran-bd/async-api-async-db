package com.amran.async.service;

import com.amran.async.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Md Amran Hossain on 28/10/2024 AD
 * @Project async-api-async-db
 */
public interface ProductService {

    Flux<Product> getAllProducts();

    Mono<Product> getProductById(Long id);

    Mono<Product> addProduct(Product product);

    Mono<Product> updateProduct(Product product, Long id);

    Mono<Void> deleteProduct(Long id);
}
