package com.service.medicine.reponsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.medicine.model.Bill;

@Repository
public interface BillReponsitory extends JpaRepository<Bill, Long> {
    Optional<Bill> findByUserId(String id);
    //    Optional<Bill> findByBillId(Long id);

}
