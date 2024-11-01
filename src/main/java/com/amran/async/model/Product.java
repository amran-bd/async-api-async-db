package com.amran.async.model;

/**
 * @author Md Amran Hossain on 28/10/2024 AD
 * @Project async-api-async-db
 */
public record Product(
        Long id,
        String productName,
        String productType,
        Double price,
        Integer quantity) {
}
