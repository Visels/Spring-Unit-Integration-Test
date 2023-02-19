package com.visels.main.Testing;

import com.visels.main.Testing.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Product, Integer> {
}
