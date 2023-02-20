package com.visels.main.Testing;

import com.visels.main.Testing.Domain.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestingApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	private static RestTemplate restTemplate;
	@Autowired
	private TestH2Repository h2Repository;

	@BeforeAll
	public static void init(){
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setup(){
		baseUrl = baseUrl.concat(":").concat(port+" ").concat("/products");

	}

	@Test
	public void testAddProduct(){
		Product product = new Product(14L, "Cake", "5600","Small");
		Product addedProduct = restTemplate.postForObject(baseUrl, product, Product.class);
		assertEquals(product.getName(),addedProduct.getName());

	}





	@Test
	void contextLoads() {
	}

}
