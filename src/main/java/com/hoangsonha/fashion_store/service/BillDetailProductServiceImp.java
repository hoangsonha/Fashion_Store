package com.hoangsonha.fashion_store.service;

import com.hoangsonha.fashion_store.entity.Bill;
import com.hoangsonha.fashion_store.entity.BillDetailsProducts;
import com.hoangsonha.fashion_store.entity.Shopping;
import com.hoangsonha.fashion_store.repository.BillDetailsProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BillDetailProductServiceImp implements BillDetailProductService{
    @Autowired private BillDetailsProductRepository billDetailsProductRepository;
    @Autowired private BillService billService;
    @Autowired private ShoppingService shoppingService;

    @Override
    public void addBillDetail(BillDetailsProducts billDetailsProducts) {
        billDetailsProductRepository.save(billDetailsProducts);
    }
    @Override
    public void addBillDetail(Collection<Shopping> cart, String email) {
        Bill bill = new Bill();
        bill.setId(billService.getId());
        cart = (Collection<Shopping>) shoppingService.getAll();

        for(Shopping itemCart : cart) {
            BillDetailsProducts billDetailsProducts = new BillDetailsProducts();

            billDetailsProducts.setBill_id(bill.getId());
            int product_id = itemCart.getProduct().getId();
            billDetailsProducts.setProduct_id(product_id);
            int quantity = itemCart.getQuantity();
            billDetailsProducts.setQuantity(quantity);
            int total = itemCart.getTotalPrice();
            billDetailsProducts.setPrice(total);

            billDetailsProductRepository.save(billDetailsProducts);
        }
    }
}
