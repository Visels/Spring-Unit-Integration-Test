package com.visels.main.Testing.Service;

import com.visels.main.Testing.Domain.Product;
import com.visels.main.Testing.Exception.AlreadyExistsException;
import com.visels.main.Testing.Exception.NotFoundException;
import com.visels.main.Testing.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product addProduct(Product product) throws AlreadyExistsException {
        //validation
        Optional<Product> retrievedProduct = productRepository.findById(product.getId());
        if(retrievedProduct.isPresent()){
            throw new AlreadyExistsException("The product already exists", 500);
        }
        product = productRepository.save(product);
        return product;
    }

    public Page<Product> retrieveAll() {
        Pageable pageable = PageRequest.of(1,5);
     return productRepository.findAll(pageable);
    }

    public Product getById(Long id) throws NotFoundException {
        Optional<Product> foundProduct = productRepository.findById(id);

        if(foundProduct.isEmpty()) throw new NotFoundException("The product cannot be found", 500);

            return foundProduct.get();
    }

    public void deleteById(Long id) throws NotFoundException {

        Optional<Product> foundProduct = productRepository.findById(id);

        if(foundProduct.isEmpty()) throw new NotFoundException("The product cannot be found", 500);

        productRepository.deleteById(id);
    }
}
