package com.hoangsonha.fashion_store.service;

import com.hoangsonha.fashion_store.entity.Shopping;

import java.util.Collection;

public interface ShoppingService {
    public void addProduct(Integer id);

    public Shopping editProduct(Integer id, int quantity);

    public void deleteProduct(Integer id);

    public int totalPrice();

    public int totalQuantity();

    public Collection<Shopping> getAll();


}
