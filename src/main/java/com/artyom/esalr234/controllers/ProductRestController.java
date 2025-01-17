package com.artyom.esalr234.controllers;

import com.artyom.esalr234.model.Product;
import com.artyom.esalr234.services.ProductService;
import com.artyom.esalr234.services.WarehouseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.ContentType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("json/{id}")
    public ResponseEntity<Product> getProductJsonById(@PathVariable(name = "id") Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getXMLProductById(@PathVariable(name = "id") Long id) throws JsonProcessingException {
        Product product = productService.findById(id).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Добавление инструкции для XSLT
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<?xml-stylesheet type=\"text/xsl\" href=\"/templates/product.xsl\"?>\n"
                + xmlMapper.writeValueAsString(product);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(xml);
    }
}
