package com.hoangsonha.fashion_store.service;


import com.hoangsonha.fashion_store.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product getProductById(Integer id);

    public void deleteProduct(Integer id);

    public Product save(Product product);

    public List<Product> searchProductByName(String name);

    public byte[] readImageContent(String img);

    public List<Product> getThoiTrangNu();

    public List<Product> getThoiTrangNam();

    public Optional<Product> findProductById(Integer id);

    public List<Product> getProductByKey(String key);

    public List<Product> getProductByKeyInNam(String key);

    public List<Product> getProductByKeyInNu(String key);

    public List<Product> getProductByKind_ao();

    public List<Product> getProductByKind_quan();
    public List<Product> getProductByKind_dam();
    public List<Product> getProductByKind_doboi();
    public List<Product> getProductByKind_phukien();

    public List<Product> getProductByKind_giay();
}
