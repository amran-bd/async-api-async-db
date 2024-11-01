package com.amran.async.constant;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * @author Md Amran Hossain on 29/10/2024 AD
 * @Project async-api-async-db
 */
public class ProductAPI {

    public static final RequestPredicate ACCEPT_JSON = accept(MediaType.APPLICATION_JSON);
    public static final String BASE_URL = "/api/v1/product";
    public static final String GET_PRODUCTS = BASE_URL;
    public static final String GET_PRODUCT_BY_ID = BASE_URL.concat("/{id}");
    public static final String ADD_PRODUCT = BASE_URL;
    public static final String UPDATE_PRODUCT = BASE_URL.concat("/{id}");
    public static final String DELETE_PRODUCT = BASE_URL.concat("/{id}");
}
