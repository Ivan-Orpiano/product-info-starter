package com.accenture.productinfo.service.impl;

import com.accenture.productinfo.dto.ProductIdResponse;
import com.accenture.productinfo.exception.BadRequestException;
import com.accenture.productinfo.service.ProductService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Collections;
import java.util.Map;

/**
 * Implementation of the ProductService interface.
 *
 * <p>Retrieves product IDs from product.json and
 * follows the provided UML class diagram.</p>
 */
@Service
public class ProductServiceImpl implements ProductService {

    // UML Attribute: - log: Logger
    // Used to record application events and errors.
    private static final Logger log =
            LoggerFactory.getLogger(ProductServiceImpl.class);

    /**
     * Retrieves a product ID using its product code.
     *
     * @param productCode Product code provided by the client.
     * @return ProductIdResponse containing the product ID.
     */
    @Override
    public ProductIdResponse getProductId(String productCode) {

        // Call readFile() as required by the UML diagram.
        Map<String, String> products = readFile();

        // Search for the product code inside the JSON data.
        String productId = products.get(productCode);

        // Throw an exception if the product code does not exist.
        if (productId == null) {

            log.warn("Product code {} not found.", productCode);

            throw new BadRequestException(
                    HttpStatus.BAD_REQUEST.value(),
                    "Product code " + productCode + " not found.",
                    Collections.emptyMap()
            );
        }

        // Return the matching product ID.
        log.info("Product ID found for code: {}", productCode);

        return new ProductIdResponse(productId);
    }

    /**
     * Reads the 'product.json' file from the application resources
     * and parses its contents into a Map of key-value pairs.
     *
     * @return A Map containing the product data, or an empty map if
     *         reading fails.
     */
    private Map<String, String> readFile() {

        // Access the JSON file from src/main/resources.
        Resource resource =
                new ClassPathResource("product.json");

        // Create a JSON parser locally, not as a class attribute.
        ObjectMapper objectMapper = new ObjectMapper();

        // Read the JSON file using an InputStream.
        try (InputStream inputStream = resource.getInputStream()) {

            // Convert JSON key-value pairs into a Java Map.
            Map<String, String> products = objectMapper.readValue(
                    inputStream,
                    new TypeReference<Map<String, String>>() {}
            );

            // Reject invalid JSON content such as null.
            if (products == null) {
                throw new IOException(
                        "product.json must contain a JSON object."
                );
            }

            // Return the product-code mappings.
            return products;

        } catch (IOException exception) {

            // Record the error in the application logs.
            log.error("Unable to read product.json", exception);

            // Let GlobalExceptionHandler return HTTP 500.
            throw new UncheckedIOException(
                    "Unable to read JSON file.",
                    exception
            );
        }
    }
}
