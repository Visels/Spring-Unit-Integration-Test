package com.visels.main.Testing.Repository;

import com.visels.main.Testing.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
