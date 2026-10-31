package com.accenture.productinfo.dto;

/**
 * DTO (Data Transfer Object).
 * Defines the JSON response returned for a valid product code.
 */
public class ProductIdResponse {

    // Holds the product ID found in product.json.
    private String productId;

    // Default constructor.
    public ProductIdResponse() {
    }

    // Constructor to initialize the response.
    public ProductIdResponse(String productId) {
        this.productId = productId;
    }

    // Returns the product ID.
    public String getProductId() {
        return productId;
    }

    // Updates the product ID.
    public void setProductId(String productId) {
        this.productId = productId;
    }
}
