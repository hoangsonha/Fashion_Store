package com.hoangsonha.fashion_store.service;

import com.hoangsonha.fashion_store.entity.Product;
import com.hoangsonha.fashion_store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired private ProductRepository productrepository;
    @Override
    public List<Product> getAllProducts() {
        return productrepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productrepository.getProductsById(id);
    }

    @Override
    public void deleteProduct(Integer id) {
        boolean exist = productrepository.existsById(id);
        if (exist) {
            productrepository.deleteById(id);
        }
    }

    @Override
    public Product save(Product product) {
        return productrepository.save(product);
    }

    @Override
    public List<Product> searchProductByName(String name) {
        name.trim().toLowerCase();
        List<Product> productList = new ArrayList<Product>();

        List<Product> productList1 = productrepository.findAll();

        for(Product product : productList1) {
            if(product.getProductName().trim().toLowerCase().contains(name)) {
                productList.add(product);
            }
        }
        return productList;
    }


    @Override
    public byte[] readImageContent(String img) {
        try {                                                      // muốn download file thì trả về dữ liệu resource, muốn xem thì trả ề byte
            Path storageImage = Paths.get("C:\\Users\\ADMIN\\OneDrive\\Spring Boot\\Fashion Store\\src\\main\\resources\\static\\image\\products");
            Path file = storageImage.resolve(img);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return bytes;
            } else {
                throw new RuntimeException("COuld not read image " + img);
            }
        }catch(Exception e) {
            throw new RuntimeException("Could not read image " + img,e);
        }
    }

    @Override
    public List<Product> getThoiTrangNu() {
        String name = "nữ";
        List<Product> productList = new ArrayList<Product>();

        List<Product> productList1 = productrepository.findAll();

        for(Product product : productList1) {
            String s = product.getProductName().trim().toLowerCase();
            if(s.contains(name)) {
                productList.add(product);
            }
        }

        String nameKind = "phụ kiện";
        for(Product product : productList1) {
            String s = product.getKind().trim().toLowerCase();
            if(s.contains(nameKind)) {
                productList.add(product);
            }
        }

        String nameGiay = "giày";
        for(Product product : productList1) {
            String s = product.getKind().trim().toLowerCase();
            if(s.contains(nameGiay)) {
                productList.add(product);
            }
        }

        return productList;
    }

    @Override
    public List<Product> getThoiTrangNam() {
        String name = "nam";
        List<Product> productList = new ArrayList<Product>();

        List<Product> productList1 = productrepository.findAll();

        for(Product product : productList1) {
            String s = product.getProductName().trim().toLowerCase();
            if(s.contains(name)) {
                productList.add(product);
            }
        }

        String nameKind = "phụ kiện";
        for(Product product : productList1) {
            String s = product.getKind().trim().toLowerCase();
            if(s.contains(nameKind)) {
                productList.add(product);
            }
        }

        String nameGiay = "giày";
        for(Product product : productList1) {
            String s = product.getKind().trim().toLowerCase();
            if(s.contains(nameGiay)) {
                productList.add(product);
            }
        }

        return productList;
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return productrepository.findById(id);
    }

    @Override
    public List<Product> getProductByKey(String key) {
        List<Product> productList = productrepository.findAll();
        List<Product> productList1 = new ArrayList<Product>();
        String temp = key.trim().toLowerCase();
        for(Product product : productList) {
            if(product.getProductName().trim().toLowerCase().contains(temp)) {
                productList1.add(product);
            }
        }
        return productList1;
    }

    @Override
    public List<Product> getProductByKeyInNam(String key) {
        List<Product> productList = productrepository.findAll();
        List<Product> productList1 = new ArrayList<Product>();
        String temp = key.trim().toLowerCase();
        for(Product product : productList) {
            if(product.getProductName().trim().toLowerCase().contains("nam") || product.getProductName().trim().toLowerCase().contains("phụ kiện") || product.getProductName().trim().toLowerCase().contains("giày")) {
                if(product.getProductName().trim().toLowerCase().contains(temp)) {
                    productList1.add(product);
                }
            }
        }
        return productList1;
    }

    @Override
    public List<Product> getProductByKeyInNu(String key) {
        List<Product> productList = productrepository.findAll();
        List<Product> productList1 = new ArrayList<Product>();
        String temp = key.trim().toLowerCase();
        for(Product product : productList) {
            if(product.getProductName().trim().toLowerCase().contains("nữ") || product.getProductName().trim().toLowerCase().contains("phụ kiện") || product.getProductName().trim().toLowerCase().contains("giày")) {
                if(product.getProductName().trim().toLowerCase().contains(temp)) {
                    productList1.add(product);
                }
            }
        }
        return productList1;
    }

    @Override
    public List<Product> getProductByKind_ao() {
        List<Product> productList = productrepository.findAll();
        List<Product> productList1 = new ArrayList<Product>();
        for(Product product : productList) {
            if(product.getKind().trim().toLowerCase().equals("áo")) {
                productList1.add(product);
            }
        }
        return productList1;
    }

    @Override
    public List<Product> getProductByKind_quan() {
        List<Product> productList = productrepository.findAll();
        List<Product> productList1 = new ArrayList<Product>();
        for(Product product : productList) {
            if(product.getKind().trim().toLowerCase().equals("quần")) {
                productList1.add(product);
            }
        }
        return productList1;
    }

    @Override
    public List<Product> getProductByKind_dam() {
        List<Product> productList = productrepository.findAll();
        List<Product> productList1 = new ArrayList<Product>();
        for(Product product : productList) {
            if(product.getKind().trim().toLowerCase().equals("đầm")) {
                productList1.add(product);
            }
        }
        return productList1;
    }

    @Override
    public List<Product> getProductByKind_doboi() {
        List<Product> productList = productrepository.findAll();
        List<Product> productList1 = new ArrayList<Product>();
        for(Product product : productList) {
            if(product.getKind().trim().toLowerCase().equals("đồ bơi")) {
                productList1.add(product);
            }
        }
        return productList1;
    }

    @Override
    public List<Product> getProductByKind_phukien() {
        List<Product> productList = productrepository.findAll();
        List<Product> productList1 = new ArrayList<Product>();
        for(Product product : productList) {
            if(product.getKind().trim().toLowerCase().equals("phụ kiện")) {
                productList1.add(product);
            }
        }
        return productList1;
    }

    @Override
    public List<Product> getProductByKind_giay() {
        List<Product> productList = productrepository.findAll();
        List<Product> productList1 = new ArrayList<Product>();
        for (Product product : productList) {
            if (product.getKind().trim().toLowerCase().equals("giày")) {
                productList1.add(product);
            }
        }
        return productList1;
    }
}
