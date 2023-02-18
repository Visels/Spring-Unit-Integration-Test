package com.visels.main.Testing.Controller;

import com.visels.main.Testing.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductResource {

    private ProductService productService;
    @Autowired
    public ProductResource(ProductService productService) {
    this.productService = productService;
    }
}
