package com.amran.async.repository;

import com.amran.async.domain.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Md Amran Hossain on 28/10/2024 AD
 * @Project async-api-async-db
 */
@Repository
public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, Long> {
//    @Query("SELECT * FROM product WHERE product_name = :productName")
//    Flux<ProductEntity> findByProductName(String productName);
}
