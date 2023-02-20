package com.visels.main.Testing.Controller;

import com.visels.main.Testing.Domain.Product;
import com.visels.main.Testing.Exception.AlreadyExistsException;
import com.visels.main.Testing.Exception.NotFoundException;
import com.visels.main.Testing.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private ProductService productService;
    @Autowired
    public ProductResource(ProductService productService) {
    this.productService = productService;
    }




    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) throws AlreadyExistsException {

        return productService.addProduct(product);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Product> retrieveAllProducts(){
        return productService.retrieveAll();

    }


    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Product getById(@PathVariable Long id) throws NotFoundException {
        return productService.getById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable Long id) throws NotFoundException {

        productService.deleteById(id);
        return new ResponseEntity("product deleted successfuly!", HttpStatus.OK);
    }
}
