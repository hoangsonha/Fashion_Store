package com.hoangsonha.fashion_store.service;

import com.hoangsonha.fashion_store.entity.BillDetailsProducts;
import com.hoangsonha.fashion_store.entity.Shopping;

import java.util.Collection;

public interface BillDetailProductService {
    public void addBillDetail(BillDetailsProducts billDetailsProducts);

    public void addBillDetail(Collection<Shopping> cart, String email);   // them nhung san pham ng do mua

  //  public void addBillDetail(Collection<Shopping> cart);   // them nhung san pham ng do mua
}
