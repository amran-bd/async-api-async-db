package com.amran.async.validator;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * @author Md Amran Hossain on 31/10/2024 AD
 * @Project async-api-async-db
 */
public abstract class AbstractValidationHandler<T, U extends Validator> {

    private final Class<T> validationClass;

    private final U validator;

    protected AbstractValidationHandler(Class<T> clazz, U validator) {
        this.validationClass = clazz;
        this.validator = validator;
    }

    abstract protected Mono<ServerResponse> addProduct(T validBody, final ServerRequest originalRequest);

    abstract protected Mono<ServerResponse> updateProduct(T validBody, final ServerRequest originalRequest);

    public final Mono<ServerResponse> handleRequest(final ServerRequest request) {
        return request.bodyToMono(this.validationClass).flatMap(body -> {
            Errors errors = new BeanPropertyBindingResult(body, this.validationClass.getName());
            this.validator.validate(body, errors);
            if (errors == null || errors.getAllErrors().isEmpty()) {
                Long productId = Long.valueOf(request.pathVariable("id"));
                if (productId != null) {
                    return updateProduct(body, request);
                } else {
                    return addProduct(body, request);
                }
            } else {
                return onValidationErrors(errors, body, request);
            }
        });
    }

    protected Mono<ServerResponse> onValidationErrors(Errors errors, T invalidBody, final ServerRequest request) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getAllErrors().toString());
    }
}