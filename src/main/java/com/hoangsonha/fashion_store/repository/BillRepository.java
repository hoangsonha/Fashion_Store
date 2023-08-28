package com.hoangsonha.fashion_store.repository;

import com.hoangsonha.fashion_store.entity.Bill;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    public Bill getBillByEmail(String email);

    @Query(value = "SELECT max(id) FROM bills ", nativeQuery = true)
    public int getBillById();
}
