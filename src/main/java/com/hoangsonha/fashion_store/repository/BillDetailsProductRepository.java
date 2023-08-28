package com.hoangsonha.fashion_store.repository;

import com.hoangsonha.fashion_store.entity.BillDetailsProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailsProductRepository extends JpaRepository<BillDetailsProducts,Integer> {
}
