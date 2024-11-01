package com.amran.async.handler;

import com.amran.async.model.Product;
import com.amran.async.service.ProductService;
import com.amran.async.validator.AbstractValidationHandler;
import com.amran.async.validator.RequestValidator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author Md Amran Hossain on 28/10/2024 AD
 * @Project async-api-async-db
 */
@Component
public class ProductHandler extends AbstractValidationHandler<Product, RequestValidator> {
    private final ProductService productService;

    public ProductHandler(ProductService productService) {
        super(Product.class, new RequestValidator());
        this.productService = productService;
    }

    public Mono<ServerResponse> getAllProducts(ServerRequest request) {
        //We can manage pagination
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productService.getAllProducts(), Product.class);
    }

    public Mono<ServerResponse> getProductById(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productService.getProductById(Long.parseLong(request.pathVariable("id"))), Product.class);
    }

    @Override
    protected Mono<ServerResponse> addProduct(Product validBody, ServerRequest originalRequest) {
        Mono<Product> productMono = Mono.just(validBody)
                .flatMap(productService::addProduct);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productMono, Product.class);
    }

    @Override
    protected Mono<ServerResponse> updateProduct(Product validBody, ServerRequest originalRequest) {
        Mono<Product> productMono = Mono.just(validBody)
                .flatMap(p -> productService.updateProduct(p, Long.parseLong(originalRequest.pathVariable("id"))));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productMono, Product.class);
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        Mono<Void> productMono = Mono.just(Long.valueOf(request.pathVariable("id")))
                .flatMap(productService::deleteProduct);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productMono, Void.class);
    }
}
