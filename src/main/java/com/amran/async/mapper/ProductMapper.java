package com.amran.async.mapper;

import com.amran.async.domain.ProductEntity;
import com.amran.async.model.Product;
import org.mapstruct.Mapper;

/**
 * @author Md Amran Hossain on 28/10/2024 AD
 * @Project async-api-async-db
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity mapToEntity(Product product);

    Product mapToModel(ProductEntity productEntity);
}
