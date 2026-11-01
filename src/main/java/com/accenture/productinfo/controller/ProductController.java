package com.accenture.productinfo.controller;

import com.accenture.productinfo.dto.ProductIdResponse;
import com.accenture.productinfo.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller.
 * Receives HTTP requests and delegates them to ProductService.
 */
@RestController
@RequestMapping("/ms-product-info")
public class ProductController {

    // Records requests for debugging.
    private static final Logger log =
            LoggerFactory.getLogger(ProductController.class);

    // Reference to the service interface.
    private final ProductService productService;

    // Constructor-based dependency injection.
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Handles GET requests for product IDs.
     */
    @GetMapping("/getProductId/{productCode}")
    public ProductIdResponse getProductDetails(
            @PathVariable("productCode") String productCode) {

        log.info("Looking up product code: {}", productCode);

        // Let the service handle the actual lookup.
        return productService.getProductId(productCode);
    }
}
