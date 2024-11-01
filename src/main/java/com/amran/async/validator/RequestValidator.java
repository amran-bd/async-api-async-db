package com.amran.async.validator;

import com.amran.async.domain.ProductEntity;
import com.amran.async.model.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Md Amran Hossain on 31/10/2024 AD
 * @Project async-api-async-db
 */
public class RequestValidator implements Validator {

    private static final double MINIMUM_PRICE = 1.00;
    private static final int MINIMUM_QUANTITY = 1;

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "Product Name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productType", "Product Type is required");
        Product request = (Product) target;
        if (request.price() == null && request.quantity() == null) {
            errors.rejectValue("price", "404",
                    new Object[]{MINIMUM_QUANTITY}, "The price might be minimum [" + MINIMUM_QUANTITY + "]");
            errors.rejectValue("quantity", "404",
                    new Object[]{MINIMUM_QUANTITY}, "The quantity might be minimum [" + MINIMUM_QUANTITY + "]");
        }
    }
}
