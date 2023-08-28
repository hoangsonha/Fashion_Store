package com.hoangsonha.fashion_store.service;

import com.hoangsonha.fashion_store.entity.Product;
import com.hoangsonha.fashion_store.entity.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingServiceImp implements ShoppingService {
    @Autowired private ProductService productService;

    public final Map<Integer, Shopping> shoppingCart = new HashMap<Integer, Shopping>();

    @Override
    public void addProduct(Integer id) {
        Product product = productService.getProductById(id);
        Shopping shopping = new Shopping();
        if (shoppingCart.containsKey(id)) {
            shopping.setQuantity(shopping.getQuantity() + 1);
        }
            int total = product.getPrice() * shopping.getQuantity();
            shopping.setTotalPrice(total);
            shopping.setProduct(product);
            shoppingCart.put(product.getId(), shopping);
    }

    @Override
    public Shopping editProduct(Integer id, int quantity) {
        Product product = productService.getProductById(id);
        Shopping shopping = new Shopping();
        if(shoppingCart.containsKey(id)) {
            shopping.setQuantity(quantity);
            shopping.setProduct(product);
            int total = product.getPrice() * quantity;
            shopping.setTotalPrice(total);
        }
        shoppingCart.put(id, shopping);
        return shopping;
    }

    @Override
    public void deleteProduct(Integer id) {
        if(shoppingCart.containsKey(id)) {
            shoppingCart.remove(id);
        }
    }
    @Override
    public int totalQuantity() {
        int total = 0;
        for(Map.Entry<Integer, Shopping> shoppingCart1 : shoppingCart.entrySet()) {
            total += shoppingCart1.getValue().getQuantity();
        }
        return total;
    }

    @Override
    public Collection<Shopping> getAll() {
        return shoppingCart.values();
    }

    @Override
    public int totalPrice() {
        int total = 0;
        for(Map.Entry<Integer, Shopping> shoppingCart1 : shoppingCart.entrySet()) {
            total += shoppingCart1.getValue().getTotalPrice();
        }
        return total;
    }


}
