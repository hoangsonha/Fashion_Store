package com.hoangsonha.fashion_store.database;

import com.hoangsonha.fashion_store.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseTest {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseTest.class);
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
        /*           Product_Nam productA = new Product_Nam();
                productA.setProductName("Áo thun đen");
                productA.setProductYear("2022");
                productA.setPrice(350000);
                productA.setDetail("");
                productA.setSize(new int[] {39,40,41,42,43});
                logger.info("Insert data : " + productRepository.save(productA));
        */
            }

        };
    }
}
