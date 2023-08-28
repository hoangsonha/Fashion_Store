package com.hoangsonha.fashion_store.service;

import com.hoangsonha.fashion_store.entity.Bill;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public interface BillService {
    public int saveBill(Bill bill);    // chuc nang them ng mua hang cho class bill
    public Integer getId();
    public Collection<Integer> getAllId(String email);


}
