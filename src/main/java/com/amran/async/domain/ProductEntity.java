package com.amran.async.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author Md Amran Hossain on 28/10/2024 AD
 * @Project async-api-async-db
 */
@Builder
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity implements Serializable {
    @Id
    private Long id;
    private String productName;
    private String productType;
    private Double price;
    private Integer quantity;
}
