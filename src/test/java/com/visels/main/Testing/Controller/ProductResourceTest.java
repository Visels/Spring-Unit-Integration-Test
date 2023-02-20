package com.visels.main.Testing.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.visels.main.Testing.Domain.Product;
import com.visels.main.Testing.Exception.AlreadyExistsException;
import com.visels.main.Testing.Service.ProductService;
import com.visels.main.Testing.TestH2Repository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.http.RequestEntity.post;
import java.util.List;

@WebMvcTest(ProductResource.class)
class ProductResourceTest {

    @Autowired
    private MockMvc mockMvc;

    private Product RECORD_1;
    private Product RECORD_2;

    private Product RECORD_3;

    ObjectMapper objectMapper = new ObjectMapper();

//    ObjectWriter objectWriter = new ObjectWriter()



    @InjectMocks
    ProductResource productResource;

    @MockBean
    ProductService productService;

//    Product RECORD_1 = new Product(1L, "Cakes", "3200", "Small");
//    Product RECORD_2 = new Product(2L, "Break", "4200", "Medium");
//    Product RECORD_3 = new Product(1L, "Coffee", "8200", "Large");


    @Before
    void setup(){
        MockitoAnnotations.initMocks(this);
        Product RECORD_1 = new Product(1L, "Cakes", "3200", "Small");
        Product RECORD_2 = new Product(2L, "Break", "4200", "Medium");
        Product RECORD_3 = new Product(1L, "Coffee", "8200", "Large");

//        this.mockMvc = MockMvcBuilders.standaloneSetup(this);
    }

    @BeforeEach
    void tryThis(){
        Product RECORD_1 = new Product(1L, "Cakes", "3200", "Small");
        Product RECORD_2 = new Product(2L, "Break", "4200", "Medium");
        Product RECORD_3 = new Product(1L, "Coffee", "8200", "Large");

    }

    @Test
    void getById() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/products/get/34L").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("34L"));





    }



//    @Test
//    void createProduct() throws Exception {
//    Product toBeAdded = new Product(34L, "Bread", "6700", "Large");
//    given(productService.addProduct(toBeAdded)).willReturn(toBeAdded);
//
//    mockMvc.perform(post("http://localhost:8080/products/create").content(String.valueOf(MediaType.APPLICATION_JSON)))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("id").value("34L"));
//    }


    @Test
    @DisplayName("create a product successfully!")
    void testCreateProduct_success() throws Exception {

        Product newProduct = new Product(1L, "Bans", "4500", "medium");

        String content = objectMapper.writeValueAsString(newProduct);
        Mockito.when(productService.addProduct(newProduct)).thenReturn(newProduct);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("http://localhost:8080/products/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name").value("Bans") );



    }

    @Test

    void retrieveAllProducts_success() throws Exception {
        java.util.List<Product> products = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        Mockito.when(productService.retrieveAll()).thenReturn( products);

        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/products/list")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[2].name").value(RECORD_3.getName()))
        ;

//              .andExpect(jsonPath("$[2].name", is(RECORD_3.getName())))
    }



    @Test
    void deleteById() {
    }
}