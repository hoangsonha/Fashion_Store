package com.hoangsonha.fashion_store.service;

import com.hoangsonha.fashion_store.entity.Bill;
import com.hoangsonha.fashion_store.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BillServiceImp implements BillService {

    @Autowired private BillRepository billRepository;

    @Override
    public int saveBill(Bill bill) {
         billRepository.save(bill);
         return 1;
    }

    @Override
    public Integer getId() {
        return billRepository.getBillById();
    }



    @Override
    public Collection<Integer> getAllId(String email) {
        List<Bill> bill = (List<Bill>) billRepository.getBillByEmail(email);
        Collection<Integer> collection = new ArrayList<Integer>();
        for(Bill bill1 : bill) {
            int temp = bill1.getId();
            collection.add(temp);
        }
        return collection;
    }

}
