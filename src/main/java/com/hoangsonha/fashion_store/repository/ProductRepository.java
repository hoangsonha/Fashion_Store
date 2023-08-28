package com.hoangsonha.fashion_store.repository;

import com.hoangsonha.fashion_store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Product getProductsById(Integer id);


 /*

 // Search by Query : Tìm kiếm tất cả các thứ liên quan đến keyword bao gồm id, tên, year, kind và giá

    @Query("SELECT p FROM product p WHERE "
     + "CONCAT(p.id, p.product_name,p.product_year,p.kind,p.price)"
     + " LIKE %?1%")
    public List<Product> findAllProductByKey(String keyword);


  */
}
