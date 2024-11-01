package com.amran.async.controller.functional;

import com.amran.async.constant.ProductAPI;
import com.amran.async.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

/**
 * @author Md Amran Hossain on 28/10/2024 AD
 * @Project async-api-async-db
 */
@Configuration(proxyBeanMethods = false)
public class ProductRoute {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(ProductHandler productHandler) {
        return RouterFunctions
                .route(GET(ProductAPI.GET_PRODUCTS).and(ProductAPI.ACCEPT_JSON), productHandler::getAllProducts)
                .andRoute(GET(ProductAPI.GET_PRODUCT_BY_ID).and(ProductAPI.ACCEPT_JSON), productHandler::getProductById)
                .andRoute(POST(ProductAPI.ADD_PRODUCT).and(ProductAPI.ACCEPT_JSON), productHandler::handleRequest)
                .andRoute(DELETE(ProductAPI.DELETE_PRODUCT).and(ProductAPI.ACCEPT_JSON), productHandler::deleteProduct)
                .andRoute(PUT(ProductAPI.UPDATE_PRODUCT).and(ProductAPI.ACCEPT_JSON), productHandler::handleRequest);
    }
}
