package com.amran.async.service;

import com.amran.async.domain.ProductEntity;
import com.amran.async.exception.EntityNotFoundException;
import com.amran.async.mapper.ProductMapper;
import com.amran.async.model.Product;
import com.amran.async.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Md Amran Hossain on 28/10/2024 AD
 * @Project async-api-async-db
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Flux<Product> getAllProducts() {
        return productRepository.findAll()
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")))
                .map(productMapper::mapToModel);
    }

    @Override
    public Mono<Product> getProductById(Long id) {
        return productRepository.findById(id).map(productMapper::mapToModel)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));
    }

    @Override
    public Mono<Product> addProduct(Product product) {
        Mono<ProductEntity> productEntity = productRepository.save(productMapper.mapToEntity(product))
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")))
                .doOnError(e -> log.error("Add product getting exception {}", e.getMessage()));
        return productEntity.map(productMapper::mapToModel);
    }

    @Override
    public Mono<Product> updateProduct(Product product, Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Product not found")))
                .flatMap(currentProduct -> {
                    ProductEntity productEntity = productMapper.mapToEntity(product);
                    productEntity.setId(currentProduct.getId());
                    return productRepository.save(productEntity).map(productMapper::mapToModel);
                }).doOnError(e -> log.error("Update product getting exception {}", e.getMessage()));
    }

    @Override
    public Mono<Void> deleteProduct(Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Product not found")))
                .flatMap(currentProduct -> {
                    return productRepository.deleteById(currentProduct.getId());
                });
    }
}
