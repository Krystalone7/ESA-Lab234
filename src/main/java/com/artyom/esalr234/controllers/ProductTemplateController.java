package com.artyom.esalr234.controllers;

import com.artyom.esalr234.model.Product;
import com.artyom.esalr234.services.ProductService;
import com.artyom.esalr234.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductTemplateController {

    private final ProductService productService;
    private final WarehouseService warehouseService;

    @Autowired
    public ProductTemplateController(ProductService productService, WarehouseService warehouseService) {
        this.productService = productService;
        this.warehouseService = warehouseService;
    }

    @GetMapping()
    public String showAllProducts(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("warehouses", warehouseService.getAll());
        return "addProduct";
    }

    @PostMapping()
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }
}
