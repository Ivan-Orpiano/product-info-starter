
package com.accenture.productinfo.service.impl;

import com.accenture.productinfo.dto.ProductIdResponse;
import com.accenture.productinfo.exception.BadRequestException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ProductServiceImpl.
 * Uses the product.json file from the application classpath.
 */
class ProductServiceImplemTest {

    private ProductServiceImpl productService;

    // Prepare the service before each test.
    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl();
    }

    /**
     * TEST CASE 1:
     * Verify that a valid product code returns its product ID.
     */
    @Test
    void testGetProductId_ValidProductCode() {

        // Act: Request an existing product code.
        ProductIdResponse response =
                productService.getProductId("123456");

        // Assert: Verify the expected product ID.
        assertEquals("1234567", response.getProductId());
    }

    /**
     * TEST CASE 2:
     * Verify that an invalid product code throws an exception.
     */
    @Test
    void testGetProductId_InvalidProductCode() {

        // Act: Request a product code that does not exist.
        BadRequestException exception = assertThrows(
                BadRequestException.class,
                () -> productService.getProductId("12345")
        );

        // Assert: Verify HTTP 400.
        assertEquals(400, exception.getErrorId());

        // Assert: Verify the expected error message.
        assertEquals(
                "Product code 12345 not found.",
                exception.getErrorMessage()
        );

        // Assert: Verify that error details are empty.
        assertTrue(exception.getErrorDetails().isEmpty());
    }
}
