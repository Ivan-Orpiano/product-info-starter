package com.accenture.productinfo.service;

import com.accenture.productinfo.dto.ProductIdResponse;

/**
 * Service interface.
 * Defines the operations supported by the service layer.
 */
public interface ProductService {

    // Declares the method for retrieving a product ID.
    ProductIdResponse getProductId(String productCode);
}
