package com.hoangsonha.fashion_store;

import com.hoangsonha.fashion_store.entity.Bill;
import com.hoangsonha.fashion_store.entity.Product;
import com.hoangsonha.fashion_store.repository.BillRepository;
import com.hoangsonha.fashion_store.repository.ProductRepository;
import com.hoangsonha.fashion_store.service.BillService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TestProductDB {
    @Autowired private ProductRepository productRepository;

    @Autowired private BillRepository billRepository;

    @Test
    public void testAddNewProduct() {
        Product newProduct = new Product();
        newProduct.setProductName("Giày Sneaker Vans Style");
        newProduct.setProductYear("2021");
        newProduct.setPrice(1790000);
        newProduct.setDetail("");
        newProduct.setKind("nam nu");

        Product product = productRepository.save(newProduct);

        Assertions.assertThat(product).isNotNull();
    }

    @Test
    public void testGetAllProducts() {
        List<Product> productList = productRepository.findAll();

        Assertions.assertThat(productList).hasSizeGreaterThan(0);

        for(Product product : productList) {
            System.out.println(product.toString());
        }

    }

    @Test
    public void testGetProductById() {
        Integer productId = 3;

        Optional<Product> getProduct = productRepository.findById(productId);

        Assertions.assertThat(getProduct).isPresent();

        System.out.println(getProduct);
    }

    @Test
    public void testUpdateProduct() {
        Integer productId = 1;

        Product product = productRepository.findById(productId).get();

        product.setKind("quần");

        Product updateProduct = productRepository.save(product);

        Assertions.assertThat(updateProduct.getKind()).isEqualTo("quần");
    }

    @Test
    public void testDeleteProduct() {
        Integer productId = 0;

        boolean existes = productRepository.existsById(productId);

        if(existes) {
            productRepository.deleteById(productId);
        }
    }

    @Test
    public void getIdBill() {
        int id = billRepository.getBillById();

        System.out.println(id);
    }
}
